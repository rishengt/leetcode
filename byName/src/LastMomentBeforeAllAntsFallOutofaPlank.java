/**
 * We have a wooden plank of the length n units. Some ants are walking on the plank, each ant moves with speed 1 unit per second.
 * Some of the ants move to the left, the other move to the right.
 *
 * When two ants moving in two different directions meet at some point, they change their directions and continue moving again.
 * Assume changing directions doesn't take any additional time.
 *
 * When an ant reaches one end of the plank at a time t, it falls out of the plank imediately.
 *
 * Given an integer n and two integer arrays left and right, the positions of the ants moving to the left and the right.
 * Return the moment when the last ant(s) fall out of the plank.
 */
public class LastMomentBeforeAllAntsFallOutofaPlank {
    public static void main(String[] args) {
        System.out.println(new LastMomentBeforeAllAntsFallOutofaPlank().getLastMoment(4, new int[]{4}, new int[]{1}));
    }

    /**
     * 细品，不管怎么碰头，最远距离的蚂蚁一定要走完才能掉下去，嘛当成是蚂蚁碰头的定式来记就好了。。。。
     * @param n
     * @param left
     * @param right
     * @return
     */
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;
        for (int x : left)
        {
            res = Math.max(res, x);
        }
        for (int x : right)
        {
            res = Math.max(res, n-x);
        }
        return res;
    }
}
