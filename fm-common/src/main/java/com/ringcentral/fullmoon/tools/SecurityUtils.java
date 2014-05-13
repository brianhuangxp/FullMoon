package com.ringcentral.fullmoon.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityUtils {
    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	private static String DEFAULT_CODE = "R9FYn6uibEmKqLHehAM2Z4SjUt1WBw5rGadDVskTC0plXox7g3PIcyzJQOfvN8";
	private static String DEFAULT_KEY_CODE = "804IKLG7A25BH9OFNP61CEMD3QJ";

	private static int DEFAULT_KEY_LENGTH = 32;

	private String allowCode;
	private String allowKeyCode;
	private int keyLength;

	private static SecurityUtils instance;

	/**
	 * 
	 * @param codeChar
	 *            : The char of encrypt or decode . Can't repeat.
	 *            codeChar.length > KeyCode.length
	 * @param keyCodeChar
	 *            : The char of generate random key. Can't repeat.
	 * @param keyLength
	 *            : The length of generate key
	 * @param keyCharLength
	 *            : The length of key char from array char. The max value =
	 *            codes.length - 9
	 * 
	 */
	private SecurityUtils(String allowCode, String allowKeyCode, int keyLength) {
		this.allowCode = allowCode;
		this.allowKeyCode = allowKeyCode;
		this.keyLength = keyLength;
	}

	/**
	 * @see SecurityUtil#SecurityUtil(String, String, int)
	 * @return
	 */
	public static SecurityUtils getInstance(String allowCode,
			String allowKeyCode, int keyLength) {
		if (instance == null) {
			instance = new SecurityUtils(allowCode, allowKeyCode, keyLength);
		}
		return instance;
	}

	public static SecurityUtils getInstance() {
		if (instance == null) {
			instance = new SecurityUtils(DEFAULT_CODE, DEFAULT_KEY_CODE,
					DEFAULT_KEY_LENGTH);
		}
		return instance;
	}

	/**
	 * generate random key
	 * 
	 * @return
	 */
	public String generateKey() {
		String keyChar = "";
		for (int i = 0; i < keyLength; i++) {
			keyChar += allowKeyCode.charAt((int) Math.round(Math.random()
					* (allowKeyCode.length() - 1)));
		}
        logger.debug("key==" + keyChar);
		return keyChar;
	}

	private int getIndex(char c, String chars) {
		for (int i = 0; i < chars.length(); i++) {
			if (chars.charAt(i) == c) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param code
	 * @param keyChar
	 * @param isEncrypt
	 *            true: encrypt; false: decode
	 * @return
	 */
	private char encryptChar(Character code, Character keyChar,
			boolean isEncrypt) {
		int origIndex = (code != null ? getIndex(code, allowCode) : 0);
		int k = (keyChar != null ? getIndex(keyChar, allowKeyCode) : 0);
		if (isEncrypt) {
			return allowCode.charAt((origIndex + k) % allowCode.length());
		} else {
			return origIndex - k >= 0 ? allowCode.charAt(origIndex - k) : allowCode.charAt(allowCode.length() + origIndex - k);
		}

	}

	public String encrypt(String origCode, String key, long time) {
		char origChars[] = new char[origCode.length()];
		char keyChars[] = new char[key.length()];
		origCode.getChars(0, origCode.length(), origChars, 0);
		key.getChars(0, key.length(), keyChars, 0);
		int length = origCode.length() > key.length() ? origCode.length() : key
				.length();
		// first char check time
		String encryptCode = "" + allowCode.charAt((int) time % allowCode.length());

		for (int i = 0; i < length; i++) {
			Character origChar = origCode.length() > i ? origChars[i] : null;
			Character keyChar = key.length() > i ? key.charAt(i) : null;
			encryptCode += encryptChar(origChar, keyChar, true);
		}

		encryptCode += allowCode.charAt(origCode.length());

		// last char check origCode
		byte obs[] = origCode.getBytes();
		long sum = 0l;
		for (byte b : obs) {
			sum += b;
		}

		encryptCode += allowCode.charAt((int) sum % allowCode.length());
		return encryptCode;
	}

	public String encrypt(String origCode, String key) {
		return encrypt(origCode, key, System.currentTimeMillis());
	}

	public String encrypt(String origCode) {
		String key = generateKey();
		return encrypt(origCode, key, System.currentTimeMillis());
	}

	public String decode(String encryptCode, String key, long time) {
		// check time
		if (encryptCode == null
				|| "".equals(encryptCode)
				|| allowCode.charAt((int) time % allowCode.length()) != encryptCode.charAt(0)
				|| encryptCode.length() != keyLength + 3) {
			return null;
		}
		int decodeLength = getIndex(encryptCode.charAt(encryptCode.length() - 2), allowCode);

		char encryptChars[] = new char[encryptCode.length() - 3];
		char keyChars[] = new char[key.length()];
		encryptCode.getChars(1, encryptCode.length() - 2, encryptChars, 0);
		key.getChars(0, key.length(), keyChars, 0);

		String decode = "";
		for (int i = 0; i < decodeLength; i++) {

			Character ec = encryptChars[i];
			Character keyChar = key.length() > i ? key.charAt(i) : null;
			decode += encryptChar(ec, keyChar, false);
		}

		// check decode
		byte obs[] = decode.getBytes();
		long sum = 0l;
		for (byte b : obs) {
			sum += b;
		}

		if (allowCode.charAt((int) sum % allowCode.length()) != encryptCode
				.charAt(encryptCode.length() - 1)) {
			return null;
		}
		return decode;
	}

	public String getAllowCode() {
		return allowCode;
	}

	public String getAllowKeyCode() {
		return allowKeyCode;
	}
	
	
	private String randomCode(String allowCode){
		String rs = "";
		while(allowCode.length() > 0){
			char tc = allowCode.charAt((int) Math.round(Math.random()
					* (allowCode.length() - 1)));	
			int idx = allowCode.indexOf(tc);
			if(idx > -1){;
				String[] as =allowCode.split(tc+"");
				rs += tc;
				if(as.length == 2){
					allowCode = as[0] + as[1];
				}else if(as.length > 0 && as[0].length() != 0){
					allowCode = as[0];
				}else{
					break;
				}
				
			}
		}
		return rs;
	}
}
