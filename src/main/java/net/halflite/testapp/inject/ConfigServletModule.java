package net.halflite.testapp.inject;

import com.google.inject.servlet.ServletModule;

/**
 * Config Servlet DI Module
 *
 * @author halflite
 *
 */
public class ConfigServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		super.configureServlets();
		install(new AppModule());
	}
}
