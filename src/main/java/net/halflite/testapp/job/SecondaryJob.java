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
 * Secondary Job Class
 *
 * @author halflite
 *
 */
@Singleton
public class SecondaryJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecondaryJob.class);

	private final DateHelper dateHelper;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.debug("{}, secondary job done.", dateHelper.now());
	}

	@Inject
	public SecondaryJob(DateHelper dateHelper) {
		this.dateHelper = dateHelper;
	}

}
