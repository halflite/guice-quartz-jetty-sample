package net.halflite.testapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.halflite.testapp.servlet.InitializeServletContextListener;
import net.halflite.testapp.util.EnvUtils;

/**
 * Main App Class
 *
 * @author halflite
 *
 */
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {

		int port = EnvUtils.getInt("PORT");
		Server server = new Server(port);

		ServletContextHandler context = new ServletContextHandler(server, "/*", ServletContextHandler.NO_SESSIONS);
		context.addEventListener(new InitializeServletContextListener());

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
		} finally {
			server.destroy();
		}
	}
}