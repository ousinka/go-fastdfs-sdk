package com.brunnerit.gofastdfs.msg;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 系统信息 status接口返回值
 * 
 * @author zhenhua wang
 *
 */
public class Status {

	@JsonProperty("Fs.AutoRepair")
	private boolean fsAutoRepair;

	@JsonProperty("Fs.FileStats")
	private List<FileStats> fsFileStats;

	@JsonProperty("Fs.Local")
	private String fsLocal;

	@JsonProperty("Fs.Peers")
	private List<String> fsPeers;

	@JsonProperty("Fs.QueueFileLog")
	private int fsQueueFileLog;

	@JsonProperty("Fs.QueueFromPeers")
	private int fsQueueFromPeers;

	@JsonProperty("Fs.QueueToPeers")
	private int fsQueueToPeers;

	@JsonProperty("Fs.QueueUpload")
	private int fsQueueUpload;

	@JsonProperty("Fs.RefreshInterval")
	private int fsRefreshInterval;

	@JsonProperty("Fs.ShowDir")
	private boolean fsShowDir;

	@JsonProperty("Sys.Alloc")
	private long SysAlloc;

	@JsonProperty("Sys.DiskInfo")
	private DiskInfo SysDiskInfo;

	@JsonProperty("Sys.Frees")
	private long SysFrees;

	@JsonProperty("Sys.GCCPUFraction")
	private double sysGCCPUFraction;

	@JsonProperty("Sys.GCSys")
	private long sysGCSys;

	@JsonProperty("Sys.HeapAlloc")
	private long sysHeapAlloc;

	@JsonProperty("Sys.HeapObjects")
	private int sysHeapObjects;

	@JsonProperty("Sys.MemInfo")
	private MemInfo SysMemInfo;

	@JsonProperty("Sys.NumCpu")
	private int sysNumCpu;

	@JsonProperty("Sys.NumGC")
	private int sysNumGC;

	@JsonProperty("Sys.NumGoroutine")
	private int sysNumGoroutine;

	@JsonProperty("Sys.TotalAlloc")
	private long sysTotalAlloc;

	public boolean isFsAutoRepair() {
		return fsAutoRepair;
	}

	public void setFsAutoRepair(boolean fsAutoRepair) {
		this.fsAutoRepair = fsAutoRepair;
	}

	public List<FileStats> getFsFileStats() {
		return fsFileStats;
	}

	public void setFsFileStats(List<FileStats> fsFileStats) {
		this.fsFileStats = fsFileStats;
	}

	public String getFsLocal() {
		return fsLocal;
	}

	public void setFsLocal(String fsLocal) {
		this.fsLocal = fsLocal;
	}

	public List<String> getFsPeers() {
		return fsPeers;
	}

	public void setFsPeers(List<String> fsPeers) {
		this.fsPeers = fsPeers;
	}

	public int getFsQueueFileLog() {
		return fsQueueFileLog;
	}

	public void setFsQueueFileLog(int fsQueueFileLog) {
		this.fsQueueFileLog = fsQueueFileLog;
	}

	public int getFsQueueFromPeers() {
		return fsQueueFromPeers;
	}

	public void setFsQueueFromPeers(int fsQueueFromPeers) {
		this.fsQueueFromPeers = fsQueueFromPeers;
	}

	public int getFsQueueToPeers() {
		return fsQueueToPeers;
	}

	public void setFsQueueToPeers(int fsQueueToPeers) {
		this.fsQueueToPeers = fsQueueToPeers;
	}

	public int getFsQueueUpload() {
		return fsQueueUpload;
	}

	public void setFsQueueUpload(int fsQueueUpload) {
		this.fsQueueUpload = fsQueueUpload;
	}

	public int getFsRefreshInterval() {
		return fsRefreshInterval;
	}

	public void setFsRefreshInterval(int fsRefreshInterval) {
		this.fsRefreshInterval = fsRefreshInterval;
	}

	public boolean isFsShowDir() {
		return fsShowDir;
	}

	public void setFsShowDir(boolean fsShowDir) {
		this.fsShowDir = fsShowDir;
	}

	@JsonProperty("Sys.Alloc")
	public long getSysAlloc() {
		return SysAlloc;
	}

	public void setSysAlloc(long sysAlloc) {
		SysAlloc = sysAlloc;
	}

	@JsonProperty("Sys.DiskInfo")
	public DiskInfo getSysDiskInfo() {
		return SysDiskInfo;
	}

	public void setSysDiskInfo(DiskInfo sysDiskInfo) {
		SysDiskInfo = sysDiskInfo;
	}

	@JsonProperty("Sys.Frees")
	public long getSysFrees() {
		return SysFrees;
	}

	public void setSysFrees(long sysFrees) {
		SysFrees = sysFrees;
	}

	public double getSysGCCPUFraction() {
		return sysGCCPUFraction;
	}

	public void setSysGCCPUFraction(double sysGCCPUFraction) {
		this.sysGCCPUFraction = sysGCCPUFraction;
	}

	public long getSysGCSys() {
		return sysGCSys;
	}

	public void setSysGCSys(long sysGCSys) {
		this.sysGCSys = sysGCSys;
	}

	public long getSysHeapAlloc() {
		return sysHeapAlloc;
	}

	public void setSysHeapAlloc(long sysHeapAlloc) {
		this.sysHeapAlloc = sysHeapAlloc;
	}

	public int getSysHeapObjects() {
		return sysHeapObjects;
	}

	public void setSysHeapObjects(int sysHeapObjects) {
		this.sysHeapObjects = sysHeapObjects;
	}

	@JsonProperty("Sys.MemInfo")
	public MemInfo getSysMemInfo() {
		return SysMemInfo;
	}

	public void setSysMemInfo(MemInfo sysMemInfo) {
		SysMemInfo = sysMemInfo;
	}

	public int getSysNumCpu() {
		return sysNumCpu;
	}

	public void setSysNumCpu(int sysNumCpu) {
		this.sysNumCpu = sysNumCpu;
	}

	public int getSysNumGC() {
		return sysNumGC;
	}

	public void setSysNumGC(int sysNumGC) {
		this.sysNumGC = sysNumGC;
	}

	public int getSysNumGoroutine() {
		return sysNumGoroutine;
	}

	public void setSysNumGoroutine(int sysNumGoroutine) {
		this.sysNumGoroutine = sysNumGoroutine;
	}

	public long getSysTotalAlloc() {
		return sysTotalAlloc;
	}

	public void setSysTotalAlloc(long sysTotalAlloc) {
		this.sysTotalAlloc = sysTotalAlloc;
	}

}
