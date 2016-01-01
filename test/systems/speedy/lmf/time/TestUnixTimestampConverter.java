package systems.speedy.lmf.time;

import static org.junit.Assert.assertEquals;

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
}
