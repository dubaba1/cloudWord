package springboot.login.domain;

public class manger_user {
	 private String username;
	 private String password;
	@Override
	public String toString() {
		return "manger_user [username=" + username + ", password=" + password + "]";
	}
	public Object getUsername() {
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
	
}
