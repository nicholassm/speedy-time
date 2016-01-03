package systems.speedy.lmf.time;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestUTCDateTime {

	@Test
	public void testToString() {
		assertEquals("1970-01-01T00:00:00.000Z", new UTCDateTime(0).toString());
	}
}
