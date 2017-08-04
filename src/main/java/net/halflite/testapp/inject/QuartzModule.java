package net.halflite.testapp.inject;

import javax.inject.Named;
import javax.inject.Singleton;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;

import com.google.common.base.MoreObjects;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.halflite.testapp.job.DefaultJob;
import net.halflite.testapp.job.LocalJobFactory;
import net.halflite.testapp.job.SecondaryJob;

/**
 * Quartz Config DI Module
 *
 * @author halflite
 *
 */
public class QuartzModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(JobFactory.class).to(LocalJobFactory.class);
		bind(SchedulerFactory.class).to(StdSchedulerFactory.class).in(Singleton.class);
	}

	@Provides
	@Singleton
	protected Scheduler provideScheduler(
			SchedulerFactory schedulerFactory,
			JobFactory jobFactory,
			@Named("JOB_ELEMENT_DEFAULT") JobElement defaultJobElement,
			@Named("JOB_ELEMENT_SCONDARY") JobElement secondaryJobElement) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		// register jobFactory
		scheduler.setJobFactory(jobFactory);

		// register jobs
		scheduler.scheduleJob(defaultJobElement.getJobDetail(), defaultJobElement.getCronTrigger());
		scheduler.scheduleJob(secondaryJobElement.getJobDetail(), secondaryJobElement.getCronTrigger());

		// fire scheduler
		scheduler.start();

		return scheduler;
	}

	@Provides
	@Singleton
	@Named("JOB_ELEMENT_DEFAULT")
	protected JobElement provideDefaultJobElement(@Named("QUARTZ_SCHEDULE_DEFAULT") String cronSchedule) {
		return new JobElement(DefaultJob.class, JobType.DEFAULT, cronSchedule);
	}

	@Provides
	@Singleton
	@Named("JOB_ELEMENT_SCONDARY")
	protected JobElement provideSecondaryJobElement(@Named("QUARTZ_SCHEDULE_SCONDARY") String cronSchedule) {
		return new JobElement(SecondaryJob.class, JobType.SECONDARY, cronSchedule);
	}

	/** Job Data Model */
	protected static class JobElement {
		private final Class<? extends Job> jobClass;
		private final JobType jobType;
		private final String cronSchedule;

		public JobElement(Class<? extends Job> jobClass, JobType jobType, String cronSchedule) {
			this.jobClass = jobClass;
			this.jobType = jobType;
			this.cronSchedule = cronSchedule;
		}

		public JobDetail getJobDetail() {
			return JobBuilder.newJob(this.jobClass)
					.withIdentity(this.jobType.getJobName())
					.build();
		}

		public CronTrigger getCronTrigger() {
			return TriggerBuilder.newTrigger()
					.withIdentity(this.jobType.getTriggerName())
					.withSchedule(CronScheduleBuilder.cronSchedule(this.cronSchedule))
					.build();
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.add("jobClass", this.jobClass)
					.add("jobType", this.jobType)
					.add("cronSchedule", this.cronSchedule)
					.toString();
		}
	}

	/** Job Group Type */
	protected static enum JobType {
		/** default Job */
		DEFAULT,
		/** secondary job */
		SECONDARY;

		public String getJobName() {
			return this.getName(GroupNameType.JOB);
		}

		public String getTriggerName() {
			return this.getName(GroupNameType.TRIGGER);
		}

		private String getName(GroupNameType type) {
			return this.name().toUpperCase() + "_" + type.name().toUpperCase();
		}

	}

	protected static enum GroupNameType {
		JOB, TRIGGER;
	}
}
