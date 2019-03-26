package com.yonyou.map.location;

import com.yonyou.map.location.baidu.BaiduLocation;
import com.yonyou.map.location.gaode.GaoDeLocation;

/**
 * Created by lxp on 2017/9/5.
 * 定位工厂
 */

public class LocationFactory {

    public static Location.Builder createLocation(LocationType type) {
        if (type == LocationType.BAIDU) {
            return new BaiduLocation.Builder();
        } else if (type == LocationType.GAODE) {
            return new GaoDeLocation.Builder();
        } else {

        }
        return null;
    }
}
