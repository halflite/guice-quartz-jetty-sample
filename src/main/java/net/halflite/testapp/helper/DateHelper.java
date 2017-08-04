package net.halflite.testapp.helper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Date/Time Helper
 *
 * @author halflite
 *
 */
@Singleton
public class DateHelper {

	/** TimeZone */
	private final ZoneId zone;

	/**
	 * @return now
	 */
	public Instant now() {
		return ZonedDateTime.now(zone).toInstant();
	}

	@Inject
	public DateHelper(@Named("TZ") String tz) {
		this.zone = ZoneId.of(tz);
	}

}
