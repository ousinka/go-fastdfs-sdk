package com.brunnerit.gofastdfs.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 
 * @author zhenhua wang
 *
 */
public class IoUtils {
	
	/**
	 * 关闭流
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
		}
	}
}
