package za.co.absa.africanacity.AbsaBillingExtract.schedular;

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
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AbsaBillingSchedularIT {

    private AbsaBillingSchedular schedular;

    @Autowired
    private BillingExtratorService service;


    @Before
    public void setUp() throws Exception {

        schedular = new AbsaBillingSchedular();

        ReflectionTestUtils.setField(schedular,  "service", service);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void schedule() throws Exception {

        schedular.schedule();
    }
}