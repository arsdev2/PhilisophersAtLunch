import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int countOfPhilosophers = 5;

        System.setOut(new PrintStream(System.out){
            @Override
            public void println(String string) {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                super.println("[" + simpleDateFormat.format(date) + "] " + string);
            }
        });

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
