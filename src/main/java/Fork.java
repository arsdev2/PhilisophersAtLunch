import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Fork {


    private final int numOfFork;

    public final ReentrantLock locker = new ReentrantLock(true);

    public Boolean isUsingRightNow = false;

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
