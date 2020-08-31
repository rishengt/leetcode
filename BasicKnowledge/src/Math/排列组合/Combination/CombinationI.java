package Math.排列组合.Combination;

/**
 *  求从a个数里面选b个数进行组合的方案数， note： a个不同的数
 */
public class CombinationI {
    public int com(int a, int b){
        if(a == 0 || a == b){
            return 1;
        }else{     /**选一个a里面选一个符合b的情况加a里面选一个但不符合b的情况 = 总情况*/
            return com(a-1,b-1)+com(a-1,b);
        }
    }
}
