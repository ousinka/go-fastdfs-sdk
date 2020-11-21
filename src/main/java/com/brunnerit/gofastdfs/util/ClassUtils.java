package com.brunnerit.gofastdfs.util;

/**
 * 
 * @author zhenhua wang
 *
 */
public class ClassUtils {
	
    /**
     *	 确定class是否可以被加载
     * @param className 完整类名
     * @param classLoader 类加载
     * @return {boolean}
     */
    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            Class.forName(className, true, classLoader);
            return true;
        }
        catch (Throwable ex) {
            return false;
        }
    }
}
