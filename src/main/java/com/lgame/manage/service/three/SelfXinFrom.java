package com.lgame.manage.service.three;

import com.lgame.manage.service.three.entity.ThreeFromData;
import com.lgame.manage.service.three.entity.ThreeFromUserData;
import com.module.FromType;

/**
 * Created by leroy:656515489@qq.com
 * 2017/6/20.
 */
public class SelfXinFrom implements ThreeFrom {
    @Override
    public FromType getKey() {
        return FromType.wx;
    }

    @Override
    public ThreeFromData checkUser(String code) {
        return null;
    }

    @Override
    public boolean checkUser(String thirdKey, String ukey) {
        return false;
    }

    @Override
    public ThreeFromUserData getThreeFromUserData(String thirdKey, String ukey) {
        return null;
    }

}
