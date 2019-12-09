package za.co.absa.africanacity.AbsaBillingExtract.util;

import com.github.ffpojo.FFPojoHelper;
import com.github.ffpojo.exception.FFPojoException;
import za.co.absa.africanacity.AbsaBillingExtract.domain.Billing;
import za.co.absa.africanacity.AbsaBillingExtract.domain.BillingFileRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class BillingRecordFileWriter {

    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

    public static String createBilingExtractFile(File file, List<Billing> billingList, String serviceName, String subServiceName) throws IOException, FFPojoException, ParseException {

        FFPojoHelper ffpojo = FFPojoHelper.getInstance();

        file.createNewFile();
        BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(file));
        for(Billing billing: billingList){

            System.out.println(billing.getDateTimeCreated());
            //Date date = new SimpleDateFormat(SIMPLE_DATE_FORMAT).parse(new Date(billing.getDateTimeCreated()).toString());

            String line = ffpojo.parseToText(new BillingFileRecord(billing.getClientSwiftAddress(), serviceName, subServiceName, "20101212", billing.getMessageStatus()));
            textFileWriter.write(line);
            textFileWriter.newLine();
        }
        textFileWriter.close();
        System.out.println("EXTRACT CREATED " + file.getAbsolutePath());

        return file.getAbsolutePath();
    }
}
