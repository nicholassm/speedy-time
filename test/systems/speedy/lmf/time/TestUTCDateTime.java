package systems.speedy.lmf.time;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestUTCDateTime {
	@Test
	public void yearOfstartOfEpochShouldBe1970() {
		assertEquals(1970, UTCDateTime.yearOf(0L));
	}

	@Test
	public void monthOfstartOfEpochShouldBe1() {
		assertEquals(1, UTCDateTime.monthOf(0L));
	}

	@Test
	public void dayOfMontyOfstartOfEpochShouldBe1() {
		assertEquals(1, UTCDateTime.dayOfMonthOf(0L));
	}

	@Test
	public void hourOfstartOfEpochShouldBe0() {
		assertEquals(0, UTCDateTime.hourOf(0L));
	}

	@Test
	public void minutesOfstartOfEpochShouldBe0() {
		assertEquals(0, UTCDateTime.minutesOf(0L));
	}

	@Test
	public void secondsOfstartOfEpochShouldBe0() {
		assertEquals(0, UTCDateTime.secondsOf(0L));
	}

	@Test
	public void milliSecondsOfstartOfEpochShouldBe0() {
		assertEquals(0, UTCDateTime.milliSecondsOf(0L));
	}
}
