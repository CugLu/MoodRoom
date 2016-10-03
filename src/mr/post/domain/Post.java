package mr.post.domain;

import java.util.Date;

public class Post {
	private String pid;
	private String uname;
	private String ptitle;
	private String pimage;
	private String pcontent;
	private int pzan;
	private int pcomment;
	private Date ptime;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public int getPzan() {
		return pzan;
	}
	public void setPzan(int pzan) {
		this.pzan = pzan;
	}
	public int getPcomment() {
		return pcomment;
	}
	public void setPcomment(int pcomment) {
		this.pcomment = pcomment;
	}
	public Date getPtime() {
		return ptime;
	}
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}
	@Override
	public String toString() {
		return "Post [pid=" + pid + ", uname=" + uname + ", ptitle=" + ptitle
				+ ", pimage=" + pimage + ", pcontent=" + pcontent + ", pzan="
				+ pzan + ", pcomment=" + pcomment + ", ptime=" + ptime + "]";
	}
	
}
