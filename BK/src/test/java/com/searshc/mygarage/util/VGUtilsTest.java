package com.searshc.mygarage.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.searshc.mygarage.util.VGUtils;

public class VGUtilsTest {

	@Test
	public void testParseFloatWithPointSuccess() {
		String value = "-77.60742";
		Float parseSafeFloat = VGUtils.parseSafeFloat(value);
		assertEquals(new Float(-77.60742), parseSafeFloat);
	}

	@Test
	public void testParseFloatWithCommaSuccess() {
		String value = "-77,60742";
		Float parseSafeFloat = VGUtils.parseSafeFloat(value);
		assertEquals(new Float(-77.60742), parseSafeFloat);
	}
}
