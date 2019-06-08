package springboot.login.domain;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 注解声明,可以自动引入其它类、或被其它类引入
 */
@Component
public class SinaDBManager {
   @Autowired
   private JdbcTemplate jdbcTemplate;
  
   public List<Map<String, Object>> getHotWordsList() {
       String sql = "select * from sina_hot_words";
       List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
       return list;
   }
   public List<Map<String, Object>> getHotWordsList2() {
       String sql = "select * from sina_hot_words2";
       List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
       return list;
   }
   public List<Map<String, Object>> getHotWordsList3() {
       String sql = "select * from sina_hot_words";
       List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
       return list;
   }
   public List<Map<String, Object>> getMountList() {
       String sql = "select * from day_mount";
       List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
       return list;
   }
   public List<Map<String, Object>> getHotWordsList1() {
       String sql = "select * from sina_hot_words";
       List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
       return list;
   }
   public JSONArray getMountJsonArray() {
       //得到List集合形式的数据
       List<Map<String, Object>> list = this.getMountList();
       //将list转化成json array
       JSONArray jsonArray=new JSONArray();
       JSONObject jsonObj=null;
       for (Map<String, Object> map : list) {
           jsonObj=new JSONObject();
           Object word=map.get("time");
           Object freq=map.get("mount");
          
           jsonObj.put("name",word.toString());
           jsonObj.put("value",freq.toString());
           jsonArray.add(jsonObj);
       }
       return jsonArray;
   }
 //得到json array形式的数据
   public JSONArray getHotWordsJsonArray() {
       //得到List集合形式的数据
       List<Map<String, Object>> list = this.getHotWordsList();
       //将list转化成json array
       JSONArray jsonArray=new JSONArray();
       JSONObject jsonObj=null;
       for (Map<String, Object> map : list) {
           jsonObj=new JSONObject();
           Object word=map.get("word");
           Object freq=map.get("freq");
          
           jsonObj.put("name",word.toString());
           jsonObj.put("value",freq.toString());
           jsonArray.add(jsonObj);
       }
       return jsonArray;
   }
   public JSONArray getHotWordsJsonArray2() {
       //得到List集合形式的数据
       List<Map<String, Object>> list = this.getHotWordsList2();
       //将list转化成json array
       JSONArray jsonArray=new JSONArray();
       JSONObject jsonObj=null;
       for (Map<String, Object> map : list) {
           jsonObj=new JSONObject();
           Object word=map.get("word");
           Object freq=map.get("freq");
          
           jsonObj.put("name",word.toString());
           jsonObj.put("value",freq.toString());
           jsonArray.add(jsonObj);
       }
       return jsonArray;
   }
}