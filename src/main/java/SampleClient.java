import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import smile.playground.alextest.NamesReader;
import smile.playground.alextest.StatsCollector;
import smile.playground.alextest.StatsInterceptor;

public class SampleClient {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SampleClient.class);

	public static void main(String[] theArgs) {
		String fileName = "names.txt";
		NamesReader nr = new NamesReader(fileName);
		String[] lastNames = null;
		try {
			lastNames = nr.readNames();
		} catch (Exception e) {
			log.error("Can't read file with name " + fileName + ", exit");
			System.exit(1);
		}
		for (int i = 0; i < 3; i++) {
			StatsCollector sc = new StatsCollector();
			for (String lastName : lastNames) {
				if (lastName.length() >= 1) {
					FhirContext fhirContext = FhirContext.forR4();
					IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
					client.registerInterceptor(new StatsInterceptor(sc));
					log.info("Search for last name which has left match with : " + lastName);
					if (i < 2) {
						client.search().forResource("Patient").where(Patient.FAMILY.matches().value(lastName))
								.returnBundle(Bundle.class).execute();
					} else {
						client.search().forResource(Patient.class).where(Patient.FAMILY.matches().value(lastName))
								.returnBundle(Bundle.class).cacheControl(new CacheControlDirective().setNoCache(true))
								.execute();
					}

				} else {
					log.info("Last name is empty, skip search with empty last name");
				}

			}
			log.info("Average response time on iteration " + i + ":" + sc.getAverageResponseTime() + " ms");

		}

	}
}
