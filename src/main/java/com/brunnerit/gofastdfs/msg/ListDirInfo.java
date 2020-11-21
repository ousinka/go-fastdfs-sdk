package com.brunnerit.gofastdfs.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author zhenhua wang
 *
 */
public class ListDirInfo {
	@JsonProperty("is_dir")
	private boolean isDir;
	private String md5;
	private long mtime;
	private String name;
	private String path;
	private int size;

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getMd5() {
		return md5;
	}

	public void setMtime(long mtime) {
		this.mtime = mtime;
	}

	public long getMtime() {
		return mtime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
