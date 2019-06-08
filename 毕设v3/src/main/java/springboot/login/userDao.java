package springboot.login;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.login.domain.User;
import springboot.login.domain.manger_user;
@Repository
public class userDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public int add(String userps,String username,String password,String id,String userid ){
        String sql="insert into denglu values(?,?,?,?,?)";
        return jdbcTemplate.update(sql,userps,username,password,id,userid);
}

    public List<Map<String, Object>> getHotWordsList2() {
        String sql = "select * from sina_hot_words";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    public List<Map<String, Object>> search_data_all() {
        String sql = "select * from sinanews1 ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    public List<User> findAll(){ /*查询语句将表的很多个对象放入List集合*/
        return jdbcTemplate.query("select * from denglu",new Object[]{},new BeanPropertyRowMapper<User>(User.class));
    }
  
    
    public manger_user findAll1(manger_user a){ /*查询语句将表的很多个对象放入List集合*/
    	BeanPropertyRowMapper<manger_user> rowMapper = new BeanPropertyRowMapper<>(manger_user.class);
          String sql="select * from manger_denglu where username=? and password=?";
          return  jdbcTemplate.queryForObject(sql,rowMapper,a.getUsername(),a.getPassword());
    	/*
		 * String sql="select * from manger_denglu where username=?"; return
		 * jdbcTemplate.query(sql,new Object[]{},new
		 * BeanPropertyRowMapper<manger_user>(manger_user.class));
		 */
    }
    
    public int   deletebyid(String id){/*通过id找到该对象实现删除操作*/
        String sql="delete from denglu where id=? ";
        int a= jdbcTemplate.update(sql,id);
        return a;
    }
/**
 * 搜索框的模糊查询
 * **/
	public List<Map<String, Object>> findbyId(String key) {
		 String sql = "select title,newsTime from sinanews2 where title like ?";
	       List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,"%"+key+"%");
	       return list;
	}


}