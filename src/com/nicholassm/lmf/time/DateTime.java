package com.nicholassm.lmf.time;

public class DateTime {
	private final long utcMillis;

	private static final int  DAYS_PER_CYCLE     = 146097;
	private static final long DAYS_0000_TO_1970  = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);
	private static final long MILLIS_IN_A_SECOND = 1_000L;
	private static final long MILLIS_IN_A_MINUTE = MILLIS_IN_A_SECOND * 60L;
	private static final long MILLIS_IN_AN_HOUR  = MILLIS_IN_A_MINUTE * 60L;
	private static final long MILLIS_IN_A_DAY    = MILLIS_IN_AN_HOUR * 24L;

	// TODO: Only work with positive numbers? (For speed).
	public DateTime(long utcMillis) {
		this.utcMillis = utcMillis;
	}

	public static DateTime now() {
		return new DateTime(System.currentTimeMillis());
	}

	public static int yearOf(long utcMillis) {
		assert utcMillis >= 0;

		long epochDay   = utcMillis / MILLIS_IN_A_DAY;
		long zeroDay    = epochDay + DAYS_0000_TO_1970;
		// Find the March-based year.
		zeroDay -= 60; // Adjust to 0000-03-01 so leap day is at end of four year cycle.
		long yearEst    = (400 * zeroDay + 591) / DAYS_PER_CYCLE;
		long doyEst     = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
		int marchDoy0   = (int) doyEst;
		// Convert march-based values back to January-based.
		int marchMonth0 = (marchDoy0 * 5 + 2) / 153;
		yearEst += marchMonth0 / 10;

		return (int) yearEst;
	}

	public static int monthOf(long utcMillis) {
		assert utcMillis >= 0;

		long epochDay   = utcMillis / MILLIS_IN_A_DAY;
		long zeroDay    = epochDay + DAYS_0000_TO_1970;
		// Find the March-based year.
		zeroDay -= 60; // Adjust to 0000-03-01 so leap day is at end of four year cycle.
		long yearEst    = (400 * zeroDay + 591) / DAYS_PER_CYCLE;
		long doyEst     = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
		int marchDoy0   = (int) doyEst;
		// Convert march-based values back to January-based.
		int marchMonth0 = (marchDoy0 * 5 + 2) / 153;
		int month       = (marchMonth0 + 2) % 12 + 1;

		return month;
	}

	public static int dayOfMonthOf(long utcMillis) {
		assert utcMillis >= 0;

		long epochDay   = utcMillis / MILLIS_IN_A_DAY;
		long zeroDay    = epochDay + DAYS_0000_TO_1970;
		// Find the March-based year.
		zeroDay -= 60; // Adjust to 0000-03-01 so leap day is at end of four year cycle.
		long yearEst    = (400 * zeroDay + 591) / DAYS_PER_CYCLE;
		long doyEst     = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
		int marchDoy0   = (int) doyEst;
		// Convert march-based values back to January-based.
		int marchMonth0 = (marchDoy0 * 5 + 2) / 153;
		int dom         = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1;

		return dom;
	}

	public static int hourOf(long utcMillis) {
		assert utcMillis >= 0;
		return (int) ((utcMillis % MILLIS_IN_A_DAY) / MILLIS_IN_AN_HOUR);
	}

	public static int minuteOf(long utcMillis) {
		assert utcMillis >= 0;
		return (int) ((utcMillis % MILLIS_IN_AN_HOUR) / MILLIS_IN_A_MINUTE);
	}

	public static int secondsOf(long utcMillis) {
		assert utcMillis >= 0;
		return (int) ((utcMillis % MILLIS_IN_A_MINUTE) / MILLIS_IN_A_SECOND);
	}

	public long asUtcMillis() {
		return utcMillis;
	}

	public static void main(String[] args) {
		long now = System.currentTimeMillis();
		System.out.println(yearOf(now)+"-"+monthOf(now)+"-"+dayOfMonthOf(now));
		System.out.println(hourOf(now)+":"+minuteOf(now)+":"+secondsOf(now));
	}
}
