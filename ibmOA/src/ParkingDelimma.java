import java.util.Arrays;

/**
 * There are many cars parked in the parking lot. The parking is a straight, very long line and has a parking slot for every single meter.
 * There are n cars parked currently and you want to cover them from the rain by building a roof. The requirement is that at least k cars are covered by the roof.
 * What's the minimum length of the roof that would cover k cars>
 *
 * Consider the following example. There are 4 cars parked at slots 2, 10, 8 and 17 respectively and k = 3. Then, you can build a roof of length 9 covering
 * all parking slots from the 2nd one to the 10th one, so covering 3 cars at slots 2, 10 and 8. There is no shorter roof that can cover 3 cars,
 * so the answer is 9.
 *
 * Function Description
 *
 * Complete the function carParkingRoof in the editor below. The function has to return a single integer denoting the minimum length of a roof that can
 * cover k cars.
 *
 * The function has the following parameter(s):
 *    cars: integer array of length n denoting the parking slots where cars are parked
 *    k: integer denoting the number of cars that have to be covers by the roof
 *
 * Constraints
 *   1 <= n <= 10^5
 *   1 <= k <= n
 *   1 <= cars[i] <= 10^14
 *   All slots taken by cars are unique
 *
 *
 * Example
 *   input array = [2,10,8,17], k = 3
 *   output 9
 *
 *   explain : There are 4 cars parked at slots 2, 10, 8 and 17 respectively and k = 3. Then, you can build a roof of length 9 covering
 *      all parking slots from the 2nd one to the 10th one, so covering 3 cars at slots 2, 10 and 8. There is no shorter roof that can cover 3 cars,
 *      so the answer is 9.
 */
public class ParkingDelimma {
    public static void main(String[] args) {
        System.out.println(new ParkingDelimma().carParkingRoof(new int[]{2,10,8,17,18,19}, 3));
    }
    public int carParkingRoof(int[] cars, int k){
        if(cars.length == 0 || k == 0) return 0;
        Arrays.sort(cars);
        int number = Integer.MAX_VALUE;
        for(int i = 0; i + k <= cars.length; i++){
            number = Math.min((cars[i+k-1] - cars[i]),number);
        }
        return number + 1;
    }
}
