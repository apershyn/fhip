package smile.playground.alextest;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.util.StopWatch;


@RunWith(MockitoJUnitRunner.class)
public class StatsInterceptorTest {
	
	@Test
	public void testInterceptResponse() {
		StatsCollector sc = new StatsCollector();
		StatsInterceptor si = new StatsInterceptor(sc);
		IHttpResponse httpResp = Mockito.mock(IHttpResponse.class);
		StopWatch sw = Mockito.mock(StopWatch.class);
		Mockito.when(httpResp.getRequestStopWatch()).thenReturn(sw);
		Mockito.when(sw.getMillis()).thenReturn(new Long(200));	
		si.interceptResponse(httpResp);
		Mockito.when(httpResp.getRequestStopWatch()).thenReturn(sw);
		Mockito.when(sw.getMillis()).thenReturn(new Long(300));	
		si.interceptResponse(httpResp);
		Mockito.when(httpResp.getRequestStopWatch()).thenReturn(sw);
		Mockito.when(sw.getMillis()).thenReturn(new Long(400));	
		si.interceptResponse(httpResp);
		ArrayList<Long> realList = sc.getTimings();
		ArrayList<Long> expectedList = new ArrayList<Long>(Arrays.asList(new Long(200), new Long(300),new Long(400)));
		Assert.assertEquals(realList, expectedList);
	}
	
	
	@Test
	public void testStatsInterceptorConstructor() {
		StatsCollector sc = new StatsCollector();
		StatsInterceptor si = new StatsInterceptor(sc);
		Assert.assertEquals(sc, si.getStarsCollector());
	}
}
