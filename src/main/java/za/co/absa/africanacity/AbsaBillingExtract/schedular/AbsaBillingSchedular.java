package za.co.absa.africanacity.AbsaBillingExtract.schedular;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.absa.africanacity.AbsaBillingExtract.service.BillingExtratorService;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaDateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class AbsaBillingSchedular {

    public static final String FULL_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

    @Autowired
    private BillingExtratorService service;

    /**
     * Scheduled(cron = "0 0 7 1 * ?")
     * The cron value above would start the the scheduler at 7:00 am on 1ST day of every month. Because the cron does not
     * know of holidays and weekends, the java code was added as a extra to make sure that the billing extract date recorded on the file is the legitimate
     * next business working day. To explain that see the discussion below as there will be different conditions to look for, e.g.
     *  if the scheduler is triggered on,
     *
     * 1. 01-11-2019 is on Friday, which is the first legitimate business day. 01112019 gets recorded to the file.
     *
     * 2. 01-06-2019 is on Saturday there we cannot record that as the first business working day. That therefore makes the first business
     * working day Monday the 3rd of June 2019 and we need to record that.
     *
     * 3. 01-12-2019 is on Sunday and that is not the correct day to record. The correct business day to record is Monday 2nd December 2019
     *
     * 4. 01-01-2020 is on Wednesday. Because the 1st of January is a public holiday, the next correct business day is the Thursday 2nd January 2020.
     *
     *
     * To demonstrate to correctness on the argument above please run the test 'TestCheckFirstBusinessDay.java' class and manipulate the
     * SOURCE_DATE field value and verify the output printout.
     *
     * For demonstration purposes, the cron will be scheduled to run every 30 seconds
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 48 13 * * *")
    public void schedule() throws Exception{

        String scheduledDate = new SimpleDateFormat(FULL_DATE_FORMAT).format(new Date());
        log.info("Scheduled time: " + scheduledDate);
        service.extract(AbsaDateUtil.getFirstBusinessWorkingDay(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(new Date())));
    }
}
