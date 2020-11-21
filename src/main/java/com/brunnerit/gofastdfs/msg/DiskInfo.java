package com.brunnerit.gofastdfs.msg;

/**
 * 
 * @author zhenhua wang
 *
 */
public class DiskInfo {
	private long free;
	private String fstype;
	private int inodesFree;
	private int inodesTotal;
	private int inodesUsed;
	private int inodesUsedPercent;
	private String path;
	private long total;
	private long used;
	private double usedPercent;

	public void setFree(long free) {
		this.free = free;
	}

	public long getFree() {
		return free;
	}

	public void setFstype(String fstype) {
		this.fstype = fstype;
	}

	public String getFstype() {
		return fstype;
	}

	public void setInodesFree(int inodesFree) {
		this.inodesFree = inodesFree;
	}

	public int getInodesFree() {
		return inodesFree;
	}

	public void setInodesTotal(int inodesTotal) {
		this.inodesTotal = inodesTotal;
	}

	public int getInodesTotal() {
		return inodesTotal;
	}

	public void setInodesUsed(int inodesUsed) {
		this.inodesUsed = inodesUsed;
	}

	public int getInodesUsed() {
		return inodesUsed;
	}

	public void setInodesUsedPercent(int inodesUsedPercent) {
		this.inodesUsedPercent = inodesUsedPercent;
	}

	public int getInodesUsedPercent() {
		return inodesUsedPercent;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getUsed() {
		return used;
	}

	public void setUsedPercent(double usedPercent) {
		this.usedPercent = usedPercent;
	}

	public double getUsedPercent() {
		return usedPercent;
	}
}
