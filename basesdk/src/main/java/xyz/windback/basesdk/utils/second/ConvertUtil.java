package xyz.windback.basesdk.utils.second;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wjj on 14-2-24.
 */
public class ConvertUtil {

    public static byte[] hexstr2byte(String cmd) {
        cmd = cmd.replaceAll(" ", "");
        byte[] res = new byte[cmd.length() / 2];
        for (int i = 0; i < res.length; i++) {
            res[i] = (byte) Integer.parseInt(cmd.substring(i * 2, i * 2 + 2), 16);
        }
        return res;
    }

    public static String byte2hex(byte[] bytes) {
        String res = "";
        for (int i = 0; i < bytes.length; i++) {
            res += shex(bytes[i]);
        }
        return res;
    }

    public static String shex(byte b) {
        String temp = Integer.toHexString(b);
        if (temp.length() == 2) {
            return temp;
        } else if (temp.length() == 1) {
            return "0" + temp;
        } else if (temp.length() > 2) {
            return temp.substring(temp.length() - 2, temp.length());
        }
        return null;
    }

    public static String trimspace(String src) {
        String ret = "";
        for (int i = 0; i < src.length(); i++) {
            if (' ' != src.charAt(i)) {
                ret += src.charAt(i);
            }
        }
        return ret;
    }

    public static String complete00(String src, int toLen, String str, boolean isHead) {
        StringBuffer ret = new StringBuffer(src);
        int temp = toLen - src.length();
        for (int i = 0; i < temp; i++) {
            if (isHead) {
                ret.insert(0, str);
            } else {
                ret.append(str);
            }
        }
        return ret.toString();
    }

    public static String complete00(String src, int len) {
        return complete00(src, len, "0", true);
    }

    public static String xHex(String lsData) {
        String xLsData = "";
        for (int i = 0; i < lsData.length(); i++) {
            xLsData += Integer.toHexString(15 - Integer.parseInt(lsData.substring(i, i + 1), 16));
        }
        return xLsData;
    }

    public static String xHex(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return null;
        }
        byte[] b1 = hexstr2byte(s1);
        byte[] b2 = hexstr2byte(s2);
        byte[] res = new byte[b1.length];
        for (int i = 0; i < b2.length; i++) {
            res[i] = (byte) (b1[i] ^ b2[i]);
        }
        return byte2hex(res);
    }

    public static String shex(int val) {
        String temp = Long.toHexString(val);
        String ret = "";
        switch (temp.length()) {
            case 1:
                ret = "0" + temp;
                break;

            default:
                ret = temp.substring(temp.length() - 2, temp.length());
                break;
        }
        return ret;
    }

    /**
     * �ֽ�����
     *
     * @param data
     * @return
     */
    public static String invertSequence(String data) {
        if (data.length() % 2 != 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length() / 2; i++) {
            sb.append(data.substring(data.length() - 2 * i - 2, data.length() - 2 * i));
        }
        return sb.toString();
    }

    public static <T, V> JSONObject toJSONObject(T obg, Class<V> tClass) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            return new JSONObject(gson.toJson(obg, tClass));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
