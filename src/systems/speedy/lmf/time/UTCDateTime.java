package systems.speedy.lmf.time;

/**
 * Utility class for converting milliseconds since the Epoch into date and time values.
 * Note: Only timestamps after January 1st 1970 are supported.
 */
public class UTCDateTime {
	private final long utcMillis;

	private static final int  DAYS_PER_CYCLE    = 146097;
	private static final long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);
	private static final long MILLIS_PER_SECOND = 1_000L;
	private static final long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * 60L;
	private static final long MILLIS_PER_HOUR   = MILLIS_PER_MINUTE * 60L;
	private static final long MILLIS_PER_DAY    = MILLIS_PER_HOUR * 24L;

	public UTCDateTime(long utcMillis) {
		assert utcMillis >= 0;

		this.utcMillis = utcMillis;
	}

	public static UTCDateTime now() {
		return new UTCDateTime(System.currentTimeMillis());
	}

	public static int yearOf(long utcMillis) {
		assert utcMillis >= 0;

		long marchBasedZeroDay = marchBasedZeroDayFrom(utcMillis);
		long yearEst           = yearEstimateFrom(marchBasedZeroDay);
		long doyEst            = dayOfYearEstimateFrom(marchBasedZeroDay, yearEst);
		int marchDoy0          = (int) doyEst;
		// Convert march-based values back to January-based.
		int marchMonth0        = marchMonth0From(marchDoy0);
		yearEst               += marchMonth0 / 10;

		return (int) yearEst;
	}

	public static int monthOf(long utcMillis) {
		assert utcMillis >= 0;

		int marchDoy0   = marchBasedDoy0From(utcMillis);
		// Convert march-based values back to January-based.
		int marchMonth0 = marchMonth0From(marchDoy0);
		int month       = (marchMonth0 + 2) % 12 + 1;

		return month;
	}

	public static int dayOfMonthOf(long utcMillis) {
		assert utcMillis >= 0;

		int marchDoy0   = marchBasedDoy0From(utcMillis);
		// Convert march-based values back to January-based.
		int marchMonth0 = marchMonth0From(marchDoy0);
		int dom         = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1;

		return dom;
	}

	public static int hourOf(long utcMillis) {
		assert utcMillis >= 0;

		return (int) ((utcMillis % MILLIS_PER_DAY) / MILLIS_PER_HOUR);
	}

	public static int minutesOf(long utcMillis) {
		assert utcMillis >= 0;

		return (int) ((utcMillis % MILLIS_PER_HOUR) / MILLIS_PER_MINUTE);
	}

	public static int secondsOf(long utcMillis) {
		assert utcMillis >= 0;

		return (int) ((utcMillis % MILLIS_PER_MINUTE) / MILLIS_PER_SECOND);
	}

	public static int milliSecondsOf(long utcMillis) {
		assert utcMillis >= 0;

		return (int) (utcMillis % MILLIS_PER_SECOND);
	}

	public int getYear() {
		return yearOf(utcMillis);
	}

	public int getMonth() {
		return monthOf(utcMillis);
	}

	public int getDayOfMonth() {
		return dayOfMonthOf(utcMillis);
	}

	public int getHour() {
		return hourOf(utcMillis);
	}

	public int getMinutes() {
		return minutesOf(utcMillis);
	}

	public int getSeconds() {
		return secondsOf(utcMillis);
	}

	public int getMilliSeconds() {
		return milliSecondsOf(utcMillis);
	}

	public long asUtcMillis() {
		return utcMillis;
	}

	private static int marchBasedDoy0From(long utcMillis) {
		long marchBasedZeroDay = marchBasedZeroDayFrom(utcMillis);
		long yearEst           = yearEstimateFrom(marchBasedZeroDay);
		long doyEst            = dayOfYearEstimateFrom(marchBasedZeroDay, yearEst);
		int marchDoy0          = (int) doyEst;
		return marchDoy0;
	}

	private static long marchBasedZeroDayFrom(long utcMillis) {
		long epochDay            = utcMillis / MILLIS_PER_DAY;
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
