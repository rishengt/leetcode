/**一些数字构成一个环，比如
 * 5-3-4-2-3-(5 in head)
 * 这个环可以表示为(5,3),(3,4),(4,2),(2,3),(3,5). 环中每个pair是distinct得
 * 现在这些pair被随机shuffle， start，end可能被对调，比如shuffle后得到
 * (3,4),(5,3),(2,4),(3,5),(3,2)
 * 求重构这个环
 *
 * Input:[(3,4),(5,3),(2,4),(3,5),(3,2)]
 * Output:[5,3,4,2,3]
 * @param numbers
 */
const findNeighbor = (arr) => {
    let neightborMap = {};
    for (let i = 0; i < arr.length; i++) {
        for (let j = 0; j < arr[i].length; j++) {
            if (neightborMap[arr[i][j]]) {
                neightborMap[arr[i][j]].push(arr[i][j + 1 >= 2 ? 0 : 1]);
            } else {
                neightborMap[arr[i][j]] = [arr[i][j + 1 >= 2 ? 0 : 1]];
            }
        }
    }

    console.log("neightborMap", neightborMap);
    let ans = [];
    //finding the shortest path 1 which will be the head or tail
    let headValue = Object.values(neightborMap).filter((x) => x.length < 2)[1];
    let headKey = Object.keys(neightborMap).find(
        (key) => neightborMap[key] === headValue
    );
    let count = Object.keys(neightborMap).length;
    ans.push(headKey, ...(headValue + ""));

    while (count > 0) {
        headValue = neightborMap[[...ans].pop()];
        for (let i = 0; i < headValue.length; i++) {
            if (!ans.includes(headValue[i] + "")) {
                ans.push(headValue[i] + "");
            }
        }

        count--;
    }
    console.log(ans);
};

const arr = [
    [5, 4],
    [2, 4],
    [1, 3],
    [1, 5],
];
findNeighbor(arr);