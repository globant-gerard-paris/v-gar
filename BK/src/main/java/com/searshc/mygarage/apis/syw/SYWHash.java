package com.searshc.mygarage.apis.syw;

import java.security.MessageDigest;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Hex;
import com.google.common.primitives.Bytes;

/**
 * @author Jero
 *
 */
public class SYWHash {

	/**
	 * This method generate a thread-safe valid SYW Hash code, given current {@code appSecret} and {@code sessionToken}
	 * that will be used to hit SYW services.<p>
	 * 
	 * <strong>NOTE:</strong>
	 * For more information, take a look <a href="http://developers.shopyourway.com/display/platdev/App+Secret+-+Hash">here</a>.<p>
	 * 
	 * @param appSecret
	 * @param sessionToken
	 * @return Return a valid SYW Hash.
	 * @throws Exception
	 */
	public static synchronized String getHash(final String appSecret, final String sessionToken) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		ArrayList<Byte> temp = new ArrayList<Byte>();
		temp.addAll(Bytes.asList(sessionToken.getBytes("UTF-8")));
		temp.addAll(Bytes.asList(appSecret.getBytes("UTF-8")));
		return Hex.encodeHexString(md.digest(Bytes.toArray(temp)));
	}

}
