import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Philosopher extends Thread{

    private final Waiter waiter;
    private final int numOfPhilosopher;
    private final SecureRandom random;
    private static final int millisToEat = 5000;
    private final Logger logger = LoggerFactory.getLogger(Philosopher.class.getName());

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
                logger.info("Philosopher #" + numOfPhilosopher + " is thinking right now for " + millisToThink);
                Thread.sleep(millisToThink);

                logger.info("Philosopher #" + numOfPhilosopher + " waiting for forks");
                Fork[] forks = waiter.askForForks(numOfPhilosopher);
                logger.info("Philosopher #" + numOfPhilosopher + " started eating with forks "
                            + forks[0].toString() + " and " + forks[1].toString());
                logger.info("Philosopher #" + numOfPhilosopher + " is eating right now for " + millisToEat);
                Thread.sleep(millisToEat);
                waiter.giveForksBack(numOfPhilosopher);
                logger.info("Philosopher #" + numOfPhilosopher + " finished eating");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
