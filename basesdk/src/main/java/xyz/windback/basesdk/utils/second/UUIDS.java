package xyz.windback.basesdk.utils.second;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import xyz.windback.basesdk.utils.PreferencesUtils;
import xyz.windback.basesdk.utils.Utils;

/**
 * 生成APP唯一识别号
 * Created by khj on 2017-12-1.
 */

public class UUIDS {
    private static final String TAG = UUIDS.class.getName();
    private static UUIDS device;
    //    private static Context context;
    private final static String DEFAULT_FILE_NAME = "com.example.wangchang.fulv";//不同的项目必须修改
    private final static String DEFAULT_DEVICE_ID = "dervice_id";
    private final static String FILE_ANDROID = Environment.getExternalStoragePublicDirectory("Android") + File.separator + DEFAULT_FILE_NAME;
    private final static String FILE_DCIM = Environment.getExternalStoragePublicDirectory("DCIM") + File.separator + DEFAULT_FILE_NAME;


    public static UUIDS buidleID() {
        if (device == null) {
            synchronized (UUIDS.class) {
                if (device == null) {
                    device = new UUIDS();
                }
            }
        }
        return device;
    }

    public static String getUUID() {
        if (PreferencesUtils.getString(Utils.getContext(), DEFAULT_DEVICE_ID) != null) {
            return PreferencesUtils.getString(Utils.getContext(), DEFAULT_DEVICE_ID);
        }
        return createUUID();
    }

    //生成一个128位的唯一标识符
    private static String createUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    public void check() {
        String uuid = PreferencesUtils.getString(Utils.getContext(), DEFAULT_DEVICE_ID);
        if (uuid == null) {
            if (checkAndroidFile() == null && checkDCIMFile() == null) {
                uuid = createUUID();
                saveAndroidFile(uuid);
                saveDCIMFile(uuid);
                Log.e(TAG, "new devices,create only id");
            }
            if (checkAndroidFile() == null) {
                uuid = checkDCIMFile();
                saveAndroidFile(uuid);
                Log.e(TAG, "Android directory was not found in UUID, from the DCIM directory to take out UUID");
            }
            if (checkDCIMFile() == null) {
                uuid = checkAndroidFile();
                saveDCIMFile(uuid);
                Log.e(TAG, "DCIM directory was not found in UUID, from the Android directory to take out UUID");
            }
            uuid = checkAndroidFile();
            PreferencesUtils.putString(Utils.getContext(), DEFAULT_DEVICE_ID, uuid);
            Log.e(TAG, "save uuid SharePref:" + uuid);
        } else {
            if (checkAndroidFile() == null) {
                saveAndroidFile(uuid);
            }
            if (checkDCIMFile() == null) {
                saveDCIMFile(uuid);
            }
        }
        Log.d(TAG, "result uuid:" + uuid);
    }

    private static String checkAndroidFile() {
        BufferedReader reader = null;
        try {
            File file = new File(FILE_ANDROID);
            reader = new BufferedReader(new FileReader(file));
            return reader.readLine();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveAndroidFile(String id) {
        try {
            File file = new File(FILE_ANDROID);
            FileWriter writer = new FileWriter(file);
            writer.write(id);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String checkDCIMFile() {
        BufferedReader reader = null;
        try {
            File file = new File(FILE_DCIM);
            reader = new BufferedReader(new FileReader(file));
            return reader.readLine();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveDCIMFile(String id) {
        try {
            File file = new File(FILE_DCIM);
            FileWriter writer = new FileWriter(file);
            writer.write(id);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
