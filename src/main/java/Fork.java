import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private final int numOfFork;

    public final ReentrantLock locker = new ReentrantLock(true);

    public Fork(int numOfFork){
        this.numOfFork = numOfFork;
    }

    public int getNumOfFork() {
        return numOfFork;
    }

    @Override
    public String toString() {
        return "Fork{" +
                "numOfFork=" + numOfFork +
                '}';
    }
}
