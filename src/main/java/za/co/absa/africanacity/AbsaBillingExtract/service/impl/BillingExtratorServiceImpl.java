package za.co.absa.africanacity.AbsaBillingExtract.service.impl;

import com.github.ffpojo.exception.FFPojoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.absa.africanacity.AbsaBillingExtract.domain.Billing;
import za.co.absa.africanacity.AbsaBillingExtract.repository.BillingRepository;
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaFileUploadUtil;
import za.co.absa.africanacity.AbsaBillingExtract.util.BillingRecordFileWriter;

import javax.ws.rs.core.Response;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private AbsaFileUploadUtil absaFileUploadUtil;


    @Override
    public void extract(String firstBusinessWorkingDay) throws IOException, FFPojoException, ParseException {

        log.info(BillingExtratorServiceImpl.SERVICE_NAME.concat(" - Billing Extract Process Started...."));

        List<Billing> billingList = repository.findAll();
        File file = new File(System.getProperty(TEMP_DIR).concat(FILE_NAME.concat(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(new Date())).concat(FILE_EXT)));

        List<Billing> filteredList = billingList
                .parallelStream()
                .filter(b -> {
                    return StringUtils.isNotBlank(b.getMessageStatus());
                })
                .collect(Collectors.toList());

        BillingRecordFileWriter.createBillingExtractFile(file, filteredList, SERVICE_NAME, SUB_SERVICE_NAME, firstBusinessWorkingDay);

        log.info("EXTRACT CREATED " + file.getAbsolutePath());

        Response response = absaFileUploadUtil.uploadFile(file, BASE_URL);

        log.info("Status " + response.getStatus());
    }

}
