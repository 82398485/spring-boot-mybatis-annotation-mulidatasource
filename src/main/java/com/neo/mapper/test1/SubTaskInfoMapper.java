package com.neo.mapper.test1;

import com.neo.entity.po.CustomerInfo;
import com.neo.entity.po.SubTaskInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by songcj on 2018/10/10.
 */
public interface SubTaskInfoMapper  {

    @Select("SELECT * FROM subTaskInfo")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "filePath", column = "filePath"),
            @Result(property = "sheetIndex", column = "sheetIndex"),
            @Result(property = "startRnum", column = "startRnum"),
            @Result(property = "endRnum", column = "endRnum"),
            @Result(property = "finished", column = "finished"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
            @Result(property = "errorInfo", column = "errorInfo")
    })
    List<SubTaskInfo> getAll();

    @Insert("INSERT INTO subTaskInfo(filePath, sheetIndex, startRnum, endRnum, finished, startDate, endDate, errorInfo) " +
            "VALUES(#{filePath}, #{sheetIndex}, #{startRnum},#{endRnum}, #{finished}, #{startDate},#{endDate}, #{errorInfo})")
    void insert(SubTaskInfo subTaskInfo);

    @Insert("<script>"+
            "insert into subTaskInfo(filePath, sheetIndex, startRnum, endRnum, finished, startDate, endDate, errorInfo) "
            + "values "
            + "<foreach collection =\"subTaskInfoList\" item=\"subTaskInfo\" index= \"index\" separator =\",\"> "
            + "(#{subTaskInfo.filePath}, #{subTaskInfo.sheetIndex}, #{subTaskInfo.startRnum},#{subTaskInfo.endRnum}, #{subTaskInfo.finished}, #{subTaskInfo.startDate},#{subTaskInfo.endDate}, #{subTaskInfo.errorInfo}) "
            + "</foreach > "
            + "</script>")
    void insertBatch(@Param(value = "subTaskInfoList") List<SubTaskInfo> subTaskInfoList);

    @Delete("DELETE FROM subTaskInfo WHERE id =#{id}")
    void deleteById(@Param(value = "id") int id);

    @Update("UPDATE subTaskInfo SET finished=#{finished}, startDate=now(), endDate=now() WHERE id =#{id}")
    void updateStatus(@Param(value = "id") int id,@Param(value = "finished") int finished);

    @Update("UPDATE subTaskInfo SET finished=#{finished}, endDate=now() WHERE id =#{id}")
    void updateStatusAndEndDate(@Param(value = "id") int id,@Param(value = "finished") int finished);

}
