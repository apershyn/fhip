package smile.playground.alextest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class NamesReaderTest {

	@Test
	public void testReadNames() throws Exception {
		NamesReader nr = new NamesReader("names.txt");
		String[] names = nr.readNames();
		assertEquals(names.length, 5);
		String[] expectedResult = new String[] { "SM", "RO", "AL", "BO", "SH" };
		Assert.assertArrayEquals(expectedResult, names);

	}

	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void testReadNamesThrowsException() throws Exception {
		NamesReader nr = new NamesReader("names1.txt");
		String[] names = nr.readNames();

	}

	@Test
	public void testReadNamesConstructor() {
		String fileName = "names.txt";
		NamesReader nr = new NamesReader(fileName);
		assertEquals(nr.getFileName(), fileName);

	}
}
