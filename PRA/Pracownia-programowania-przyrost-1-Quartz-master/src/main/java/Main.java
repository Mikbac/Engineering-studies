import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Scanner;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {

    public static void main(String[] args) throws Exception {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        {
            String wyraz = "";
            int numer = 0;
            Question a2 = new Question();
            boolean dzialanie;

            /*EXAMPLE - kazda linijka wpisywana osobno*/
/*
3
test:  zadanie1 SELECT * FROM * WHERE * ORDER BY **
 */
/*
2
zadanie2 SELECT *123 FROM *123 123 123 13 WHERE *123 ORDER BY **
 */
/*
1
zadanie3213 zadanie2 SELECT *123 FROM *123 123 123 13 WHERE *123 ORDER BY **
 */


            JobDetail job2 = newJob(EndOfClass.class)
                    .withIdentity("job2", "group1")
                    .build();


            JobDetail job3 = newJob(Write.class)
                    .withIdentity("job3", "group1")
                    .build();

            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startAt(dateOf(8, 15, 0))
                    .withSchedule(cronSchedule("0 0/1 * ? * MON,TUE,WED,THU,FRI *"))
                    .endAt(dateOf(18, 45, 0))
                    .build();

            Trigger trigger3 = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0/30 * * ? * * *")
                    )
                    .build();


            scheduler.scheduleJob(job2, trigger2);
            scheduler.scheduleJob(job3, trigger3);

            scheduler.start();


            dzialanie = true;

            while (dzialanie) {


                Scanner in = new Scanner(System.in);

                Scanner odczyt = new Scanner(System.in);


                numer = in.nextInt();

                wyraz = odczyt.nextLine();


                if (wyraz.equals("exit")) {
                    dzialanie = false;
                }


                a2.setNumer(numer);
                a2.setTekst(wyraz);


                if ((a2.test()) && (dzialanie)) {
                    System.out.println("Question zostało zaakceptowane!");
                    System.out.println(a2.getNumer());
                    a2.dodanie();
                } else {
                    System.out.println("Question nie zostało zaakceptowane!");
                    System.out.println(a2.getNumer());

                }

            }

            scheduler.shutdown();

        }


    }

}
