package com.neo.mapper.test1;

import com.neo.entity.po.RelationInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by songcj on 2018/10/10.
 */
public interface RelationInfoMapper {

    @Select("SELECT * FROM relationInfo")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "type",  column = "type"),
            @Result(property = "entityClass", column = "entityClass"),
            @Result(property = "mapperClass", column = "mapperClass")
    })
    List<RelationInfo> getAll();
}
