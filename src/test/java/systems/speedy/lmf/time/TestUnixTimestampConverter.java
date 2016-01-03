package systems.speedy.lmf.time;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import org.junit.Test;

public class TestUnixTimestampConverter {
	@Test
	public void yearOfstartOfEpochShouldBe1970() {
		assertEquals(1970, UnixTimestampConverter.yearOf(0L));
	}

	@Test
	public void monthOfstartOfEpochShouldBe1() {
		assertEquals(1, UnixTimestampConverter.monthOf(0L));
	}

	@Test
	public void dayOfMontyOfstartOfEpochShouldBe1() {
		assertEquals(1, UnixTimestampConverter.dayOfMonthOf(0L));
	}

	@Test
	public void hourOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.hourOf(0L));
	}

	@Test
	public void minutesOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.minutesOf(0L));
	}

	@Test
	public void secondsOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.secondsOf(0L));
	}

	@Test
	public void milliSecondsOfstartOfEpochShouldBe0() {
		assertEquals(0, UnixTimestampConverter.milliSecondsOf(0L));
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
