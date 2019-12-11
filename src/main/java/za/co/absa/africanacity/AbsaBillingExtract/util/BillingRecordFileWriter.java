package za.co.absa.africanacity.AbsaBillingExtract.util;

import com.github.ffpojo.FFPojoHelper;
import com.github.ffpojo.exception.FFPojoException;
import org.apache.commons.lang3.StringUtils;
import za.co.absa.africanacity.AbsaBillingExtract.domain.Billing;
import za.co.absa.africanacity.AbsaBillingExtract.domain.BillingFileRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class BillingRecordFileWriter {

    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

    public static void createBillingExtractFile(File file, List<Billing> billingList, String serviceName, String subServiceName, String firstBusinessWorkingDay) throws IOException, FFPojoException, ParseException {

        /*FFPojoHelper ffpojo = FFPojoHelper.getInstance();

        file.createNewFile();
        BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(file));
        String currentRunDate = firstBusinessWorkingDay.replace("-", "");
        for(Billing billing: billingList){

            if(Integer.parseInt(billing.getDateTimeCreated().substring(5, 7)) == Integer.parseInt(currentRunDate.substring(4, 6)) -1) {

                String line = ffpojo.parseToText(new BillingFileRecord(billing.getClientSwiftAddress(), serviceName, subServiceName, firstBusinessWorkingDay.replace("-", ""), billing.getMessageStatus()));
                textFileWriter.write(line);
                textFileWriter.newLine();
            }
        }
        textFileWriter.close();*/


        FFPojoHelper ffpojo = FFPojoHelper.getInstance();

        file.createNewFile();
        BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(file));
        String currentRunDate = firstBusinessWorkingDay.replace("-", "");
        billingList
                .parallelStream()
                .filter(b -> {
                    if(Integer.parseInt(b.getDateTimeCreated().substring(5, 7)) == Integer.parseInt(currentRunDate.substring(4, 6)) -1) {

                        String line = null;
                        try {
                            line = ffpojo.parseToText(new BillingFileRecord(b.getClientSwiftAddress(), serviceName, subServiceName, firstBusinessWorkingDay.replace("-", ""), b.getMessageStatus()));
                            textFileWriter.write(line);
                            textFileWriter.newLine();
                        } catch (FFPojoException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                      return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        textFileWriter.close();
    }
}
