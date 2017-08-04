package net.halflite.testapp.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

public class DateHelperTest {

	private final static String TZ = "Asia/Tokyo";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		DateHelper dateHelper = new DateHelper(TZ);

		assertThat(dateHelper).isNotNull();

		Instant now = dateHelper.now();

		assertThat(now).isNotNull();
	}

}
