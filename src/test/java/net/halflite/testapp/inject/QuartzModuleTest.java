package net.halflite.testapp.inject;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.quartz.Scheduler;

import com.google.guiceberry.junit4.GuiceBerryRule;

import net.halflite.testapp.DefaultGuiceBerryEnv;
import net.halflite.testapp.inject.QuartzModule.GroupNameType;
import net.halflite.testapp.inject.QuartzModule.JobElement;
import net.halflite.testapp.inject.QuartzModule.JobType;
import net.halflite.testapp.job.DefaultJob;

public class QuartzModuleTest {

	@Rule
	public final GuiceBerryRule rule = new GuiceBerryRule(DefaultGuiceBerryEnv.class);

	@Inject
	private Scheduler scheduler;

	private static final String SCHEDULE = "1 * * * * ?";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testScheduler() {
		assertThat(scheduler).isNotNull();
	}

	@Test
	public void testJobElementToString() {
		JobElement jobElement = new JobElement(DefaultJob.class, JobType.DEFAULT, SCHEDULE);
		String string = jobElement.toString();

		assertThat(string).isEqualTo("JobElement{jobClass=class net.halflite.testapp.job.DefaultJob,"
				+ " jobType=DEFAULT,"
				+ " cronSchedule=1 * * * * ?}");
	}

	@Test
	public void testJobGroupTypeValuesOf() {
		JobType jobGroupType = JobType.valueOf("SECONDARY");

		assertThat(jobGroupType).isEqualTo(JobType.SECONDARY);
	}

	@Test
	public void testJobGroupTypeValuess() {
		JobType[] values = JobType.values();

		assertThat(values.length).isNotEqualTo(0);
	}

	@Test
	public void testGroupNameTypeValueOf() {
		GroupNameType groupNameType = GroupNameType.valueOf("JOB");

		assertThat(groupNameType).isEqualTo(GroupNameType.JOB);
	}

	@Test
	public void testGroupNameTypeValus() {
		GroupNameType[] values = GroupNameType.values();

		assertThat(values.length).isNotEqualTo(0);
	}

}
