package SingletonDP;

class Singleton {

    private static volatile Singleton instance;
    // volatile keyword is used to ensure that the instance variable is not cached and all threads see the most up-to-date value of the instance variable, preventing the reordering of instructions and ensuring that the instance is fully initialized before any thread can access it.

    private Singleton() {
        // Private constructor to prevent instantiation
        System.out.println("Instance created by: " + Thread.currentThread().getName()); // to log which thread creates the instance
    }

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class) {
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}



public class CorrectSingletonDP{
    public static void main(String[] args) {

        Runnable task = () -> {
          Singleton.getInstance();
        };

        for(int i = 0; i < 10000; i++){
            Thread thread = new Thread(task, "Thread-" + i);
            thread.start();
        }
    }
}
