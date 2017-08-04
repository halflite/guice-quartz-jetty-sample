package net.halflite.testapp.inject;

import com.google.inject.AbstractModule;

import net.halflite.testapp.helper.DateHelper;
import net.halflite.testapp.job.DefaultJob;
import net.halflite.testapp.job.SecondaryJob;

/**
 * Application Config
 *
 * @author halflite
 *
 */
public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new EnvModule());
		install(new QuartzModule());

		// bind jobs
		bind(DefaultJob.class);
		bind(SecondaryJob.class);

		// bind helper
		bind(DateHelper.class);
	}
}
