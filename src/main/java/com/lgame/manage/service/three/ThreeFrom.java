package com.lgame.manage.service.three;

import com.lgame.manage.service.three.entity.ThreeFromData;
import com.lgame.manage.service.three.entity.ThreeFromUserData;
import com.module.FromType;

/**
 * Created by leroy:656515489@qq.com
 * 2017/6/20.
 */
public interface ThreeFrom {
    public FromType getKey();
    public ThreeFromData checkUser(String code);
    public boolean checkUser(String thirdKey,String ukey);
    public ThreeFromUserData getThreeFromUserData(String thirdKey, String ukey);
}
