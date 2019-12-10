package za.co.absa.africanacity.AbsaBillingExtract.schedular;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaDateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;

public class AbsaBillingSchedularTest {

    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

    private AbsaBillingSchedular absaBillingSchedular;
    private BillingExtratorService service;

    @Before
    public void setUp() throws Exception {

        absaBillingSchedular = new AbsaBillingSchedular();
        service = mock(BillingExtratorService.class);
        ReflectionTestUtils.setField(absaBillingSchedular, "service", service);

    }

    @Test
    public void schedule() throws Exception{

        absaBillingSchedular.schedule();

        verify(service, atLeastOnce()).extract(AbsaDateUtil.getFirstBusinessWorkingDay(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(new Date())));
    }
}