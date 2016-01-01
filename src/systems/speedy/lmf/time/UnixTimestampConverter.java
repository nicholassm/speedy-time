package systems.speedy.lmf.time;

public class UnixTimestampConverter {
	private static final int  DAYS_PER_CYCLE    = 146097;
	private static final long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);
	private static final long MILLIS_PER_SECOND = 1_000L;
	private static final long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * 60L;
	private static final long MILLIS_PER_HOUR   = MILLIS_PER_MINUTE * 60L;
	private static final long MILLIS_PER_DAY    = MILLIS_PER_HOUR * 24L;
	/**
	 * @param  millisSinceTheEpoch The Unix Timestamp to calculate the year from.<br/>
	 *         Note: Only positive timestamps are supported.
	 * @return The year of the Unix Timestamp.
	 */
	public static int yearOf(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		long marchBasedZeroDay = marchBasedZeroDayFrom(millisSinceTheEpoch);
		long yearEst           = yearEstimateFrom(marchBasedZeroDay);
		long doyEst            = dayOfYearEstimateFrom(marchBasedZeroDay, yearEst);
		int marchDoy0          = (int) doyEst;
		// Convert march-based values back to January-based.
		int marchMonth0        = marchMonth0From(marchDoy0);
		yearEst               += marchMonth0 / 10;

		return (int) yearEst;
	}

	/**
	 * @param  millisSinceTheEpoch The Unix Timestamp to calculate the month from.<br/>
	 *         Note: Only positive timestamps are supported.
	 * @return The month of the year of the Unix Timestamp (1-12).
	 */
	public static int monthOf(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		int marchDoy0   = marchBasedDoy0From(millisSinceTheEpoch);
		// Convert march-based values back to January-based.
		int marchMonth0 = marchMonth0From(marchDoy0);
		int month       = (marchMonth0 + 2) % 12 + 1;

		return month;
	}

	/**
	 * @param  millisSinceTheEpoch The Unix Timestamp to calculate the day of the month from.<br/>
	 *         Note: Only positive timestamps are supported.
	 * @return The day of the month of the Unix Timestamp (1-31).
	 */
	public static int dayOfMonthOf(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		int marchDoy0   = marchBasedDoy0From(millisSinceTheEpoch);
		// Convert march-based values back to January-based.
		int marchMonth0 = marchMonth0From(marchDoy0);
		int dom         = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1;

		return dom;
	}

	/**
	 * @param  millisSinceTheEpoch The Unix Timestamp to calculate the hour from.<br/>
	 *         Note: Only positive timestamps are supported.
	 * @return The hour of the day of the Unix Timestamp (0-23).
	 */
	public static int hourOf(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		return (int) ((millisSinceTheEpoch % MILLIS_PER_DAY) / MILLIS_PER_HOUR);
	}

	/**
	 * @param  millisSinceTheEpoch The Unix Timestamp to calculate the minutes from.<br/>
	 *         Note: Only positive timestamps are supported.
	 * @return The minutes of the hour of the Unix Timestamp (0-59).
	 */
	public static int minutesOf(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		return (int) ((millisSinceTheEpoch % MILLIS_PER_HOUR) / MILLIS_PER_MINUTE);
	}

	/**
	 * @param  millisSinceTheEpoch The Unix Timestamp to calculate the seconds from.<br/>
	 *         Note: Only positive timestamps are supported.
	 * @return The seconds of the minute of the Unix Timestamp (0-59).
	 */
	public static int secondsOf(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		return (int) ((millisSinceTheEpoch % MILLIS_PER_MINUTE) / MILLIS_PER_SECOND);
	}

	/**
	 * @param  millisSinceTheEpoch The Unix Timestamp to calculate the milliseconds from.<br/>
	 *         Note: Only positive timestamps are supported.
	 * @return The milliseconds of the second of the Unix Timestamp (0-999).
	 */
	public static int milliSecondsOf(long millisSinceTheEpoch) {
		assert millisSinceTheEpoch >= 0;

		return (int) (millisSinceTheEpoch % MILLIS_PER_SECOND);
	}

	private static int marchBasedDoy0From(long millisSinceTheEpoch) {
		long marchBasedZeroDay = marchBasedZeroDayFrom(millisSinceTheEpoch);
		long yearEst           = yearEstimateFrom(marchBasedZeroDay);
		long doyEst            = dayOfYearEstimateFrom(marchBasedZeroDay, yearEst);
		int marchDoy0          = (int) doyEst;
		return marchDoy0;
	}

	private static long marchBasedZeroDayFrom(long millisSinceTheEpoch) {
		long epochDay            = millisSinceTheEpoch / MILLIS_PER_DAY;
		long zeroDay             = epochDay + DAYS_0000_TO_1970;
		// Find the March-based year by adjusting to 0000-03-01 so leap day is at end of four year cycle.
		long marchedBasedZeroDay = zeroDay - 60;
		return marchedBasedZeroDay;
	}

	private static int marchMonth0From(int marchDoy0) {
		return (marchDoy0 * 5 + 2) / 153;
	}

	private static long dayOfYearEstimateFrom(long marchBasedZeroDay, long yearEst) {
		return marchBasedZeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
	}

	private static long yearEstimateFrom(long marchBasedZeroDay) {
		return (400 * marchBasedZeroDay + 591) / DAYS_PER_CYCLE;
	}
}
