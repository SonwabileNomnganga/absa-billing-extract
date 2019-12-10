package za.co.absa.africanacity.AbsaBillingExtract.service;

import com.github.ffpojo.exception.FFPojoException;

import java.io.IOException;
import java.text.ParseException;

public interface BillingExtratorService {
    void extract(String firstBusinessWorkingDay) throws IOException, FFPojoException, ParseException;
}
