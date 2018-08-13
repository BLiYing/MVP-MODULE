package xyz.windback.basesdk.utils.second;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassPathResource {

    /*
     * 判断是否为手机号码
     */
    public static boolean isMobileNO(String mobiles) {

//		Pattern p = Pattern
//		.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$");

        //TODO 新的号码
        Pattern p = Pattern
                .compile("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[06-8])\\d{8}$");


        Matcher m = p.matcher(mobiles);
//		System.out.println(m.matches() + "---");
        return m.matches();

    }

    /*
     * 判断是否为邮箱
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        //String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

	/*
     * 判断特殊条件(密码不包括除数字，字母以外的字符)
	 */

    public static boolean isPasswordOK(String strPass) {

        // ^[A-Za-z]+　　 匹配由26个英文字母组成的字符串
        // ^[A-Z]+　　 匹配由26个英文字母的大写组成的字符串
        // ^[a-z]+　　 匹配由26个英文字母的小写组成的字符串
        // ^[A-Za-z0-9]+　　匹配由数字和26个英文字母组成的字符串
        // ^\w+　　 匹配由数字、26个英文字母或者下划线组成的字符串

        String strPattern = "^[A-Za-z0-9]+";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strPass);
        return m.matches();
    }

    public static boolean checkPass(String strPass) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(strPass);
        return m.matches();
//        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    }


    /*
     * 判断是否为邮编
     */
    public static boolean isPostCode(String postcode) {
        Pattern p = Pattern
                .compile("^\\d{6}$");
        Matcher m = p.matcher(postcode);
        return m.matches();

    }

    /**
     * 判断是否是有效的真实姓名
     */
    public static boolean isTureName(String name) {
        Pattern p = Pattern
                .compile("^([\\u0391-\\uFFE5A-Za-z]){1,20}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**
     * 判断是否是有效的昵称
     */
    public static boolean isNickName(String name) {
        Pattern p = Pattern
                .compile("^([^\\d][\u0391-\\uFFE5A-Za-z]*\\d*\\w*){1,25}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**
     * 判断是否是有效的车牌
     */
    public static boolean isEffectivePlate(String plate) {
        String express = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
        Pattern p = Pattern.compile(express);
        Matcher m = p.matcher(plate);
        return m.matches();
    }


}
