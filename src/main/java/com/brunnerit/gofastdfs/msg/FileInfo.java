package com.brunnerit.gofastdfs.msg;

import java.util.List;

/**
 * 
 * @author zhenhua wang
 *
 */
public class FileInfo {
	private String md5;
	private String name;
	private int offset;
	private String path;
	private List<String> peers;
	private String rename;
	private String scene;
	private int size;
	private long timeStamp;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getPeers() {
		return peers;
	}

	public void setPeers(List<String> peers) {
		this.peers = peers;
	}

	public String getRename() {
		return rename;
	}

	public void setRename(String rename) {
		this.rename = rename;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
