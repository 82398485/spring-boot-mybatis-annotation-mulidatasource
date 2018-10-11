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
            @Result(property = "filePattern",  column = "filePattern"),
            @Result(property = "sheetIndex",  column = "sheetIndex"),
            @Result(property = "minStartRnum",  column = "minStartRnum"),
            @Result(property = "batchCount",  column = "batchCount"),
            @Result(property = "entityClass", column = "entityClass"),
            @Result(property = "mapperClass", column = "mapperClass"),
            @Result(property = "msg", column = "msg")
    })
    List<RelationInfo> getAll();
}
