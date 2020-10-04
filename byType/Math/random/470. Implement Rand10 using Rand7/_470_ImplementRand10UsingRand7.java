/**
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
 *
 * Do NOT use system's Math.random().
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: [7]
 * Example 2:
 *
 * Input: 2
 * Output: [8,4]
 * Example 3:
 *
 * Input: 3
 * Output: [8,1,10]
 *
 *
 * Note:
 *
 * rand7 is predefined.
 * Each testcase has one argument: n, the number of times that rand10 is called.
 *
 *
 * Follow up:
 *
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 */
public class _470_ImplementRand10UsingRand7 {
    public int rand7(){
        /**你把它变成int的话最大数要先加一，不然7永远出不来*/
        return (int)(Math.random()*(8-1)+1);
    }
    public int rand10(){
        int a,b,c;
        do{
            a = rand7();
            b = rand7();
            c = a+(b-1)*7;
        }while(c>40);
        return 1+(c-1)%10;
    }
}
