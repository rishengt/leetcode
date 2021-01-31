/**
 Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.



 Example 1:


 Input: n = 3
 Output: [[1,2,3],[8,9,4],[7,6,5]]
 Example 2:

 Input: n = 1
 Output: [[1]]


 Constraints:

 1 <= n <= 20
 **/
/**
 * @param {number} n
 * @return {number[][]}
 */
var generateMatrix = function(n) {
    let rightbound = n-1, leftbound = 0, upperbound = 0, lowerbound = n-1, direction = 1, number = new Array(n);
    for(let i = 0; i<n ; i++){
        number[i] = new Array(n);
    }
    let count = 1;
    while(count <= n*n) {
        if (direction === 1) {
            for (let i = leftbound; i <= rightbound; i++) {
                number[upperbound][i] = count;
                count++;
            }
            direction =(direction+1)%4;
//            rightbound++;
            upperbound++;
        }
        else if(direction === 2){
            for(let i = upperbound; i<=lowerbound; i++){
                number[i][rightbound] = count;
                count++;
            }
            direction =( direction+1)%4;
            rightbound--;
        }
        else if(direction == 3){
            for(let i = rightbound; i>=leftbound; i--){
                number[lowerbound][i] = count;
                count++;
            }
            direction =( direction+1)%4;
            lowerbound--;
        }
        else if(direction == 0){
            for(let i = lowerbound; i>=upperbound; i--){
                number[i][leftbound] = count;
                count++;
            }
            leftbound++;
            direction =( direction+1)%4;
        }
    }
    return number;
};

console.log(generateMatrix(1));