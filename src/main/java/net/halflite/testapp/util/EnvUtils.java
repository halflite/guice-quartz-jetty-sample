package net.halflite.testapp.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.Predicates;
import com.google.common.collect.Maps;

/**
 * Environment Variable Utils
 *
 * @author halflite
 */
public class EnvUtils {

	private EnvUtils() {
	}

	public static Map<String, String> properties(Collection<String> keySet) {
		return Maps.filterKeys(allProperties(), Predicates.in(keySet));
	}

	public static int getInt(final String key) {
		return Optional.of(allProperties())
				.map(props -> props.get(key))
				.map(Integer::valueOf)
				.orElseThrow(IllegalArgumentException::new);
	}

	private static Map<String, String> allProperties() {
		Map<String, String> properties = new HashMap<>();
		properties.putAll(System.getenv());
		properties.putAll(Maps.fromProperties(System.getProperties()));
		return properties;
	}

}
