package com.neo.mapper.test1;

import com.neo.entity.po.CustomerInfo;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface CustomerInfoMapper {

	@Select("SELECT * FROM 客户信息")
	@Results({
			@Result(property = "userName",  column = "名字"),
			@Result(property = "weight", column = "体重"),
			@Result(property = "age", column = "年龄")
	})
	List<CustomerInfo> getAll();

	@Insert("INSERT INTO 客户信息(名字,体重,年龄) VALUES(#{userName}, #{weight}, #{age})")
	void insert(CustomerInfo customerInfo);


	@Delete("DELETE FROM 客户信息 WHERE 名字 =#{userName} and 体重=#{weight} and 年龄=#{age}")
	void delete(CustomerInfo customerInfo);

	@Insert("<script>"+
			"insert into 客户信息(名字, 体重, 年龄) "
			+ "values "
			+ "<foreach collection =\"customerInfoList\" item=\"customerInfo\" index= \"index\" separator =\",\"> "
			+ "(#{customerInfo.userName},#{customerInfo.weight},#{customerInfo.age}) "
			+ "</foreach > "
			+ "</script>")
	void insertBatch(@Param(value = "customerInfoList") List<CustomerInfo> customerInfoList);

}