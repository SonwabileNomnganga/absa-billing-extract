package za.co.absa.africanacity.AbsaBillingExtract.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import za.co.absa.africanacity.AbsaBillingExtract.domain.Billing;
import za.co.absa.africanacity.AbsaBillingExtract.repository.BillingRepository;
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaDateUtil;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaFileUploadUtil;

import javax.ws.rs.core.Response;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BillingExtratorServiceImplTest {

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
        repository = mock(BillingRepository.class);
        absaFileUploadUtil = mock(AbsaFileUploadUtil.class);
        file = mock(File.class);

        dateRun = AbsaDateUtil.getFirstBusinessWorkingDay(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(new Date()));

        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "absaFileUploadUtil", absaFileUploadUtil);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * When executing the test below.. the sample test file does get created, as means to prove that the process does try reach the end.
     *
     * @throws Exception
     */
    @Test
    public void extract() throws Exception {

        when(repository.findAll()).thenReturn(createSampleList());
        when(absaFileUploadUtil.uploadFile(anyObject(), anyObject())).thenReturn(createPositiveResponse());

        service.extract(dateRun);

        verify(repository, atLeastOnce()).findAll();
        verify(absaFileUploadUtil, atLeastOnce()).uploadFile(anyObject(), anyObject());
    }

    private Response createPositiveResponse() {
        return Response.status(200)
                .entity("Sucess")
                .build();
    }

    private List<Billing> createSampleList() {

        List<Billing> list = new ArrayList<>();
        list.add(new Billing("ere", "dd", "sd", "sd", "sad", "sas"));
        list.add(new Billing("ere", "dd", "sd", "sd", "sad", "sas"));
        list.add(new Billing("ere", "dd", "", "sd", "sad", "sas"));

        return list;
    }
}