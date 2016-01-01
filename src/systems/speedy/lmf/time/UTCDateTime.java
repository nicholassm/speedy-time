package systems.speedy.lmf.time;

import static systems.speedy.lmf.time.UnixTimestampConverter.dayOfMonthOf;
import static systems.speedy.lmf.time.UnixTimestampConverter.hourOf;
import static systems.speedy.lmf.time.UnixTimestampConverter.milliSecondsOf;
import static systems.speedy.lmf.time.UnixTimestampConverter.minutesOf;
import static systems.speedy.lmf.time.UnixTimestampConverter.monthOf;
import static systems.speedy.lmf.time.UnixTimestampConverter.secondsOf;
import static systems.speedy.lmf.time.UnixTimestampConverter.yearOf;

/**
 * Immutable representation of a datetime instance.
 * <b>Note:</b> Only Unix Timestamps after 00:00:00 January 1st 1970 are supported.
 */
public class UTCDateTime {
	private final long millisSinceTheEpoch;

	/**
	 * Creates a new UTCDateTime which is a simple wrapper around the Unix Timestamp.
	 * @param millisSinceTheEpoch The Unix Timestamp in milliseconds since the Epoch (00:00:00 at January 1st 1970).<br/>
	 *        Note: Only positive timestamps are supported.
	 */
	public UTCDateTime(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		this.millisSinceTheEpoch = millisSinceTheEpoch;
	}

	/** @return Returns the current time as a UTCDateTime.*/
	public static UTCDateTime now() {
		return new UTCDateTime(System.currentTimeMillis());
	}

	/**
	 * @return The year of this UTCDateTime.
	 * @see UnixTimestampConverter#yearOf
	 */
	public int getYear() {
		return yearOf(millisSinceTheEpoch);
	}

	/**
	 * @return The month of this UTCDateTime (1-12).
	 * @see UnixTimestampConverter#monthOf
	 */
	public int getMonth() {
		return monthOf(millisSinceTheEpoch);
	}

	/**
	 * @return The day of the month of this UTCDateTime (1-31).
	 * @see UnixTimestampConverter#dayOfMonthOf
	 */
	public int getDayOfMonth() {
		return dayOfMonthOf(millisSinceTheEpoch);
	}

	/**
	 * @return The hour of the day of this UTCDateTime (0-23).
	 * @see UnixTimestampConverter#hourOf
	 */
	public int getHour() {
		return hourOf(millisSinceTheEpoch);
	}

	/**
	 * @return The minutes of the hour of this UTCDateTime (0-59).
	 * @see UnixTimestampConverter#minutesOf
	 */
	public int getMinutes() {
		return minutesOf(millisSinceTheEpoch);
	}

	/**
	 * @return The seconds of the minute of this UTCDateTime (0-59).
	 * @see UnixTimestampConverter#secondsOf
	 */
	public int getSeconds() {
		return secondsOf(millisSinceTheEpoch);
	}

	/**
	 * @return The milliseconds of the second of this UTCDateTime (0-999).
	 * @see UnixTimestampConverter#milliSecondsOf
	 */
	public int getMilliSeconds() {
		return milliSecondsOf(millisSinceTheEpoch);
	}

	/** @return The milli seconds since the Epoch for this UTCDateTime.*/
	public long asMillisSinceTheEpoch() {
		return millisSinceTheEpoch;
	}

	@Override public int hashCode() {
		return 31 + (int) (millisSinceTheEpoch ^ (millisSinceTheEpoch >>> 32));
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		UTCDateTime other = (UTCDateTime) obj;
		if (millisSinceTheEpoch != other.millisSinceTheEpoch) return false;
		return true;
	}
}
