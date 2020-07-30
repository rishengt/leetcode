/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(new MultiplyStrings().multiplyII("123456789","987654321"));
    }
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /************ 传说中的补0⃣️大法， wang神万岁！！！！，有时候还是不要乱用StringBuilder的好，reverse来reverse去容易出错*/
    public String multiplyII(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int index = 0; //记录当前是哪一位，便于后边补 0
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0; //保存进位
            String ans_part = ""; //直接用字符串保存每位乘出来的数
            int m = num2.charAt(i) - '0';
            //乘上每一位
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n = num1.charAt(j) - '0';
                int mul = m * n + carry;
                ans_part = mul % 10 + "" + ans_part;
                carry = mul / 10;
            }
            if (carry > 0) {
                ans_part = carry + "" + ans_part;
            }
            //补 0
            for (int k = 0; k < index; k++) {
                ans_part = ans_part + "0";
            }
            index++;
            //和之前的结果相加
            ans = sumString(ans, ans_part);
        }
        return ans;
    }
    //大数相加
    private String sumString(String num1, String num2) {
        int carry = 0;
        int num1_index = num1.length() - 1;
        int num2_index = num2.length() - 1;
        String ans = "";
        while (num1_index >= 0 || num2_index >= 0) {
            int n1 = num1_index >= 0 ? num1.charAt(num1_index) - '0' : 0;
            int n2 = num2_index >= 0 ? num2.charAt(num2_index) - '0' : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            ans = sum % 10 + "" + ans;
            num1_index--;
            num2_index--;
        }
        if (carry > 0) {
            ans = carry + "" + ans;
        }
        return ans;
    }
}
