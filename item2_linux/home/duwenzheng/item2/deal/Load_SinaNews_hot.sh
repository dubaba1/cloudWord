#! /bin/sh
#设置所使用数据库
db_name=duwenzheng
#设置jar包位置
jar_path="../udf/TlHadoopCore-jar-with-dependencies.jar"
#设置udf classpath
class_path="com.tl.weibo_fenci.weibo_fenci"
#数据的来源表
from_table=sinanews_org
#要生成的数据表
to_table=sina_seg_result
#发起执行hql脚本
hive -e "
        use $db_name;
        add jar $jar_path;
        create temporary function seg as '$class_path';
        from $from_table
        insert overwrite table $to_table
select id,seg(title) as seg_title,newsTime,content;
"

