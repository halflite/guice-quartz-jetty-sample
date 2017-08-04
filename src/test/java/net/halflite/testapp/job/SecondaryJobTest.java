package net.halflite.testapp.job;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.guiceberry.junit4.GuiceBerryRule;

import net.halflite.testapp.DefaultGuiceBerryEnv;

public class SecondaryJobTest {

	@Rule
	public final GuiceBerryRule rule = new GuiceBerryRule(DefaultGuiceBerryEnv.class);

	@Inject
	private SecondaryJob secondaryJob;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws JobExecutionException {
		assertThat(secondaryJob).isNotNull();

		JobExecutionContext context = mock(JobExecutionContext.class);

		secondaryJob.execute(context);
	}

}
