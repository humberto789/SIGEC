package br.com.SIGEC.web.mbean;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class AgendarEmailTrigger {

	public void tarefa() {

		SchedulerFactory shedFact = new StdSchedulerFactory();
		try {
			Scheduler scheduler = shedFact.getScheduler();
			scheduler.start();
			JobDetail job = JobBuilder.newJob(AgendarEmailJob.class)
					.withIdentity("agendarEmailJOB", "grupo01").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("AgendarEmailTRIGGER", "grupo01")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * *")).build();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
