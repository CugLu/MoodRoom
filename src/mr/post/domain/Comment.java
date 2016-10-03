package mr.post.domain;

public class Comment {
	private String cid;
	private String pid;
	private String uname;
	private String ccomment;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
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
	public String getCcomment() {
		return ccomment;
	}
	public void setCcomment(String ccomment) {
		this.ccomment = ccomment;
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", pid=" + pid + ", uname=" + uname
				+ ", ccomment=" + ccomment + "]";
	}
	
}
