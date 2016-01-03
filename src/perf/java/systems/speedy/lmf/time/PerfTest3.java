package systems.speedy.lmf.time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PerfTest3 extends AbstractPerfTest{
	public static void main(String[] args) {
		new PerfTest3().runTest();
	}

	@Override protected void generateTestData(long[] timestamps) {
		for (int i = 0; i < Constants.NUM_OF_TIMESTAMPS; ++i) {
			timestamps[i] = Math.abs(rand.nextLong()) / 1_000;
		}
	}

	@Override protected long conductTest(long[] timestamps) {
		long sum   = 0L;
		long start = System.nanoTime();

		for (long timestamp: timestamps) {
			LocalDateTime ldt = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);

			sum += ldt.getYear();
			sum += ldt.getMonthValue();
			sum += ldt.getDayOfMonth();
			sum += ldt.getHour();
			sum += ldt.getMinute();
			sum += ldt.getSecond();
		}

		long duration = toMilliSeconds(System.nanoTime() - start);
		// To ensure computations are not optimized away.
		System.out.println(sum);

		return duration;
	}
}
