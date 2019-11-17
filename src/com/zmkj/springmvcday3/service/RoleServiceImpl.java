package com.zmkj.springmvcday3.service;

import java.sql.Connection;
import java.util.List;
import javax.annotation.Resource;
import com.zmkj.springmvcday3.dao.BaseDao;
import com.zmkj.springmvcday3.dao.RoleDao;
import com.zmkj.springmvcday3.entity.Role;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleDao roleDao;
	
	@Override
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Role> roleList = null;
		try {
			connection = BaseDao.getConnection();
			roleList = roleDao.getRoleList(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return roleList;
	}
	
}
