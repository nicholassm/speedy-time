package systems.speedy.lmf.time;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import org.junit.Test;

public class TestUnixTimestampConverter {
	// 1970-01-01 00:00:00.000
	private static final long timestamp0 = 0L;

	// 2016-01-03 15:20:10.674
	private static final long timestamp1 = 1451834410674L;

	//
	// Test timestamp 0 (start of Epoch).
	//
	@Test
	public void yearOfstartOfEpochShouldBe1970() {
		assertEquals(1970, UnixTimestampConverter.yearOf(timestamp0));
	}

	@Test
	public void monthOfstartOfEpochShouldBe1() {
		assertEquals(1, UnixTimestampConverter.monthOf(timestamp0));
	}

	@Test
	public void dayOfMontyOfstartOfEpochShouldBe1() {
		assertEquals(1, UnixTimestampConverter.dayOfMonthOf(timestamp0));
	}

	@Test
	public void hourOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.hourOf(timestamp0));
	}

	@Test
	public void minutesOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.minutesOf(timestamp0));
	}

	@Test
	public void secondsOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.secondsOf(timestamp0));
	}

	@Test
	public void milliSecondsOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.milliSecondsOf(timestamp0));
	}

	//
	// Test timestamp 1.
	//
	@Test
	public void yearOfTimestamp1ShouldBe2016() {
		assertEquals(2016, UnixTimestampConverter.yearOf(timestamp1));
	}

	@Test
	public void monthOfTimestamp1ShouldBe1() {
		assertEquals(1, UnixTimestampConverter.monthOf(timestamp1));
	}

	@Test
	public void dayOfMonthOfTimestamp1ShouldBe3() {
		assertEquals(3, UnixTimestampConverter.dayOfMonthOf(timestamp1));
	}

	@Test
	public void hourOfTimestamp1ShouldBe15() {
		assertEquals(15, UnixTimestampConverter.hourOf(timestamp1));
	}

	@Test
	public void minutesOfTimestamp1ShouldBe20() {
		assertEquals(20, UnixTimestampConverter.minutesOf(timestamp1));
	}

	@Test
	public void secondsOfTimestamp1ShouldBe10() {
		assertEquals(10, UnixTimestampConverter.secondsOf(timestamp1));
	}

	@Test
	public void milliSecondsOfTimestamp1ShouldBe674() {
		assertEquals(674, UnixTimestampConverter.milliSecondsOf(timestamp1));
	}

	@Test
	public void testComparisonWithLocalDateTime() {
		final Random rand = new Random(123456L);

		long[] timestamps = new long[Constants.NUM_OF_TIMESTAMPS];

		for (int i = 0; i < Constants.NUM_OF_TIMESTAMPS; ++i) {
			timestamps[i] = Math.abs(rand.nextLong());
		}

		for (long timestamp: timestamps) {
			LocalDateTime ldt = LocalDateTime.ofEpochSecond(timestamp / 1_000, 0, ZoneOffset.UTC);

			assertEquals("timestamp="+timestamp, ldt.getYear(), UnixTimestampConverter.yearOf(timestamp));
			assertEquals("timestamp="+timestamp, ldt.getDayOfMonth(), UnixTimestampConverter.dayOfMonthOf(timestamp));
			assertEquals("timestamp="+timestamp, ldt.getMonthValue(), UnixTimestampConverter.monthOf(timestamp));
			assertEquals("timestamp="+timestamp, ldt.getHour(), UnixTimestampConverter.hourOf(timestamp));
			assertEquals("timestamp="+timestamp, ldt.getMinute(), UnixTimestampConverter.minutesOf(timestamp));
			assertEquals("timestamp="+timestamp, ldt.getSecond(), UnixTimestampConverter.secondsOf(timestamp));
		}
	}
}
