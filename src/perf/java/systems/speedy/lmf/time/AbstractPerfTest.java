package systems.speedy.lmf.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractPerfTest {

	protected final Random rand = new Random(123456L);

	public void runTest() {
		final long[] timestamps  = new long[Constants.NUM_OF_TIMESTAMPS];
		final List<Long> timings = new ArrayList<>(Constants.REPETITIONS);

		generateTestData(timestamps);

		for (int i = 0; i < Constants.NUM_OF_WARMUPS; ++i) {
			long warmUpTime = conductTestInMs(timestamps);
			System.out.println("Warm up time #"+i+": "+warmUpTime+"ms");
		}

		for (int i = 0; i < Constants.REPETITIONS; ++i) {
			timings.add(conductTestInMs(timestamps));
		}

		for (int i = 0; i < Constants.REPETITIONS; ++i) {
			System.out.println("Test #"+i+" time: "+timings.get(i)+"ms");
		}

		double average = timings.stream().mapToLong(l -> l).average().orElse(0.0);
		System.out.println("Avg: "+average+"ms");
		System.out.println("Max: "+timings.stream().mapToLong(l -> l).max().orElse(0)+"ms");
		System.out.println("Min: "+timings.stream().mapToLong(l -> l).min().orElse(0)+"ms");
		System.out.println("Std: "+Math.sqrt(timings.stream().mapToDouble(l -> Math.pow(l - average, 2.0)).sum() / Constants.REPETITIONS));
		System.out.println("Average time per timestamp: "+(average * 1_000_000)/Constants.NUM_OF_TIMESTAMPS+"ms");
	}

	public static long toMilliSeconds(long timeInNs) {
		return timeInNs / 1_000_000L;
	}

	protected abstract void generateTestData(long[] timestamps);

	/**
	 * @param timestamps The array of timestamps to perform the conversions on.
	 * @return The duration in ns of all the conversions.
	 */
	protected abstract long conductTest(long[] timestamps);

	public long conductTestInMs(long[] timestamps) {
		return toMilliSeconds(conductTest(timestamps));
	}
}
