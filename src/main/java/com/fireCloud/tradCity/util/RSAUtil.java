package com.fireCloud.tradCity.util;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import com.fireCloud.tradCity.log.SysLogger;

public class RSAUtil {

	static SysLogger sysLogger = new SysLogger();

	/**
	 * verify
	 *
	 * @param publicKeyStr
	 *            publickey base64
	 * @param sign
	 * @param content
	 * @return
	 */
	public static boolean verify(String publicKeyStr, byte[] sign, byte[] content) {
		if (publicKeyStr == null || publicKeyStr.length() == 0) {
			throw new RuntimeException("publickey is empty.");
		}
		if (sign == null || content == null) {
			return false;
		}
		RSAPublicKey publicKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = (RSAPublicKey) keyFactory
					.generatePublic(new X509EncodedKeySpec(Base64Util.decode(publicKeyStr)));
		} catch (Exception e) {
			throw new RuntimeException("init public key failed!", e);
		}
		boolean isOk = false;
		try {
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initVerify(publicKey);
			signature.update(content);
			isOk = signature.verify(sign);
		} catch (Exception e) {
			sysLogger.error("验证出错", "rsa verify failed.", e);
		}
		return isOk;
	}

}
