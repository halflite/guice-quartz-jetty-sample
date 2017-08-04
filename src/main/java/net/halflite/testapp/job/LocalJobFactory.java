package net.halflite.testapp.job;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import com.google.inject.Injector;

@Singleton
public class LocalJobFactory implements JobFactory {

	/** Guice DI Injector */
	private final Injector injector;

	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
		JobDetail jobDetail = bundle.getJobDetail();
		Class<? extends Job> jobClass = jobDetail.getJobClass();
		return injector.getInstance(jobClass);
	}

	@Inject
	public LocalJobFactory(final Injector injector) {
		this.injector = injector;
	}

}
