package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.user.model.MUser;

/*mybatisでレポジトリを作成するためにmapper*/
@Mapper
public interface UserMapper {
	/*ユーザー登録*/
	public int insertOne(MUser user);
	}
