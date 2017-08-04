package net.halflite.testapp;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;

import net.halflite.testapp.inject.ConfigServletModule;

public class DefaultGuiceBerryEnv extends AbstractModule {

	@Override
	protected void configure() {
		System.setProperty("PORT", "8080");
		System.setProperty("TZ", "Asia/Tokyo");
		System.setProperty("QUARTZ_SCHEDULE_DEFAULT", "0/10 * * * * ?");
		System.setProperty("QUARTZ_SCHEDULE_SCONDARY", "0 * * * * ?");

		install(new GuiceBerryModule());
		install(new ConfigServletModule());
	}

}
