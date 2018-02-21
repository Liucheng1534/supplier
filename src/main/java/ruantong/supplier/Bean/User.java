package ruantong.supplier.Bean;

public class User {
	
	private Integer uid;
	private String username;
	private String password;
	private String email;
	private String registime;
	
	
	
	public Integer getUid() {
		return uid;
	}



	public void setUid(Integer uid) {
		this.uid = uid;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRegistime() {
		return registime;
	}



	public void setRegistime(String registime) {
		this.registime = registime;
	}

	

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", registime=" + registime + "]";
	}



	public User(Integer uid, String username, String password, String email, String registime) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.registime = registime;
	}



	public User() {
		super();
	}
	
}
