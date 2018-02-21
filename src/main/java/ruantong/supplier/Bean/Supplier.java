package ruantong.supplier.Bean;


import ruantong.supplier.Util.Comment;

public class Supplier {

	@Comment("供应商编号")
	private String supplierid;		//供应商编号
	@Comment("供应商名称")
	private String suppliername;	//供应商名称
	@Comment("供应法定代表人")
	private String supplylegaler;	//供应法定代表人
	@Comment("业务联系人")
	private String businesser;		//业务联系人
	@Comment("联系方式")
	private String phonenumber;		//联系方式
	@Comment("营业执照编号")
	private String licenseid;		//营业执照编号
	@Comment("税务登记编号")
	private String revenueid;		//税务登记编号
	@Comment("注册时间")
	private String registertime;	//注册时间
	
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getSupplylegaler() {
		return supplylegaler;
	}
	public void setSupplylegaler(String supplylegaler) {
		this.supplylegaler = supplylegaler;
	}
	public String getBusinesser() {
		return businesser;
	}
	public void setBusinesser(String businesser) {
		this.businesser = businesser;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getLicenseid() {
		return licenseid;
	}
	public void setLicenseid(String licenseid) {
		this.licenseid = licenseid;
	}
	public String getRevenueid() {
		return revenueid;
	}
	public void setRevenueid(String revenueid) {
		this.revenueid = revenueid;
	}
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public Supplier(String supplierid, String suppliername, String supplylegaler, String businesser, String phonenumber, String licenseid, String revenueid, String registertime) {
		this.supplierid = supplierid;
		this.suppliername = suppliername;
		this.supplylegaler = supplylegaler;
		this.businesser = businesser;
		this.phonenumber = phonenumber;
		this.licenseid = licenseid;
		this.revenueid = revenueid;
		this.registertime = registertime;
	}

	@Override
	public String toString() {
		return "Supplier [supplierid=" + supplierid + ", suppliername=" + suppliername + ", supplylegaler="
				+ supplylegaler + ", businesser=" + businesser + ", phonenumber=" + phonenumber + ", licenseid="
				+ licenseid + ", revenueid=" + revenueid + ", registertime=" + registertime + "]";
	}

	public Supplier() {
	}
}
