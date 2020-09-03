public class FibonacciNumber {
    public int fib(int N){
        if(N<=1){
            return N;
        }
        return fib(N-1) + fib(N-2);
    }

    public int fib2(int N){
        if(N<=1){
            return N;
        }
        if(N == 2){
            return 1;
        }
        int current = 0;
        int pre1 = 1;
        int pre2 = 1;
        for(int i = 3; i<=N; i++){
            current = pre1+pre2;
            pre2 = pre1;
            pre1 = current;
        }
        return current;
    }
}
