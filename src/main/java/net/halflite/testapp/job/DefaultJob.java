package net.halflite.testapp.job;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.halflite.testapp.helper.DateHelper;

/**
 * Default Job Class
 *
 * @author halflite
 *
 */
@Singleton
public class DefaultJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJob.class);

	private final DateHelper dateHelper;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.debug("{}, default job done.", dateHelper.now());
	}

	@Inject
	public DefaultJob(DateHelper dateHelper) {
		this.dateHelper = dateHelper;
	}

}
