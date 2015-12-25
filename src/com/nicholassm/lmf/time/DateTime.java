package com.nicholassm.lmf.time;

public class DateTime {
	private final long utcMillis;

	private static final int  DAYS_PER_CYCLE     = 146097;
	private static final long DAYS_0000_TO_1970  = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);
	private static final long MILLIS_IN_A_SECOND = 1_000L;
	private static final long MILLIS_IN_A_MINUTE = MILLIS_IN_A_SECOND * 60L;
	private static final long MILLIS_IN_AN_HOUR  = MILLIS_IN_A_MINUTE * 60L;
	private static final long MILLIS_IN_A_DAY    = MILLIS_IN_AN_HOUR * 24L;
	private static final long MILLIS_IN_A_MONTH  = MILLIS_IN_A_DAY * 30L;
	private static final long MILLIS_IN_A_YEAR   = MILLIS_IN_A_DAY * 365L;

	// TODO: Only work with positive numbers? (For speed).
	public DateTime(long utcMillis) {
		this.utcMillis = utcMillis;
	}

	// TODO: Idea: Precalculate 1. Jan. 00:00:00.000 for each year and store in an array. Index is
	// years after 1970. Do a binary search to find year.
	// For months and days: Subtract start of year in millis.
	// Now two cases: (1) Non-leap year and (2) leap year.

	// TODO: Take leap years into account:
	public static int yearOf(long utcMillis) {
		long epochDay = utcMillis / MILLIS_IN_A_DAY;
		// TODO: Continue here: How to calculate the year.
		return (int) (epochDay + DAYS_0000_TO_1970);
	}

	// TODO: Take leap years into account:
	public static int monthOf(long utcMillis) {
		return (int) ((utcMillis % MILLIS_IN_A_YEAR) / MILLIS_IN_A_MONTH);
	}

	// TODO: Take leap years into account:
	public static int dayOf(long utcMillis) {
		return (int) ((utcMillis % MILLIS_IN_A_MONTH) / MILLIS_IN_A_DAY);
	}

	public static int hourOf(long utcMillis) {
		return (int) ((utcMillis % MILLIS_IN_A_DAY) / MILLIS_IN_AN_HOUR);
	}

	public static int minuteOf(long utcMillis) {
		return (int) ((utcMillis % MILLIS_IN_AN_HOUR) / MILLIS_IN_A_MINUTE);
	}

	public long asUtcMillis() {
		return utcMillis;
	}

	public static void main(String[] args) {
		System.out.println(yearOf(System.currentTimeMillis()));
		System.out.println(monthOf(System.currentTimeMillis()));
		System.out.println(dayOf(System.currentTimeMillis()));
		System.out.println(hourOf(System.currentTimeMillis()));
		System.out.println(minuteOf(System.currentTimeMillis()));
	}
}
