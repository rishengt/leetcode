package Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {


    public static void main(String[] args) throws ParseException {
        Date now = new Date();
        System.out.println(now);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat lky = new SimpleDateFormat("dd-MMM-yyy hh.mm.ss aa");/** aa就是加am pm的意思，hh是12小时，HH是24小时*/
        Date kk = df.parse("2021-01-23 23:05:12.0");

//        Date cnm = DateFormat.getInstance().parse(fk);
//        System.out.println(cnm);
        Date wtf = new Date(1614139643000L);
        String fk = lky.format(wtf);
        System.out.println(wtf);
        System.out.println(fk);
    }
}
