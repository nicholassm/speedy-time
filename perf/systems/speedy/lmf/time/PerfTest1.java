package systems.speedy.lmf.time;

public class PerfTest1 extends AbstractPerfTest {
	public static void main(String[] args) {
		new PerfTest1().runTest();
	}

	@Override protected void generateTestData(long[] timestamps) {
		for (int i = 0; i < Constants.NUM_OF_TIMESTAMPS; ++i) {
			timestamps[i] = Math.abs(rand.nextLong());
		}
	}

	@Override protected long conductTest(long[] timestamps) {
		long sum   = 0L;
		long start = System.nanoTime();

		for (long timestamp: timestamps) {
			sum += UTCDateTime.yearOf(timestamp);
			sum += UTCDateTime.monthOf(timestamp);
			sum += UTCDateTime.dayOfMonthOf(timestamp);
			sum += UTCDateTime.hourOf(timestamp);
			sum += UTCDateTime.minutesOf(timestamp);
			sum += UTCDateTime.secondsOf(timestamp);
		}

		long duration = toMilliSeconds(System.nanoTime() - start);
		// To ensure computations are not optimized away.
		System.out.println(sum);

		return duration;
	}
}
