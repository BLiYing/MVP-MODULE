package xyz.windback.basesdk.utils.second;

import android.annotation.SuppressLint;


import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import xyz.windback.base64.BASE64Encoder;

@SuppressLint("DefaultLocale")
public class AppDes {

	@SuppressLint("TrulyRandom")
	public static String AesEncrypt(String src, String key, String ivParam) {
		try {
			SecretKey aeskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"AES");
			Cipher c1 = Cipher.getInstance("AES/CBC/NOPadding");
			IvParameterSpec iv = new IvParameterSpec(
					ConvertUtil.hexstr2byte(ivParam));
			c1.init(Cipher.ENCRYPT_MODE, aeskey, iv);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String AesEncrypt(String src, String key) {
		try {
			SecretKey aeskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"AES");
			Cipher c1 = Cipher.getInstance("AES/ECB/NOPadding");
			c1.init(Cipher.ENCRYPT_MODE, aeskey);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String AesDecrypt(String src, String key, String ivParam) {
		try {
			SecretKey aeskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"AES");
			Cipher c1 = Cipher.getInstance("AES/CBC/NOPadding");
			IvParameterSpec iv = new IvParameterSpec(
					ConvertUtil.hexstr2byte(ivParam));
			c1.init(Cipher.DECRYPT_MODE, aeskey, iv);
			return new String(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String AesDecrypt(String src, String key) {
		try {
			SecretKey aeskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"AES");
			Cipher c1 = Cipher.getInstance("AES/ECB/NOPadding");
			c1.init(Cipher.DECRYPT_MODE, aeskey);
			return new String(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	@SuppressLint("TrulyRandom")
	public static String signleDesEncrypt(String key, String src) {
		try {
			SecretKey deskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"DES");
			Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String signleDesEncrypt(String key, String src, String iv) {
		try {
			SecretKey deskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"DES");
			Cipher c1 = Cipher.getInstance("DES/CBC/NoPadding");
			IvParameterSpec ivParam = new IvParameterSpec(
					ConvertUtil.hexstr2byte(iv));
			c1.init(Cipher.ENCRYPT_MODE, deskey, ivParam);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String CBC_DESedeEncrypt(String key, String src, String iv) {
		try {
			SecretKey deskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"DESede");
			Cipher c1 = Cipher.getInstance("DESede/CBC/NoPadding");
			IvParameterSpec ivParam = new IvParameterSpec(
					ConvertUtil.hexstr2byte(iv));
			c1.init(Cipher.ENCRYPT_MODE, deskey, ivParam);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String CBC_DESedeDecrypt(String key, String src, String iv) {
		try {
			SecretKey deskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"DESede");
			Cipher c1 = Cipher.getInstance("DESede/CBC/NoPadding");
			IvParameterSpec ivParam = new IvParameterSpec(
					ConvertUtil.hexstr2byte(iv));
			c1.init(Cipher.DECRYPT_MODE, deskey, ivParam);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String signleDesDecrypt(String key, String src, String iv) {
		try {
			SecretKey deskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"DES");
			Cipher c1 = Cipher.getInstance("DES/CBC/NoPadding");
			IvParameterSpec ivParam = new IvParameterSpec(
					ConvertUtil.hexstr2byte(iv));
			c1.init(Cipher.DECRYPT_MODE, deskey, ivParam);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String signleDesDecrypt(String key, String src) {
		try {
			SecretKey deskey = new SecretKeySpec(ConvertUtil.hexstr2byte(key),
					"DES");
			Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return ConvertUtil
					.byte2hex(c1.doFinal(ConvertUtil.hexstr2byte(src)));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String TripDESencrypt(String key, String src) {
		String tmp = "";
		tmp = signleDesEncrypt(key.substring(0, 16), src);
		tmp = signleDesDecrypt(key.substring(16), tmp);
		tmp = signleDesEncrypt(key.substring(0, 16), tmp);
		return tmp;
	}

	/**
	 * 与iOS加密一致
	 * @param key
	 * @param src
	 * @return
	 */
	public static String TripDESencrypt1(String key, String src) {
		String tmp = "";
		try {
			tmp = encryptThreeDESECB(src, key);
			tmp = encryptThreeDESECB(tmp, key);
			tmp = encryptThreeDESECB(tmp, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public static String encryptThreeDESECB(String src, String key)
			throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		byte[] b = cipher.doFinal(src.getBytes());

		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");

	}

	public static String TripDESencrypt(String key, String src, String iv) {
		String tmp = "";
		tmp = signleDesEncrypt(key.substring(0, 16), src, iv);
		tmp = signleDesDecrypt(key.substring(16), tmp, iv);
		tmp = signleDesEncrypt(key.substring(0, 16), tmp, iv);
		return tmp;
	}

	public static String TripDESdecrypt(String key, String src) {
		String tmp = "";
		tmp = signleDesDecrypt(key.substring(0, 16), src);
		tmp = signleDesEncrypt(key.substring(16), tmp);
		tmp = signleDesDecrypt(key.substring(0, 16), tmp);
		return tmp;
	}

	public static String TripDESdecrypt(String key, String src, String iv) {
		String tmp = "";
		tmp = signleDesDecrypt(key.substring(0, 16), src, iv);
		tmp = signleDesEncrypt(key.substring(16), tmp, iv);
		tmp = signleDesDecrypt(key.substring(0, 16), tmp, iv);
		return tmp;
	}

	public static String ECB_TripDESencrypt(String key, String sour) {
		if (key.length() != 32 || sour.length() % 16 != 0) {
			return null;
		}
		String res = "";
		for (int i = 0; i < sour.length() / 16; i++) {
			res += TripDESencrypt(key, sour.substring(i * 16, i * 16 + 16));
		}
		return res;
	}

	public static String CBC_TripDESencrypt(String key, String sour, String iv) {
		if (key.length() != 32 || sour.length() % 16 != 0 || iv.length() != 16) {
			return null;
		}
		String res = "";
		for (int i = 0; i < sour.length() / 16; i++) {
			res += TripDESencrypt(key, sour.substring(i * 16, i * 16 + 16), iv);
			iv = res.substring(res.length() - 16);
		}
		return res;
	}

	public static String CBC_TripDESdecrypt(String key, String sour, String iv) {
		if (key.length() != 32 || sour.length() % 16 != 0 || iv.length() != 16) {
			return null;
		}
		String res = "";
		for (int i = 0; i < sour.length() / 16; i++) {
			res += TripDESdecrypt(key, sour.substring(i * 16, i * 16 + 16), iv);
			iv = sour.substring(i * 16, i * 16 + 16);
		}
		return res;
	}

	public static String ECB_TripDESdecrypt(String key, String sour) {
		if (key.length() != 32 || sour.length() % 16 != 0) {
			return null;
		}
		String res = "";
		for (int i = 0; i < sour.length() / 16; i++) {
			res += TripDESdecrypt(key, sour.substring(i * 16, i * 16 + 16));
		}
		return res;
	}

	public static String ECB_DESmac(String key, String sour, String iv) {
		if (key.length() != 16 || sour.length() % 16 != 0 || iv.length() != 16) {
			return null;
		}
		String temp = "";
		for (int i = 0; i < sour.length() / 16; i++) {
			temp = ConvertUtil.xHex(sour.substring(i * 16, i * 16 + 16), iv);
			iv = signleDesEncrypt(key.substring(0, 16), temp);
		}
		return iv;
	}

	public static String ECB_DEStac(String key, String sour, String iv) {
		if (key.length() != 32 || sour.length() % 16 != 0) {
			return null;
		}
		String temp = "";
		String lastkey = ConvertUtil.xHex(key.substring(0, 16),
				key.substring(16));
		for (int i = 0; i < sour.length() / 16; i++) {
			temp = ConvertUtil.xHex(sour.substring(i * 16, i * 16 + 16), iv);
			iv = signleDesEncrypt(lastkey, temp);
		}
		return iv;
	}

	public static String ECB_TripDESmac(String key, String sour, String iv) {
		if (key.length() != 32 || sour.length() % 16 != 0 || iv.length() != 16) {
			return null;
		}
		String temp = "";
		for (int i = 0; i < sour.length() / 16; i++) {
			temp = ConvertUtil.xHex(sour.substring(i * 16, i * 16 + 16), iv);
			iv = signleDesEncrypt(key.substring(0, 16), temp);
		}
		String dest = "";
		dest = signleDesDecrypt(key.substring(16), iv);
		dest = signleDesEncrypt(key.substring(0, 16), dest);
		return dest;
	}

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String complete80ForDes(String str) {
		int num = str.length() % 16;
		if (num == 0) {
			return str;
		}
		String returnStr = "8000000000000000".substring(0, 16 - num);
		return str + returnStr;
	}

	public static String complete80ForMac(String str) {
		int num = str.length() % 16;
		if (num == 0) {
			return str + "8000000000000000";
		}
		String returnStr = "8000000000000000".substring(0, 16 - num);
		return str + returnStr;
	}

	public static String pboc(String key, String lsData) {
		if (key.length() != 32 || lsData.length() != 16) {
			return null;
		}
		String temp = TripDESencrypt(key, lsData);
		temp += TripDESencrypt(key, ConvertUtil.xHex(lsData));
		return temp;
	}

	public static String getCommMac(String phoneNo, String cardNo,
                                    String apduRet) {
		String key = "00112233445566778899AABBCCDDEEFF";
		String lsKey = pboc(key, cardNo);
		String mac = ECB_TripDESmac(lsKey, complete80ForMac("0" + phoneNo
				+ apduRet), "0000000000000000");
		if (mac == null) {
			return null;
		}
		return mac.substring(0, 8);
	}

	public static void main(String[] args) {
		// C8DCA75861E2F1B5 A7BFF2A715DF02D3
		// System.out.println(AppDes.getCommMac("13820022002",
		// "3000140098988888",
		// "0000000000000100EAB5C7B603A33F88").toUpperCase());
		// System.out.println(AppDes.ECB_TripDESencrypt("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF",
		// AppDes.complete80ForDes("083100000020000001")));
	}

}
