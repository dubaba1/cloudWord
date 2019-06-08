package springboot.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.login.LoginDao;
import springboot.login.domain.User;
import springboot.login.domain.manger_user;

import java.util.List;

/**
 * Created by huangds on 2017/10/28.
 */
@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

  public boolean verifyLogin(String username,String password){

     List<User> userList = loginDao.findByUsernameAndPassword(username, password);
      return userList.size()>0;
  }
}
