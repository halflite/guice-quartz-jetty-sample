package net.halflite.testapp.servlet;

import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;

import net.halflite.testapp.inject.ConfigServletModule;

/**
 * Initialize Context Listener
 *
 * @author halflite
 *
 */
@WebListener
public class InitializeServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(Stage.PRODUCTION, new ConfigServletModule());
	}

}
