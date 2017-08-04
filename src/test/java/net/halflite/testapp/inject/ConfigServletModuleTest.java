package net.halflite.testapp.inject;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.junit4.GuiceBerryRule;

import net.halflite.testapp.DefaultGuiceBerryEnv;

public class ConfigServletModuleTest {

	@Rule
	public final GuiceBerryRule rule = new GuiceBerryRule(DefaultGuiceBerryEnv.class);

	@Inject
	@Named("TZ")
	private String tz;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertThat(tz).isNotEmpty();
	}

}
