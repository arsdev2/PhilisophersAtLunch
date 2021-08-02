import java.util.concurrent.atomic.AtomicBoolean;

public class Fork {


    private final int numOfFork;

    public final Object locker = new Object();

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
