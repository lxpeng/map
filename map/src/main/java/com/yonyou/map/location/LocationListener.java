package com.yonyou.map.location;

import com.yonyou.map.entity.LocationInfo;

/**
 * Created by lxp on 2017/9/5.
 * 定位监听
 */

public abstract class LocationListener {
    private Location mLocation;

    /**
     * 设置监听
     *
     * @param mLocation 定位类
     */
    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    /**
     * 监听
     *
     * @param location 定位类
     * @param info     定位数据
     */
    public abstract void onLocationChanged(Location location, LocationInfo info);

    /**
     * @param errMsg 错误信息
     */
    public abstract void onLocationErr(String errMsg);
}
