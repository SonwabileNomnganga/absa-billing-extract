package za.co.absa.africanacity.AbsaBillingExtract.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AbsaDateUtil {

    enum Holiday {
        NEW_YEARS_DAY("0101"),
        HUMAN_RIGHTS_DAY("0321"),
        FREEDOM_DAY("0627"),
        WORKERS_DAY("0501"),
        YOUTH_DAY("0616"),
        NATIONAL_WOMENS_DAY("0809"),
        HERITAGE_DAY("0924"),
        DAY_OF_RECONCILIATION("1216"),
        CHRISTMAS_DAY("1225"),
        DAY_OF_GOODWILL("1226");

        private String date;

        Holiday(String date) {
            this.date = date;
        }

        public String getDate() {
            return this.date;
        }
    }

    public static final String MRS_DATE_FORMAT = "yyyyMMdd";
    public static final String SUNDAY = "SUNDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String DAY_OF_THE_WEEK_FORMAT = "EEEE";
    public static final String MONTH_DAY_FORMAT = "MMdd";
    public static final String FINSWITCH_DATE_FORMAT = "yyyy-MM-dd";

    public static String getFirstBusinessWorkingDay(String currentDate)throws Exception {

        DateFormat dateFormat = new SimpleDateFormat(MRS_DATE_FORMAT);
        Date myDate = dateFormat.parse(currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        calendar.add(Calendar.DAY_OF_YEAR, + 0);
        Date nextDay = calendar.getTime();

        while(true) {

            dateFormat = new SimpleDateFormat(MRS_DATE_FORMAT);
            Date date = dateFormat.parse(dateFormat.format(nextDay));

            if(checkWeekends(dateFormat, date) || checkHolidays(dateFormat, date)) {
                calendar.add(Calendar.DAY_OF_YEAR, + 1);
                nextDay = calendar.getTime();
            } else {
                return new SimpleDateFormat(FINSWITCH_DATE_FORMAT).format(nextDay);
            }
        }
    }

    private static boolean checkWeekends(DateFormat dateFormat, Date date) {
        dateFormat = new SimpleDateFormat(DAY_OF_THE_WEEK_FORMAT);
        String day = dateFormat.format(date).toUpperCase();

        return SUNDAY.equals(day) || SATURDAY.equals(day);
    }

    private static boolean checkHolidays(DateFormat dateFormat, Date date) {

        dateFormat = new SimpleDateFormat(MONTH_DAY_FORMAT);
        String holidayDate = dateFormat.format(date);

        for (Holiday holiday: Holiday.values()) {
            if (holiday.date.equals(holidayDate))
                return true;

        }
        return false;
    }
}
