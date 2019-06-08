package springboot.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springboot.login.domain.*;
import com.alibaba.fastjson.JSONArray;

import springboot.login.domain.User;
import springboot.login.domain.manger_user;
import springboot.login.service.LoginService;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
/**
 * Created by huangds on 2017/10/24.
 */
@EnableAutoConfiguration
@Controller
public class LoginController {
	@Autowired
    private SinaDBManager sinaBoDBManager;

    @Autowired
    private LoginService loginService;
    @Autowired
    private userDao userDao ;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account,Model model){

        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/manger_login")
    public String manger_login(){
        return "manger_login";
    }
    @ResponseBody/*这注解表明操作完直接在该页面显示删除成功或失败*/
    @RequestMapping("/delete") /*点删除按钮跳转到这*/
    public String delete(String id){
        int a=userDao.deletebyid(id);/*调用userDao的deletebyid方法根据id进行删除操作*/
        if(a>0){
            return "删除成功"+"<a href='/data'>继续处理</a>"+"----"+"<a href='/login'>返回登录界面</a>";
        }
        else{return "删除失败"+"<a href='/data'>返回主页</a>";
        }
    }

    @GetMapping("/model1")
    public String model1(){
        return "model1";
    }

    @GetMapping("/user_data")
    public  String home (Model model){
       model.addAttribute("users",userDao.findAll());
       return "user_data";
   }
    @GetMapping("/database")
    public  String hot_data (Model model){
       model.addAttribute("result1",userDao.getHotWordsList2());
       return "database";
   }
    @GetMapping("/search_data")
   public  String search_data1 (Model model){
      model.addAttribute("result2",userDao.search_data_all());
      return "search_data";
  }

	
	  @RequestMapping("/manger_loginVerify") 	//管理员登录
	  public String manger_loginVerify(manger_user a,Model model) { 
	  manger_user manger_user1=userDao.findAll1(a); 
	  if(manger_user1!=null) {
	  model.addAttribute("users",userDao.findAll()); 
	  return "manger_data";} 
	  else { 
		  return
	  "/loser"; 
		  }
	  
	  }
	/*
	 * @RequestMapping("/searchAction") public String searchAction(String key) {
	 * return "result"; }
	 */
	
	/*
	 * @RequestMapping("/manger_loginVerify") public String loginVerify(String
	 * username,String password,Model model){ User user = new User();
	 * user.setUsername(username); user.setPassword(password); boolean verify =
	 * loginService.verifyLogin(username, password); if (verify) {
	 * model.addAttribute("users",userDao.findAll()); return "data"; } else { return
	 * "/loser"; } }
	 */
	 
	  @RequestMapping("/loginVerify")	//登录界面
    public String loginVerify(String username,String password,HttpSession session){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean verify = loginService.verifyLogin(username,password);
        if (verify) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            return "index";
        } else {
            return "/loser";
        }
    }
	 @GetMapping("/register1") 
	  public String insert(){
		 
		  return "register1"; 
		  }
	 @RequestMapping("/searchAction")		//input的搜索功能
	 
	 public String searchAction(String search,Model model) {
		 
		/*
		 * model.addAttribute("users",userDao.findAll()); return "data";
		 */
		 List<Map<String, Object>> list = userDao.findbyId(search);
		 int key=list.size();
		/* list.add(new Map<String, Object>("data",key)); */
		 Map<String, Object> aMap=new HashMap<>();
		 aMap.put("data", ">>>>sory后台无相关数据");
		 List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
		 list1.add(aMap);
		 if (list.size()==0) {
			 model.addAttribute("result1",list1);
		 }else {
		 model.addAttribute("result1",list); }
         return "database";
	 }

	 @RequestMapping("/registerAction")//注册功能
	  
	   @ResponseBody
	 
	    public String addAction (String userps,String username,String password,String id,String userid) {
		 
			   int raw = userDao.add(userps,username,password,id,userid);
				 
	            if(raw>0){
	            	return"注册成功"+'\n'+"<a href='/login.html'>返回登录界面</a>";
	            }
	            else{return "注册失败"+"<a href='/register.html'>重新注册</a>";
	            }
	    }
	 @RequestMapping("/wordcloud")  //词云展示功能
	    String getHotWords(ModelMap modelMap) {
	        JSONArray jsonArray = sinaBoDBManager.getHotWordsJsonArray();

	        modelMap.put("result", jsonArray.toJSONString());
	        return "wordcloud";
	    }
		@Autowired
	    private SinaDBManager sinaBoDBManager2;
	 @RequestMapping("/wordcloud2")  //词云展示功能
	    String getHotWords2(ModelMap modelMap) {
	        JSONArray jsonArray = sinaBoDBManager2.getHotWordsJsonArray2();

	        modelMap.put("result", jsonArray.toJSONString());
	        return "wordcloud2";
	    }
		@Autowired
	    private SinaDBManager sinaBoDBManager3;
		 @RequestMapping("/wordcloud3")  //词云展示功能
		    String getHotWords3(ModelMap modelMap) {
		        JSONArray jsonArray = sinaBoDBManager3.getHotWordsJsonArray();

		        modelMap.put("result", jsonArray.toJSONString());
		        return "wordcloud3";
		    }
	 @RequestMapping("/model2") 	//柱形图	
	 String getMount(ModelMap modelMap1){
	        JSONArray jsonArray = sinaBoDBManager.getMountJsonArray();

	        modelMap1.put("result", jsonArray.toJSONString());
	        return "model2";
	    }

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/insertAction")
	 * 
	 * public String addAction ( User user) { int raw = user.add(user); if(raw>0){
	 * return "注册成功"+'\n'+"<a href='/home'>返回用户数据库</a>"; } else{return
	 * "注册失败"+"<a href='/home'>返回主页</a>"; } }
	 */

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "index";
    }
}
