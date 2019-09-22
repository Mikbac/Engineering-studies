import org.quartz.JobExecutionContext;

public class Write implements org.quartz.Job {

    public void execute(JobExecutionContext context){
        {

            WritingToAFile.zapis();

        }

    }

}