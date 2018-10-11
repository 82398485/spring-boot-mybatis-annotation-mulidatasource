package com.neo.mapper.test1;

import com.neo.entity.po.RelationInfo;
import com.neo.entity.po.SourceInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by songcj on 2018/10/11.
 */
public interface SourceInfoMapper {

    @Select("SELECT * FROM sourceInfo")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "path",  column = "path"),
            @Result(property = "filePattern",  column = "filePattern"),
            @Result(property = "createDate", column = "createDate"),
            @Result(property = "lastDate", column = "lastDate")
    })
    List<SourceInfo> getAll();


}
