package smile.playground.alextest;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class StatsCollectorTest {

	@Test
	public void testGetAverageResponseTime() {
		StatsCollector sc = new StatsCollector();
		sc.add(20);
		sc.add(30);
		sc.add(20);
		sc.add(30);
		float average = sc.getAverageResponseTime();
		float expectedValue = 25;
		Assert.assertEquals(average, expectedValue, 0);

	}

	@Test
	public void testAdd() {
		StatsCollector sc = new StatsCollector();
		Assert.assertEquals(sc.getTimings().size(),0);
		sc.add(20);
		sc.add(30);
		Assert.assertEquals(sc.getTimings().size(),2);
	}
	
	@Test
	public void testGetTimings() {
		StatsCollector sc = new StatsCollector();
		sc.add(20);
		sc.add(30);
		ArrayList<Long> realList = sc.getTimings();
		ArrayList<Long> expectedList = new ArrayList<Long>(Arrays.asList(new Long(20), new Long(30)));
		Assert.assertEquals(realList, expectedList);
	}

}
