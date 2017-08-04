package net.halflite.testapp.job;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;

import com.google.guiceberry.junit4.GuiceBerryRule;

import net.halflite.testapp.DefaultGuiceBerryEnv;

public class LocalJobFactoryTest {

	@Rule
	public final GuiceBerryRule rule = new GuiceBerryRule(DefaultGuiceBerryEnv.class);

	@Inject
	private LocalJobFactory localJobFactory;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNewJob() throws Exception {
		Scheduler scheduler = mock(Scheduler.class);

		TriggerFiredBundle bundle = mock(TriggerFiredBundle.class);
		JobDetail jobDetail = JobBuilder.newJob(DefaultJob.class).build();
		when(bundle.getJobDetail()).thenReturn(jobDetail);

		Job newJob = localJobFactory.newJob(bundle, scheduler);

		assertThat(newJob)
				.isNotNull()
				.isInstanceOf(DefaultJob.class);
	}

}
