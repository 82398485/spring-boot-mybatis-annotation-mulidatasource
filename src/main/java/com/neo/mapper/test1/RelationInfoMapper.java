package com.neo.mapper.test1;

import com.neo.entity.po.RelationInfo;
import com.neo.entity.po.SubTaskInfo;
import com.neo.entity.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by songcj on 2018/10/10.
 */
public interface RelationInfoMapper {

    @Select("SELECT * FROM relationInfo")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "filePattern",  column = "filePattern"),
            @Result(property = "sheetIndex",  column = "sheetIndex"),
            @Result(property = "minStartRnum",  column = "minStartRnum"),
            @Result(property = "batchCount",  column = "batchCount"),
            @Result(property = "entityClass", column = "entityClass"),
            @Result(property = "mapperClass", column = "mapperClass"),
            @Result(property = "msg", column = "msg")
    })
    List<RelationInfo> getAll();

    @Select("SELECT * FROM relationInfo where id=#{id}")
    RelationInfo getRelationInfo(@Param(value = "id") int id);

    @Insert("INSERT INTO relationInfo(filePattern,sheetIndex,minStartRnum,batchCount,entityClass,mapperClass,msg) " +
            "VALUES(#{filePattern}, #{sheetIndex}, #{minStartRnum}, #{batchCount}, #{entityClass}, #{mapperClass}, #{msg})")
    void insert(RelationInfo relationInfo);

    @Update("UPDATE relationInfo SET filePattern=#{filePattern},sheetIndex=#{sheetIndex},minStartRnum=#{minStartRnum}," +
            " batchCount=#{batchCount},entityClass=#{entityClass},mapperClass=#{mapperClass},msg=#{msg}" +
            " WHERE id =#{id}")
    void update(RelationInfo relationInfo);

    @Delete("DELETE FROM relationInfo WHERE id =#{id}")
    void delete(@Param(value = "id") int id);

}
