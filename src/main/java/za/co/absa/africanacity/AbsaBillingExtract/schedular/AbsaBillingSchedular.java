package za.co.absa.africanacity.AbsaBillingExtract.schedular;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class AbsaBillingSchedular {

    public static final String FULL_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Autowired
    private BillingExtratorService service;

    @Scheduled(cron = "0 20 9 * * *")
    public void schedule() throws Exception{

        log.info("Scheduled time: " + new SimpleDateFormat(FULL_DATE_FORMAT).format(new Date()));

        service.extract();
    }
}
