import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Waiter {

    private final Fork[] forks;

    private final int n;

    private final Logger logger = LoggerFactory.getLogger(Waiter.class);

    public Waiter(int n) {
        this.n = n;
        forks = new Fork[n];
        for(int i = 0;i<n;i++){
            forks[i] = new Fork(i);
        }
    }

    public Fork[] askForForks(int numOfPhilosopher){
        if(numOfPhilosopher < 0 || numOfPhilosopher >= n){
            throw new IllegalArgumentException("N should be positive or forks count is not matching with philosophers count");
        }
        int[] indexes = getForksByPhilosopherNum(numOfPhilosopher);

        for (int index : indexes) {
            Fork fork = forks[index];
            try {
//                while(!fork.locker.isLocked()){
                    fork.locker.lock();
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new Fork[]{forks[indexes[0]], forks[indexes[1]]};
    }

    public void giveForksBack(int numOfPhilosopher) {
        int[] indexes = getForksByPhilosopherNum(numOfPhilosopher);
        for (int index : indexes) {
            Fork fork = forks[index];
            try {
                boolean isLocked = fork.locker.isLocked();
                logger.info(String.format("Num of ph - %d Num of fork %d Monitor locked %s",
                        numOfPhilosopher,
                        index,
                        String.valueOf(isLocked)));

                if(isLocked){
                    fork.locker.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int[] getForksByPhilosopherNum(int numOfPhilosopher) {
        int leftIndex = numOfPhilosopher;
        int rightIndex = numOfPhilosopher + 1 >= n ? 0 : leftIndex + 1;
        return new int[]{leftIndex, rightIndex};
    }
}
