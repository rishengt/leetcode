/**
 * Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.
 *
 * The operation of drinking a full water bottle turns it into an empty bottle.
 *
 * Return the maximum number of water bottles you can drink.
 *
 *
 */
public class WaterBottles {
    public static void main(String[] args) {
        System.out.println(new WaterBottles().numWaterBottles(15,4));
        System.out.println(new WaterBottles().numWaterBottles(5,5));
    }
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = 0;
        ans+=numBottles;
        return helper(ans,numBottles/numExchange,numBottles,numExchange);
    }
    public int helper(int ans,int can, int total, int numExchange){
        if(total<numExchange){
            return ans;
        }
        else{
            int canDrink = total/numExchange;
            int left = total%numExchange;
            total = canDrink+left;
            return helper(ans+canDrink, canDrink,total,numExchange);
        }
    }
}
