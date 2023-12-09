package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.porder;

@Mapper//產生 bean
public interface porderMapper {
	//Create
	@Insert("insert into porder(name,a,b,c) "
			+ "values(#{name},#{a},#{b},#{c})")
	void add(porder p);
	
	//Read
	@Select("select * from porder")
	List<porder> selectAll();
	
	
	//Update
	
	
	//Delete

}
