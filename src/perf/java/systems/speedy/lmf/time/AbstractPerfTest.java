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
			long warmUpTime = conductTest(timestamps);
			System.out.println("Warm up time #"+i+": "+warmUpTime+"ms");
		}

		for (int i = 0; i < Constants.REPETITIONS; ++i) {
			timings.add(conductTest(timestamps));
		}

		for (int i = 0; i < Constants.REPETITIONS; ++i) {
			System.out.println("Test #"+i+" time: "+timings.get(i)+"ms");
		}

		double average = timings.stream().mapToLong(l -> l).average().orElse(0.0);
		System.out.println("Avg: "+average);
		System.out.println("Max: "+timings.stream().mapToLong(l -> l).max().orElse(0));
		System.out.println("Min: "+timings.stream().mapToLong(l -> l).min().orElse(0));
		System.out.println("Average time per timestamp: "+(average * 1_000_000)/Constants.NUM_OF_TIMESTAMPS+" ns");
	}

	public static long toMilliSeconds(long warmUpTime) {
		return warmUpTime / 1_000_000L;
	}

	protected abstract void generateTestData(long[] timestamps);
	protected abstract long conductTest(long[] timestamps);
}
