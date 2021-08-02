
public class Waiter {

    private final Fork[] forks;

    private final int n;

    public Waiter(int n) {
        this.n = n;
        forks = new Fork[n];
        for(int i = 0;i<n;i++){
            forks[i] = new Fork(i);
        }
    }

    public Fork[] askForForks(int numOfPhilosopher) throws InterruptedException {
        if(numOfPhilosopher < 0 || numOfPhilosopher >= n){
            throw new IllegalArgumentException("N should be positive or forks count is not matching with philosophers count");
        }
        int[] indexes = getForksByPhilosopherNum(numOfPhilosopher);

        for (int index : indexes) {
            Fork fork = forks[index];
            synchronized (fork.locker) {
                if (fork.isUsingRightNow) {
                    fork.locker.wait();
                }else{
                    fork.isUsingRightNow = true;
                }
            }
        }

        return new Fork[]{forks[indexes[0]], forks[indexes[1]]};
    }

    public void giveForksBack(int numOfPhilosopher) {
        int[] indexes = getForksByPhilosopherNum(numOfPhilosopher);
        for (int index : indexes) {
            Fork fork = forks[index];
            synchronized (fork.locker) {
                fork.isUsingRightNow = false;
                fork.locker.notify();
            }
        }
    }

    private synchronized int[] getForksByPhilosopherNum(int numOfPhilosopher) {
        int leftIndex = numOfPhilosopher;
        int rightIndex = numOfPhilosopher + 1 >= n ? 0 : leftIndex + 1;
        return new int[]{leftIndex, rightIndex};
    }
}
