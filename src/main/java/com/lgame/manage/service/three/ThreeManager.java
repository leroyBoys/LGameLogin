package com.lgame.manage.service.three;

import com.lgame.manage.service.three.entity.ThreeFromData;
import com.lgame.manage.service.three.entity.ThreeFromUserData;
import com.module.FromType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leroy:656515489@qq.com
 * 2017/6/20.
 */
public class ThreeManager{
    private static ThreeManager ourInstance = new ThreeManager();

    public static ThreeManager getInstance() {
        return ourInstance;
    }

    private static final Map<FromType,ThreeFrom> threeMap = new HashMap<>();
    private ThreeManager() {

        ThreeFrom weiXinFrom = new WeiXinFrom();
        threeMap.put(weiXinFrom.getKey(),weiXinFrom);

        SelfXinFrom selfXinFrom = new SelfXinFrom();
        threeMap.put(weiXinFrom.getKey(),selfXinFrom);
    }

    public ThreeFromData checkUser(FromType fromType, String code) {
        ThreeFrom threeFrom = threeMap.get(fromType);
        if(threeFrom == null){
            return null;
        }
        return threeFrom.checkUser(code);
    }

    /**
     * 检测是否是合法的key，ukey
     * @param fromType
     * @param thirdKey
     * @param ukey
     * @return
     */
    public boolean checkUser(FromType fromType,String thirdKey, String ukey) {
        ThreeFrom threeFrom = threeMap.get(fromType);
        if(threeFrom == null){
            return false;
        }
        return threeFrom.checkUser(thirdKey,ukey);
    }

    public ThreeFromUserData getThreeFromUserData(FromType fromType, String thirdKey, String ukey) {
        ThreeFrom threeFrom = threeMap.get(fromType);
        if(threeFrom == null){
            return null;
        }
        return threeFrom.getThreeFromUserData(thirdKey,ukey);
    }
}
