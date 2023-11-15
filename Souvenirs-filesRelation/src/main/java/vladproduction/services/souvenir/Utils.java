package vladproduction.services.souvenir;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
    public static String createStringFromDate(Date date){
        return FORMATTER.format(date);
    }

    public static Date createDateFromString(String str){
        try {
            return FORMATTER.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
