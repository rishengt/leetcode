public class running {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<20; i++)
                    System.out.println("我爱黎明");
            }
        };
        Thread t = new Thread(r1);
        t.start();
        new running().method();
    }

    public void method(){
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<20; i++)
                    System.out.println("我爱张学友");
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<20; i++)
                    System.out.println("我爱lky");
            }
        };
        Thread t2 = new Thread(r2);
        t2.start();
        Thread t3 = new Thread(r3);
        t3.start();
    }
}
