package xyz.windback.basesdk.utils.https;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CertificateHttpUtil {

    // 证书的下载地址
//	 public static String URL =
//	 "http://ic.tcps.com.cn:9430/jnonline/j_c_fi/b3.cer"; // 测试

    /**
     * 证书生成环境
     */
    //测试证书
    public static String URL = "http://ic.tcps.com.cn:9430/jnonline/j_c_fi/gender.cer";
    //正式证书
//	public static String URL = "http://101.201.144.125/fbcapp/fbc.cer";

    public static String fileName = "tcpscard.cer";

    public static String getReString(Context context) {
        String str_req = null; // yes-获取到证书，false-未获取到证书

        try {
            URL url = new URL(URL);// 请求地址

            // 获取存放证书的地址
            String sdPath = getDiskCacheDir(context) + "/";

            File dir = new File(sdPath + "download/");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, fileName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
//			 conn.setConnectTimeout(5000);// 超时设置
//			 conn.setDoOutput(true);// 允许打开输入流给请求的资源传递参数
//			 conn.setDoInput(true);
//			 conn.setUseCaches(false);// 不使用缓存(POST请求不能使用缓存)
//			 conn.setRequestMethod("POST");// POST请求，默认是get请求
//			 conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
//			 conn.setRequestProperty("Charset", "UTF-8");
//			 conn.setRequestProperty("Content-Length",
//			 String.valueOf(xmlbyte.length));
//			 conn.setRequestProperty("Content-Type", "text/xml;
//			 charset=UTF-8");
//			 conn.setRequestProperty("X-ClientType", "2");// 发送自定义的头信息

            // 打开此资源的输出流。用于传递请求的内容（post请求，请求时可以携带参数）
            // conn.getOutputStream().write(xmlbyte);
            // conn.getOutputStream().flush();
            // conn.getOutputStream().close();

            url.openStream(); // 请求网络
            Log.e("0000", "---===>>>" + conn.getResponseCode());
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");

            InputStream is = conn.getInputStream();// 获取返回数据
            // 使用输出流来输出字符(可选)
            // ByteArrayOutputStream out = new ByteArrayOutputStream();

            OutputStream output = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            // 把获取到的文件保存到路径下
            while ((len = is.read(buf)) != -1) {
                output.write(buf, 0, len);
            }

            output.close();
            str_req = "yes";
        } catch (Exception e) {
            e.printStackTrace();
            str_req = "false";
            return str_req;
        }
        return str_req;
    }

    // 获取存文件的路径，如果存在SD卡，获取的路径是 /sdcard/Android/data/<application package>/cache
    // 。如果不存在SD卡，返回的路径是/data/data/<application package>/cache 。
    public static String getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }



}
