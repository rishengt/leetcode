import java.util.HashMap;

/**
 * Given a date string in the form Day Month Year, where:
 *
 * Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
 * Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
 * Year is in the range [1900, 2100].
 * Convert the date string to the Date.format YYYY-MM-DD, where:
 *
 * YYYY denotes the 4 digit year.
 * MM denotes the 2 digit month.
 * DD denotes the 2 digit day.
 *
 *
 * Example 1:
 *
 * Input: date = "20th Oct 2052"
 * Output: "2052-10-20"
 * Example 2:
 *
 * Input: date = "6th Jun 1933"
 * Output: "1933-06-06"
 * Example 3:
 *
 * Input: date = "26th May 1960"
 * Output: "1960-05-26"
 */
public class ReformatDate {
    public static void main(String[] args) {
        System.out.println(new ReformatDate().reformatDate("26th May 1960"));
    }
    public String reformatDate(String date) {
        String[] array = date.split(" ");
        HashMap<String,String> map = new HashMap<>();
        map.put("1st", "01");
        map.put("2nd","02");
        map.put("3rd", "03");
        map.put("4th", "04");
        map.put("5th","05");
        map.put("6th","06");
        map.put("7th","07");
        map.put("8th","08");
        map.put("9th","09");
        map.put("10th","10");
        map.put("11th","11");
        map.put("12th","12");
        map.put("13th","13");
        map.put("14th","14");
        map.put("15th","15");
        map.put("16th","16");
        map.put("17th","17");
        map.put("18th","18");
        map.put("19th","19");
        map.put("20th","20");
        map.put("21st","21");
        map.put("22nd","22");
        map.put("23rd","23");
        map.put("24th","24");
        map.put("25th","25");
        map.put("26th","26");
        map.put("27th","27");
        map.put("28th","28");
        map.put("29th","29");
        map.put("30th","30");
        map.put("31st","31");
        map.put("Jan","01");
        map.put("Feb","02");
        map.put("Mar","03");
        map.put("Apr","04");
        map.put("May","05");
        map.put("Jun","06");
        map.put("Jul","07");
        map.put("Aug","08");
        map.put("Sep","09");
        map.put("Oct","10");
        map.put("Nov","11");
        map.put("Dec","12");
        StringBuilder sb = new StringBuilder();
        for(int i = array.length-1; i>= 0; i--){
            if(i==array.length-1) {sb.append(array[i]); sb.append("-");}
            else if(i!=0){
                sb.append(map.get(array[i]));
                sb.append("-");
            }else{
                sb.append(map.get(array[i]));
            }
        }
        return String.valueOf(sb);
    }
}
