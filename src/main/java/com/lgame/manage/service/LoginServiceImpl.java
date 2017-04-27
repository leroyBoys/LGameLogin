package com.lgame.manage.service;

import com.lgame.model.User;
import com.lgame.utils.AppException;
import org.springframework.stereotype.Service;

import com.lgame.utils.PropertiesUtils;
import com.lgame.utils.StringUtil;

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
