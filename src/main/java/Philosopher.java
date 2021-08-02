import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Philosopher extends Thread{

    private final Waiter waiter;
    private final int numOfPhilosopher;
    private final SecureRandom random;
    private static final int millisToEat = 5000;


    public static List<Philosopher> getPhilosophers(Waiter waiter, int n){
        List<Philosopher> philosopherList = new ArrayList<>();

        for(int i = 0;i<n;i++){
            Philosopher philosopher = new Philosopher(waiter, i);
            philosopherList.add(philosopher);
        }

        return philosopherList;
    }

    private Philosopher(Waiter waiter, int numOfPhilosopher) {
        super("Philosopher #" + numOfPhilosopher);
        this.waiter = waiter;
        this.numOfPhilosopher = numOfPhilosopher;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("No such algorithm");
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                int millisToThink = random.nextInt(4000) + 1000;
                System.out.println("Philosopher #" + numOfPhilosopher + " is thinking right now for " + millisToThink);
                Thread.sleep(millisToThink);

                System.out.println("Philosopher #" + numOfPhilosopher + " waiting for forks");
                Fork[] forks = waiter.askForForks(numOfPhilosopher);
                System.out.println("Philosopher #" + numOfPhilosopher + " started eating with forks "
                            + forks[0].toString() + " and " + forks[1].toString());
                System.out.println("Philosopher #" + numOfPhilosopher + " is eating right now for " + millisToEat);
                Thread.sleep(millisToEat);
                waiter.giveForksBack(numOfPhilosopher);
                System.out.println("Philosopher #" + numOfPhilosopher + " finished eating");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
