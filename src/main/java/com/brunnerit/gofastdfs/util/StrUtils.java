package com.brunnerit.gofastdfs.util;

import java.util.UUID;

/**
 * String类型操作工具类
 *
 */
public final class StrUtils {

	/**
	 * 获取UUID
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 判断字符串是否为空值
	 * 
	 * @param str
	 * @return Boolean
	 */
	public static Boolean isBlank(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	public static Boolean notBlank(String s) {
		return !isBlank(s);
	}
	
    /**
     * 	两个字符串比较
     * @param str1
     * @param str2
     * @return 不相同 false，相同 true
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            if (str2 != null)
                return false;
        } else if (!str1.equals(str2))
            return false;
        return true;
    }

}
