import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int countOfPhilosophers = 5;
        Waiter waiter = new Waiter(countOfPhilosophers);
        List<Philosopher> philosophers = Philosopher.getPhilosophers(waiter, countOfPhilosophers);
        for(Philosopher philosopher : philosophers){
            philosopher.start();
        }

        Philosopher firstPhilosopher = philosophers.get(0);
        if (firstPhilosopher != null) {
            firstPhilosopher.join();
        }
    }


}
