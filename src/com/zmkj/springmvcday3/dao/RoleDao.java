package com.zmkj.springmvcday3.dao;

import com.zmkj.springmvcday3.entity.Role;

import java.sql.Connection;
import java.util.List;


public interface RoleDao {
	
	public List<Role> getRoleList(Connection connection)throws Exception;

}
