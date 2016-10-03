package mr.user.domain;

public class User {
	private String uid;
	private String uname;
	private String upassword;
	private String repassword;
	private String uemail;
	private String ucode;
	private boolean ustatus;
	private int postnumber;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUcode() {
		return ucode;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public boolean isUstatus() {
		return ustatus;
	}
	public void setUstatus(boolean ustatus) {
		this.ustatus = ustatus;
	}
	public int getPostnumber() {
		return postnumber;
	}
	public void setPostnumber(int postnumber) {
		this.postnumber = postnumber;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upassword="
				+ upassword + ", repassword=" + repassword + ", uemail="
				+ uemail + ", ucode=" + ucode + ", ustatus=" + ustatus
				+ ", postnumber=" + postnumber + "]";
	}
}
