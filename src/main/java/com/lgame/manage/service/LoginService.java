package com.lgame.manage.service;
import com.lgame.model.*;
import com.module.db.UserInfo;
import org.springframework.stereotype.Repository;

import com.lgame.utils.AppException;

import javax.servlet.http.HttpSession;

@Repository
public interface LoginService {
	/**
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean login(User user) throws AppException;

	public SEVersionCheck check(REVersionCheck vcd);

	public Object login(RELogin vcd);

	public SELoginThird login_three(RELoginThird vcd);

	public Object regedister(REregister re);

	public Object changePwd(HttpSession session, REChangePwd vcd);
}
