package com.brunnerit.gofastdfs.msg;

/**
 * 
 * @author zhenhua wang
 *
 */
public class FileStats {
	private String date;
	private int fileCount;
	private int totalSize;

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalSize() {
		return totalSize;
	}
}
