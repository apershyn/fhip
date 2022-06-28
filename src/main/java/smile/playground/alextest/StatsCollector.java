package smile.playground.alextest;

import java.util.ArrayList;

public class StatsCollector {

	ArrayList<Long> timings = new ArrayList<Long>();

	public float getAverageResponseTime() {
		long sum = 0;
		for (Long time : timings) {
			sum = sum + time.longValue();
		}
		return sum / timings.size();
	}

	public void add(long l) {
		timings.add(l);
	}

	public ArrayList<Long> getTimings() {
		return timings;
	}

}
