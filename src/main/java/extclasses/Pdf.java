package extclasses;

import java.io.File;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Auriwield on 25.01.2016.
 */
public class Pdf extends File {
    private boolean isText = false;
    private String date;

    public Pdf(String pathname) {
        super(pathname);
    }

    public boolean isText() {
        return isText;
    }

    public void setIsText(boolean isText) {
        this.isText = isText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String year = date.substring(2,6);
        String month = date.substring(6,8);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy", myDateFormatSymbols);
        this.date = format.format(cal.getTime());
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"};
        }

    };
}










