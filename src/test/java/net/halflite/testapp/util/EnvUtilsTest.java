package net.halflite.testapp.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

public class EnvUtilsTest {

	@Before
	public void setUp() throws Exception {
		System.setProperty("hoge", "fuga");
		System.setProperty("PORT", "8080");
	}

	@Test
	public void testConstructor() {
		try {
			Constructor<?>[] constructors = EnvUtils.class.getDeclaredConstructors();

			assertThat(constructors.length).isNotEqualTo(0);

			Constructor<?> defaultConstructor = constructors[0];
			assertThat(defaultConstructor.getParameterTypes().length).isEqualTo(0);
			assertThat(Modifier.isPrivate(defaultConstructor.getModifiers())).isTrue();

			defaultConstructor.setAccessible(true);
			Object instance = defaultConstructor.newInstance();
			assertThat(instance).isNotNull().isInstanceOf(EnvUtils.class);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetInt() {
		int value = EnvUtils.getInt("PORT");

		assertThat(value).isEqualTo(8080);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetIntIllegalArgumentException() {
		EnvUtils.getInt("hoge");

		fail();
	}

}
