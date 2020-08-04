/**
 * The following operations on a string are defined:
 *      Left Shift: A single circular rotation of the string in which the first character becomes the last character and all other characters are shifted
 *      one index to the left. For example, abcde becomes bcdea after one left shift and cdeab after two left shifts.
 *      Right Shift: A single circular rotation of the string in which the last character becomes the first character and all other characters are shifted
 *      one index to the right. For example, abcde becomes eabcd after one right shift and deabc after two right shifts.
 */
public class ShiftingStrings {
    public static String shiftString(String s, int leftShift, int rightShift){
        s = leftrotate(s, leftShift%s.length());
        s = rightrotate(s, rightShift%s.length());
        return s;
    }
    static String leftrotate(String str, int d)
    {
        String ans = str.substring(d) + str.substring(0, d);
        return ans;
    }

    // function that rotates s towards right by d
    static String rightrotate(String str, int d)
    {
        return leftrotate(str, str.length() - d);
    }
}
