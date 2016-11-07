package com.nova.app.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nova.app.sys.dao.SysDao;

@Service
public class SysServiceImpl implements SysService{

	@Autowired
	private SysDao sysDao;
	
	public String getPermission(String urlPath) {
		return sysDao.getPermission(urlPath);
	}
	
}
