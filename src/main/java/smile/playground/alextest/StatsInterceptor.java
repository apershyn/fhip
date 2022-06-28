package smile.playground.alextest;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.client.api.IHttpResponse;

@Interceptor
public class StatsInterceptor {

	private StatsCollector sc = null;

	public StatsInterceptor(StatsCollector sc) {
		super();
		this.sc = sc;
	}

	public StatsCollector getStarsCollector() {
		return sc;
	}

	@Hook(Pointcut.CLIENT_RESPONSE)
	public void interceptResponse(IHttpResponse theResponse) {
		sc.add(theResponse.getRequestStopWatch().getMillis());
	}

}
