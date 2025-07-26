
package com.devopsdemo.utilities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestHexAsciiConversion {

	HexAsciiConvertor conversion = new HexAsciiConvertor();
	String value = "testing ascii convertion into hexadecimal";
	String hexvalue = "74657374696e6720617363696920636f6e76657274696f6e20696e746f2068657861646563696d616c";

	@Test
	void testAsciiToHexValid() {
		String hexadecimalValue = conversion.convertAsciiToHex(value);
		assertEquals("74657374696e6720617363696920636f6e76657274696f6e20696e746f2068657861646563696d616c", hexadecimalValue);
	}

	@Test
	void testAsciiToHexNull() {
		String hexvalueNull = conversion.convertHexToASCII(null);
		assertNull(hexvalueNull);
	}

	@Test
	void testHexToAsciiValid() {
		String asciiValue = conversion.convertHexToASCII(hexvalue);
		assertEquals("testing ascii convertion into hexadecimal", asciiValue);
	}

	@Test
	void testHextoAsciiNull() {
		String asciiValueNull = conversion.convertAsciiToHex(null);
		assertNull(asciiValueNull);
	}
}
