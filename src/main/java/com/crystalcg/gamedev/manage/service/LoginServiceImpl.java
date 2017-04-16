package com.crystalcg.gamedev.manage.service;

import org.springframework.stereotype.Service;

import com.crystalcg.gamedev.model.User;
import com.crystalcg.gamedev.utils.AppException;
import com.crystalcg.gamedev.utils.PropertiesUtils;
import com.crystalcg.gamedev.utils.StringUtil;

@Service
public class LoginServiceImpl implements LoginService{
	
	public boolean login(User user) throws AppException {
		String userList = PropertiesUtils.getValue("user");
		String name;
		String pwd;
		for(String userStr : userList.split(",")){
			if(StringUtil.isEmpty(userStr)){
				continue;
			}
			name = userStr.split(":")[0].substring(1);
			pwd = userStr.split(":")[1].substring(0,userStr.split(":")[1].length()-1);
			if(name.equals(user.getName())){
				if(pwd.equals(user.getPassword())){
					return true;
				}else{
					throw new AppException("error");
				}
			}
		}
		return false;
	}

}
