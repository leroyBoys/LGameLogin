package com.lgame.manage.service.impl;

import com.lgame.manage.cache.ServerConnection;
import com.lgame.manage.cache.ServerManager;
import com.lgame.manage.service.LoginService;
import com.lgame.manage.service.ServerService;
import com.lgame.manage.service.UserService;
import com.lgame.model.*;
import com.lgame.util.comm.StringTool;
import com.lgame.util.comm.Tools;
import com.lgame.util.time.DateTimeTool;
import com.lgame.util.unique.ShareCodeUtil;
import com.lgame.utils.AppException;
import com.lgame.utils.PropertiesUtils;
import com.lgame.utils.StringUtil;
import com.module.FromType;
import com.module.Status;
import com.module.Type;
import com.module.db.*;
import com.module.net.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private ServerService serverService;
	@Autowired
	private UserService userService;

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

	@Override
	public SEVersionCheck check(REVersionCheck vc) {
		SEVersionCheck rp = new SEVersionCheck();
		Version v = serverService.getVersion(vc.getSrcId(), vc.getGameId());
		if (v == null) {
			return null;
		}
		if (!StringUtil.isEmpty(v.getVersion_test())) {
			//    String test_v = StringUtil.subPreString(v.getVersion_test(), ".", 2) + ".";
			// if (vc.getVersion().startsWith(test_v)) {//是审核版本
			if (vc.getVersion().equals(v.getVersion_test())) {//是审核版本
				rp.setGoUrl(v.getTest_ip());
				return rp;
			}
		}
		if (!StringUtil.isEmpty(v.getF_ids())) {
			List<GameNotice> gameNotice = null;
			if (v.getSever_status() == Status.ServerStatus.stoping.getVal()) {
				gameNotice = serverService.getGameStopingNotice(v);
			} else {
				gameNotice = serverService.getGameRuningNotice(v);
			}
			if (!gameNotice.isEmpty()) {
				rp.setNoticeVersions(getNotices(gameNotice));
			}
		}
		rp.setQuzoneStatus(v.getSever_status());
		if (v.getSever_status() == Status.ServerStatus.stoping.getVal()) {
			return rp;
		}
		rp.setVersion(v.getVersion());
		if (!vc.getVersion().equals(v.getVersion())) {
			String[] array = v.getVersion().split("\\.");
			//   String main_t = StringUtil.subPreString(v.getVersion_test(), ".", 2) + ".";
			String main_t = StringTool.subPreString(v.getVersion(), ".", 2) + ".";
			if (vc.getVersion().startsWith(main_t)) {//主版一致
				if (!vc.getVersion().endsWith("." + array[array.length - 1])) {
					rp.setDownr(v.getRes_url());
				}
			} else {
				rp.setDownv(v.getDown_url());
			}
		}

		for (ServerConnection gz : ServerManager.getIntance().getServerPool().values()) {
			if(v.getId() != gz.getG_v_id()){
				continue;
			}

			rp.addZone(new NetZone(gz.getIp(), gz.getPort(), gz.getUdpPort(), gz.getRunStatus().ordinal(),
					gz.getId(), gz.getZoneName()));

		}
		return rp;
	}

	private List<NetNotice> getNotices(List<GameNotice> gameNotice) {
		List<NetNotice> list = new ArrayList<>();
		for (GameNotice no : gameNotice) {
			if (!StringUtil.isEmpty(no.getEndTime())) {
				if (DateTimeTool.getDateTime(no.getEndTime()).getTime() - System.currentTimeMillis() <= 0) {
					continue;
				}
			}
			if (!StringUtil.isEmpty(no.getStartTime())) {
				if (DateTimeTool.getDateTime(no.getEndTime()).getTime() - System.currentTimeMillis() > 0) {
					continue;
				}
			}
			list.add(new NetNotice(no.getType(), no.getTitleName(), no.getTitleDesc(), no.getStartTime(), no.getEndTime()));
		}
		return list;
	}

	@Override
	public Object login(RELogin vcd) {
		if (vcd.getDev() == null) {
			System.out.println("登陆设备信息不能为空！");
			return "登陆设备信息不能为空！";
		}

		UserInfo info = userService.getUserInfo(vcd.getUserName(),vcd.getPwd());
		if (info == null) {
			System.out.println(vcd.getUserName() + " 的玩家不存在！");
			return vcd.getUserName() + " 的玩家不存在！";
		}
		UserDev dev = userService.insertDev(vcd.getDev().getInfo(), vcd.getDev().getPlat(), vcd.getDev().getMac(), vcd.getDev().getUdid(), 0);
		SELoginThird l = login(info, dev);
		return new SELogin(l.getUid(), l.getKey(),l.getIpPort());
	}

	private SELoginThird login(UserInfo info, UserDev dev) {
		info.setDeviceId(dev.getId());

		userService.updateUserInfoLastDev(info.getId(), dev.getId());
		SELoginThird login = new SELoginThird();

		DB.UK uk = userService.getUk(info.getId());
		String ip = "";
		if(uk != null && uk.getIpPort() != null && !uk.getIpPort().trim().isEmpty()){
			ip = uk.getIpPort();
			ServerConnection serverConnection = ServerManager.getIntance().getServerConnection(ip);
			if(serverConnection == null || serverConnection.getRunStatus() == ServerConnection.ServerStatus.closed){
				ip="";
			}
		}

		String key = Tools.getCharacterAndNumber(6);
		userService.updateUserToken(info.getId(), key, ip);
		login.setKey(key);
		login.setIpPort(ip);
		login.setUid(info.getId());
		return login;
	}


	@Override
	public SELoginThird login_three(RELoginThird vcd) {
		if (vcd.getDev() == null) {
			System.out.println( "登陆设备信息不能为空！");
			return null;
		}
		UserFrom uf = checkThree(vcd.getThirdKey(), vcd.getUkey());
		if (uf == null) {
			return null;
		}
		UserInfo info = userService.getUserInfoByUserFormId(uf.getId());
		UserDev dev = userService.insertDev(vcd.getDev().getInfo(), vcd.getDev().getPlat(), vcd.getDev().getMac(), vcd.getDev().getUdid(), 0);
		if (info == null) {
			//第三方注册
			info = new UserInfo();
			info.setCreateDate(new Date());
			info.setDeviceId(dev.getId());
			info.setInviteCode("");
			info.setOnLineType(UserInfo.OnLineType.waitLogin);
			info.setRole((byte) Type.RoleType.nomal.getValue());
			info.setUserFromId(uf.getId());
			info.setUserFromType((byte) FromType.valueOf(uf.getUserSrc()).val());

			info.setUserStatus((byte) Status.UserStatus.normal.getValue());

			String userName = uf.getUserSrc() + info.getId();
			//String pwd = GameConst.Config.pwd;
			info.setUserName(userName);
			info.setUserPwd("dsf3*2s");
			info.setInviteCode(ShareCodeUtil.toSerialCode(info.getId()));
			info = userService.insertUserInfo(info);

		}
		return login(info, dev);
	}

	private UserFrom checkThree(String thirdKey, String ukey) {
		UserFrom uf = null;
		String tag = thirdKey.substring(thirdKey.lastIndexOf("_"));
		String oldKey = thirdKey.substring(0, thirdKey.lastIndexOf("_"));
		FromType t = FromType.valueOf(tag);
		boolean isRight = false;
		String info = null;

		if (t == FromType.tx) {

		} else if (t == FromType.safe) {

		} else {

		}
		if (!isRight) {
			return null;
		}
		uf = new UserFrom(0, ukey, info, info, new Date());

		return uf;
	}

	@Override
	public Object regedister(REregister re) {
		UserInfo info = userService.getUserInfo(re.getName());
		if (info != null) {
			return "账号"+re.getName()+"已经存在";
		}
		UserDev dev = userService.insertDev(re.getDev().getInfo(), re.getDev().getPlat(), re.getDev().getMac(), re.getDev().getUdid(), 0);
		//注册
		info = new UserInfo();
		info.setCreateDate(new Date());
		info.setDeviceId(dev.getId());
		info.setOnLineType(UserInfo.OnLineType.waitLogin);
		info.setRole((byte) Type.RoleType.nomal.getValue());
		info.setUserFromId(0);
		info.setUserFromType((byte) FromType.self.val());
		info.setUserName(re.getName());
		info.setUserPwd(re.getPwd());

		info.setUserStatus((byte) Status.UserStatus.normal.getValue());
		info.setInviteCode(ShareCodeUtil.toSerialCode(info.getId()));
		info = userService.insertUserInfo(info);


		return info;
	}

	@Override
	public Object changePwd(HttpSession session, REChangePwd vcd) {
		String code = vcd.getCode();
		UserInfo info = userService.getUserInfo(vcd.getName(),vcd.getOldPwd());
		if (info == null) {
			return "用户不存在或者密码错误";
		}

		info.setUserPwd(vcd.getNewPwd());
		boolean suc = userService.updatepwd(info.getId(), vcd.getNewPwd());
		if (suc) {
			System.out.println("===================" + suc);
			return true;
		}

		return false;
	}
}
