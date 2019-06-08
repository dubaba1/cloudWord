package springboot.login.domain;

import javax.persistence.*;

/**
 * Created by huangds on 2017/10/28.
 */
@Entity
@Table(name="denglu")
public class User {
	/*
	 * controller代码解释：loginVerify是对登录请求到数据库中进行验证用户名和密码，
	 * 
	 * 验证通过以后设置session，否则跳转到登录页面。@GetMapping是一个组合注解， 是@RequestMapping(method =
	 * RequestMethod.GET)的缩写,@PostMapping同理。 ps:实际项目登录验证会使用登录验证框架：spring security
	 * 、shiro等， 以及登录过程密码加密传输保存等，这里仅仅用于了解。
	 */

    @Id//数据库的主键
    @Column(name = "id")	//对应数据库的属性
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 
    private String id;
	@Column(name = "userid") 
	private String userid;
    @Column(name = "username")
    private String username;
    @Override
	public String toString() {
		return "User [id=" + id + ", userid=" + userid + ", username=" + username + ", password=" + password
				+ ", userps=" + userps + "]";
	}
	@Column(name = "password")
    private String password;
	  @Column(name = "userps") 
	  private String userps;
	  public String getId() { return id; }
	  
	  public void setId(String id) { this.id = id; }
	
	  public String getUserps() { return userps; }
	  
	  public void setUserps(String userps) { this.userps = userps; }
	  
	  public String getUserid() { return userid; }
	  
	  public void setUserid(String userid) { this.userid = userid; }
	 

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
}
