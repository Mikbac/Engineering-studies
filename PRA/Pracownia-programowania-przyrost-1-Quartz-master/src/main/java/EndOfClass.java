import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
public class EndOfClass implements org.quartz.Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        {
            Time.podajCzasDoKonca();
        }
    }
}