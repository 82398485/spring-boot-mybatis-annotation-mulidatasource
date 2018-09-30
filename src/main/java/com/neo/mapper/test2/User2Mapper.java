package com.neo.mapper.test2;

import java.util.List;

import com.neo.entity.UserEntity;
import com.neo.enums.UserSexEnum;
import org.apache.ibatis.annotations.*;

public interface User2Mapper {

	@Select("SELECT * FROM users")
	@Results({
			@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
			@Result(property = "nickName", column = "nick_name")
	})
	List<UserEntity> getAll();

	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
			@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
			@Result(property = "nickName", column = "nick_name")
	})
	UserEntity getOne(Long id);

	@Insert("INSERT INTO users(user_name,pass_word,user_sex,nick_name) VALUES(#{userName}, #{passWord}, #{userSex}, #{nickName})")
	void insert(UserEntity user);

	@Update("UPDATE users SET user_name=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	void update(UserEntity user);

	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Long id);

}