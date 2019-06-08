package club.p9j7.mapper;

import club.p9j7.model.SinaNews;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface SinaNewsMapper{

    @Insert("insert into sinanews2(title,content,keyword,newsTime) value (#{title},#{content},#{keyword},#{newsTime})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertNews(SinaNews sinaNews);
}
