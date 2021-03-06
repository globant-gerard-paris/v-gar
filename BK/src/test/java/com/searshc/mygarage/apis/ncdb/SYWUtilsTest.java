package com.searshc.mygarage.apis.ncdb;

import org.junit.Assert;
import org.junit.Test;

import com.searshc.mygarage.apis.syw.SYWUtils;

/**
 * @author Jero
 *
 */
public class SYWUtilsTest {

	@Test
	public void testSuccessHashcode() {
		try {
			String hash = SYWUtils
					.getHash("41c3a227fb8844da9da890e9ce6258ea",
							"5681385_17510_1416603727_0_e2bde10a6ab6dc2211de8f5b1ae83b0412d15a05d0c9702853bee742fdaa6351");
			Assert.assertNotNull(hash);
			Assert.assertEquals(hash,
					"d34ede670d6136818c5840036cc41f2a1bce09f6128447dcaae80636dc0c837b");
		} catch (Exception e) {
		}
	}

	@Test
	public void testSuccessGetUser() {
		Long userId = SYWUtils.getSywId("5681385_17510_1416603727_0_e2bde10a6ab6dc2211de8f5b1ae83b0412d15a05d0c9702853bee742fdaa6351");
		Assert.assertEquals(userId, new Long(5681385));
	}

	@Test
	public void testFaildGetUser() {
		Long userId = SYWUtils.getSywId("a351");
		Assert.assertNull(userId);
	}

}
