package com.lgame.manage.service;
import org.springframework.stereotype.Repository;

import com.lgame.model.User;
import com.lgame.utils.AppException;
@Repository
public interface LoginService {
	/**
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean login(User user) throws AppException; 
}
