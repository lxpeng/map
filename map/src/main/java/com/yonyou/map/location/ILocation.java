package com.yonyou.map.location;

import android.content.Context;
import android.support.annotation.NonNull;

import com.amap.api.location.AMapLocationListener;

/**
 * Created by lxp on 2017/9/5.
 * 定位相关接口
 */

public interface ILocation {
    /**
     * @param isOne 是否定位一次
     * @return 对象
     */
    Location.Builder setOnceLocation(boolean isOne);

    /**
     * 自定义连续定位
     *
     * @param time 多久定位一次
     * @return 对象
     */
    Location.Builder setInterval(long time);

    /**
     * @return 构建
     */
    Location build();

    /**
     * 初始化
     *
     * @param type 选择定位服务定位
     * @return 对象
     */
    Location.Builder init(@NonNull LocationType type);

}