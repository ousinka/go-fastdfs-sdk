package com.brunnerit.gofastdfs.msg;

/**
 * 
 * @author zhenhua wang
 *
 */
public class MemInfo {
	private int active;
	private long available;
	private int buffers;
	private int cached;
	private int commitlimit;
	private int committedas;
	private int dirty;
	private int free;
	private int highfree;
	private int hightotal;
	private int hugepagesfree;
	private int hugepagesize;
	private int hugepagestotal;
	private int inactive;
	private int laundry;
	private int lowfree;
	private int lowtotal;
	private int mapped;
	private int pagetables;
	private int shared;
	private int slab;
	private int sreclaimable;
	private int sunreclaim;
	private int swapcached;
	private int swapfree;
	private int swaptotal;
	private long total;
	private long used;
	private int usedPercent;
	private int vmallocchunk;
	private int vmalloctotal;
	private int vmallocused;
	private int wired;
	private int writeback;
	private int writebacktmp;

	public void setActive(int active) {
		this.active = active;
	}

	public int getActive() {
		return active;
	}

	public void setAvailable(long available) {
		this.available = available;
	}

	public long getAvailable() {
		return available;
	}

	public void setBuffers(int buffers) {
		this.buffers = buffers;
	}

	public int getBuffers() {
		return buffers;
	}

	public void setCached(int cached) {
		this.cached = cached;
	}

	public int getCached() {
		return cached;
	}

	public void setCommitlimit(int commitlimit) {
		this.commitlimit = commitlimit;
	}

	public int getCommitlimit() {
		return commitlimit;
	}

	public void setCommittedas(int committedas) {
		this.committedas = committedas;
	}

	public int getCommittedas() {
		return committedas;
	}

	public void setDirty(int dirty) {
		this.dirty = dirty;
	}

	public int getDirty() {
		return dirty;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getFree() {
		return free;
	}

	public void setHighfree(int highfree) {
		this.highfree = highfree;
	}

	public int getHighfree() {
		return highfree;
	}

	public void setHightotal(int hightotal) {
		this.hightotal = hightotal;
	}

	public int getHightotal() {
		return hightotal;
	}

	public void setHugepagesfree(int hugepagesfree) {
		this.hugepagesfree = hugepagesfree;
	}

	public int getHugepagesfree() {
		return hugepagesfree;
	}

	public void setHugepagesize(int hugepagesize) {
		this.hugepagesize = hugepagesize;
	}

	public int getHugepagesize() {
		return hugepagesize;
	}

	public void setHugepagestotal(int hugepagestotal) {
		this.hugepagestotal = hugepagestotal;
	}

	public int getHugepagestotal() {
		return hugepagestotal;
	}

	public void setInactive(int inactive) {
		this.inactive = inactive;
	}

	public int getInactive() {
		return inactive;
	}

	public void setLaundry(int laundry) {
		this.laundry = laundry;
	}

	public int getLaundry() {
		return laundry;
	}

	public void setLowfree(int lowfree) {
		this.lowfree = lowfree;
	}

	public int getLowfree() {
		return lowfree;
	}

	public void setLowtotal(int lowtotal) {
		this.lowtotal = lowtotal;
	}

	public int getLowtotal() {
		return lowtotal;
	}

	public void setMapped(int mapped) {
		this.mapped = mapped;
	}

	public int getMapped() {
		return mapped;
	}

	public void setPagetables(int pagetables) {
		this.pagetables = pagetables;
	}

	public int getPagetables() {
		return pagetables;
	}

	public void setShared(int shared) {
		this.shared = shared;
	}

	public int getShared() {
		return shared;
	}

	public void setSlab(int slab) {
		this.slab = slab;
	}

	public int getSlab() {
		return slab;
	}

	public void setSreclaimable(int sreclaimable) {
		this.sreclaimable = sreclaimable;
	}

	public int getSreclaimable() {
		return sreclaimable;
	}

	public void setSunreclaim(int sunreclaim) {
		this.sunreclaim = sunreclaim;
	}

	public int getSunreclaim() {
		return sunreclaim;
	}

	public void setSwapcached(int swapcached) {
		this.swapcached = swapcached;
	}

	public int getSwapcached() {
		return swapcached;
	}

	public void setSwapfree(int swapfree) {
		this.swapfree = swapfree;
	}

	public int getSwapfree() {
		return swapfree;
	}

	public void setSwaptotal(int swaptotal) {
		this.swaptotal = swaptotal;
	}

	public int getSwaptotal() {
		return swaptotal;
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

	public void setUsedPercent(int usedPercent) {
		this.usedPercent = usedPercent;
	}

	public int getUsedPercent() {
		return usedPercent;
	}

	public void setVmallocchunk(int vmallocchunk) {
		this.vmallocchunk = vmallocchunk;
	}

	public int getVmallocchunk() {
		return vmallocchunk;
	}

	public void setVmalloctotal(int vmalloctotal) {
		this.vmalloctotal = vmalloctotal;
	}

	public int getVmalloctotal() {
		return vmalloctotal;
	}

	public void setVmallocused(int vmallocused) {
		this.vmallocused = vmallocused;
	}

	public int getVmallocused() {
		return vmallocused;
	}

	public void setWired(int wired) {
		this.wired = wired;
	}

	public int getWired() {
		return wired;
	}

	public void setWriteback(int writeback) {
		this.writeback = writeback;
	}

	public int getWriteback() {
		return writeback;
	}

	public void setWritebacktmp(int writebacktmp) {
		this.writebacktmp = writebacktmp;
	}

	public int getWritebacktmp() {
		return writebacktmp;
	}
}
