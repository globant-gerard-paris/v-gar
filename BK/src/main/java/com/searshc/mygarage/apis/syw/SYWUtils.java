package com.searshc.mygarage.apis.syw;

import java.security.MessageDigest;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;
import org.parboiled.common.StringUtils;

import com.google.common.primitives.Bytes;

/**
 * @author Jero
 *
 */
public class SYWUtils {

	private static final int LENGHT_USER_ID = 7;
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String DEFAULT_ALGORITHM = "SHA-256";

	/**
	 * This method generate a thread-safe valid SYW Hash code, given current {@code appSecret} and
	 * {@code sessionToken} that will be used to hit SYW services.
	 * <p>
	 * 
	 * <strong>NOTE:</strong> For more information, take a look <a
	 * href="http://developers.shopyourway.com/display/platdev/App+Secret+-+Hash">here</a>.
	 * <p>
	 * 
	 * @param appSecret
	 * @param sessionToken
	 * @return Return a valid SYW Hash.
	 * @throws Exception
	 */
	public static synchronized String getHash(final String appSecret, final String sessionToken)
			throws Exception {
		MessageDigest md = MessageDigest.getInstance(DEFAULT_ALGORITHM);
		md.reset();
		ArrayList<Byte> temp = new ArrayList<Byte>();
		temp.addAll(Bytes.asList(sessionToken.getBytes(DEFAULT_CHARSET)));
		temp.addAll(Bytes.asList(appSecret.getBytes(DEFAULT_CHARSET)));
		return Hex.encodeHexString(md.digest(Bytes.toArray(temp)));
	}

	/**
	 * Extract the userId from the given {@code token}.
	 * 
	 * @param token
	 * @return Return the userId otherwise return null.
	 */
	public static Long getSywId(final String token) {
		if (StringUtils.isEmpty(token) || token.length() < LENGHT_USER_ID) {
			return null;
		}
		String sywString = token.substring(0, LENGHT_USER_ID);
		try {
			return Long.valueOf(sywString);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
