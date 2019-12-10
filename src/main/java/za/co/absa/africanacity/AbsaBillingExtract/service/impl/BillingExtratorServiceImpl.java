package za.co.absa.africanacity.AbsaBillingExtract.service.impl;

import com.github.ffpojo.exception.FFPojoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.absa.africanacity.AbsaBillingExtract.domain.Billing;
import za.co.absa.africanacity.AbsaBillingExtract.repository.BillingRepository;
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;
import za.co.absa.africanacity.AbsaBillingExtract.util.BillingRecordFileWriter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BillingExtratorServiceImpl implements BillingExtratorService {

    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    public static final String SERVICE_NAME = "SRVNAME";
    public static final String SUB_SERVICE_NAME = "SSNME";
    public static final String BASE_URL = "http://localhost:7555/file";
    public static final String TEMP_DIR = "java.io.tmpdir";
    public static final String FILE_NAME = "billing_extract_";
    public static final String FILE_EXT = ".txt";

    @Autowired
    private BillingRepository repository;


    @Override
    public void extract(String firstBusinessWorkingDay) throws IOException, FFPojoException, ParseException {

        log.info(BillingExtratorServiceImpl.SERVICE_NAME.concat(" - Billing Extract Process Started...."));
        File file = new File(System.getProperty(TEMP_DIR).concat(FILE_NAME.concat(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(new Date())).concat(FILE_EXT)));
        List<Billing> billingList = repository.findAll();

        BillingRecordFileWriter.createBilingExtractFile(file, billingList, SERVICE_NAME, SUB_SERVICE_NAME, firstBusinessWorkingDay);

        BasicHttpContext localContext = new BasicHttpContext();
        HttpClient httpClient = HttpClientBuilder.create().build();
        ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient, localContext);
        ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();

        InputStream fileInStream = new FileInputStream(file);
        String contentDisposition = "attachment; filename=" + file.getName();

        int status =  client.target(BASE_URL)
                .path("post")
                .request()
                .header("Content-Disposition", contentDisposition)
                .post(Entity.entity(fileInStream, MediaType.APPLICATION_OCTET_STREAM)).getStatus();

        log.info("EXTRACT CREATED " + file.getAbsolutePath());
        log.info("Status " + status);
    }
}
