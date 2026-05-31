package SingletonDP;

class BrokenSingleton {

    private static BrokenSingleton instance;
    // This is broken singleton because when an object is created its not an atomic process.
    // it involves 3 steps memory allocation, object initialization, and reference assignment.
    // so compiler sometimes reorder the step 2 and 3 to optmize the performance, so when the thread 1 is creating the instance and it has completed step 1 and step 3 but not step 2, 
    // then thread 2 can access the instance and it will get a reference to an uninitialized (or partially initialized) object which can lead to unexpected behavior.
    // To fix this issue we can use volatile keyword to ensure that the instance variable is not cached and all threads see the most up-to-date value of the instance variable, preventing the reordering of instructions and ensuring that the instance is fully initialized before any thread can access it.

    private BrokenSingleton() {
        // Private constructor to prevent instantiation
        System.out.println("Instance created by: " + Thread.currentThread().getName()); // to log which thread creates the instance
    }

    public static BrokenSingleton getInstance(){
        if(instance == null){
            synchronized (BrokenSingleton.class) {
                if(instance == null){
                    instance = new BrokenSingleton();
                }
            }
        }
        return instance;
    }
}



public class BrokenSingletonDP{
    public static void main(String[] args) {

        Runnable task = () -> {
          BrokenSingleton.getInstance();
        };

        for(int i = 0; i < 10000; i++){
            Thread thread = new Thread(task, "Thread-" + i);
            thread.start();
        }
    }
}
