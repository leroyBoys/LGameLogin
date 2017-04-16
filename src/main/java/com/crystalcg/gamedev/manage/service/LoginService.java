package com.crystalcg.gamedev.manage.service;
import org.springframework.stereotype.Repository;

import com.crystalcg.gamedev.model.User;
import com.crystalcg.gamedev.utils.AppException;
@Repository
public interface LoginService {
	/**
	 * µÇÂ½
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean login(User user) throws AppException; 
}
