package net.halflite.testapp.inject;

import static com.google.inject.name.Names.bindProperties;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;

import net.halflite.testapp.util.EnvUtils;

/**
 * Environment DI Module
 *
 * @author halflite
 *
 */
public class EnvModule extends AbstractModule {

	/** DI Element Key */
	private final static Collection<String> BIND_KEYS = ImmutableSet.<String> of("TZ",
			"QUARTZ_SCHEDULE_DEFAULT",
			"QUARTZ_SCHEDULE_SCONDARY");

	@Override
	protected void configure() {
		bindProperties(binder(), EnvUtils.properties(BIND_KEYS));
	}

}
