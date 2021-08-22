package Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class formatForDate {
    public static void main(String[] args) {
        System.out.println(new Date());
        LocalDate today = LocalDate.now();
        System.out.println(today);
        System.out.println(today.minusDays(7));
        DateFormat df = new SimpleDateFormat("dd-MMM-yy");
        System.out.println(df.format(new Date())); // DateFormat can only format Date, not LocalDate
    }
}
