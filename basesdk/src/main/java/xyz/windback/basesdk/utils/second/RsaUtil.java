package xyz.windback.basesdk.utils.second;



import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;

import xyz.windback.base64.BASE64Decoder;
import xyz.windback.base64.BASE64Encoder;

/**
 * rsa 工具类
 * 
 * @author lhc
 */
public class RsaUtil {

	public static void main(String[] args) {
		try {
			HashMap<String, Object> keys = RsaUtil.getRSAKeys();
			RSAPublicKey pub_key = (RSAPublicKey) keys.get("publicKey");
			RSAPrivateKey pri_key = (RSAPrivateKey) keys.get("privateKey");

			String pubKey = RsaUtil.getKeyString(pub_key);
			String priKey = RsaUtil.getKeyString(pri_key);

			// System.out.println("base64公钥：" + pubKey);
			// System.out.println("base64私钥：" + priKey);

			/****************************************************************/
			RSAPublicKey publicKey = RsaUtil.getPublicKey(pubKey);
			// System.out.println("公钥对象：" + publicKey);
			String pass = "tcps_admin1234";
			String jm = RsaUtil.encrypt(publicKey, pass);
			// System.out.println("公钥加密密文：" + jm);
			/****************************************************************/

			RSAPrivateKey privateKey = RsaUtil.getPrivateKey(priKey);
			// System.out.println("私钥对象：" + privateKey);
			// System.out.println("私钥解密：" + RsaUtil.decrypt(privateKey, jm));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建公私钥对
	 * 
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Object> getRSAKeys() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		map.put("publicKey", publicKey);
		map.put("privateKey", privateKey);
		return map;
	}

	/**
	 * 得到密钥字符串（经过base64编码）
	 * 
	 * @param key
	 *            公私钥
	 * @return
	 * @throws Exception
	 */
	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(keyBytes);
	}

	/**
	 * 使用base64编码的公钥字符串获得公钥
	 * 
	 * @param keyStr
	 *            base64编码的公钥
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(String keyStr) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		// System.out.println("decoder:length=="
		// + decoder.decodeBuffer(keyStr).length + "___String=="
		// + new String(decoder.decodeBuffer(keyStr), "UTF-8"));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(
				decoder.decodeBuffer(keyStr)));
	}

	/**
	 * 使用base64编码的私钥字符串获得私钥
	 * 
	 * @param keyStr
	 *            base64编码的私钥
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey getPrivateKey(String keyStr) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return (RSAPrivateKey) keyFactory
				.generatePrivate(new PKCS8EncodedKeySpec(decoder
						.decodeBuffer(keyStr)));
	}

	/**
	 * 加密函数
	 * 
	 * @param publicKey
	 *            公钥
	 * @param data
	 *            要加密的base64数据
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(RSAPublicKey publicKey, String data)
			throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] plaintext = data.getBytes("UTF-8");
		byte[] output = cipher.doFinal(plaintext);
		return new String(encoder.encode(output));
	}

	/**
	 * 解密函数
	 * 
	 *            公钥
	 * @param data
	 *            要解密的base64数据
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPublic(RSAPublicKey publicKey, String data)
			throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] newPlainText = cipher.doFinal(decoder.decodeBuffer(data));
		return new String(newPlainText, "utf-8").trim();
	}

	/**
	 * 解密函数
	 * 
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            要解密的base64数据
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(RSAPrivateKey privateKey, String data)
			throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		Cipher cipher = Cipher.getInstance("RSA/ECB/NOPADDING");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] newPlainText = cipher.doFinal(decoder.decodeBuffer(data));
		return new String(newPlainText, "utf-8");
	}
}
