package LocalDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class format {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        String kk = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        System.out.println(kk);
        LocalDate fk = LocalDate.of(2021,6,2);
        System.out.println(fk);
    }
}
