package za.co.absa.africanacity.AbsaBillingExtract.service.impl.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import za.co.absa.africanacity.AbsaBillingExtract.domain.Billing;
import za.co.absa.africanacity.AbsaBillingExtract.repository.BillingRepository;
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;
import za.co.absa.africanacity.AbsaBillingExtract.service.impl.BillingExtratorServiceImpl;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaDateUtil;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaFileUploadUtil;

import javax.ws.rs.core.Response;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BillingExtratorServiceImplIT {

    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

    private BillingExtratorService service;

    @Autowired
    private BillingRepository repository;

    @Autowired
    private AbsaFileUploadUtil absaFileUploadUtil;

    private File file;

    private String dateRun;

    @Before
    public void setUp() throws Exception {

        service = new BillingExtratorServiceImpl();
        dateRun = AbsaDateUtil.getFirstBusinessWorkingDay(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(new Date()));

        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "absaFileUploadUtil", absaFileUploadUtil);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void extract() throws Exception {

        service.extract(dateRun);
    }
}