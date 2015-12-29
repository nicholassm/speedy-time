package systems.speedy.lmf.time;

public class PerfTest2 extends AbstractPerfTest {
	public static void main(String[] args) {
		new PerfTest2().runTest();
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
			UTCDateTime utcdt = new UTCDateTime(timestamp);

			sum += utcdt.getYear();
			sum += utcdt.getMonth();
			sum += utcdt.getDayOfMonth();
			sum += utcdt.getHour();
			sum += utcdt.getMinutes();
			sum += utcdt.getSeconds();
		}

		long duration = toMilliSeconds(System.nanoTime() - start);
		// To ensure computations are not optimized away.
		System.out.println(sum);

		return duration;
	}
}
