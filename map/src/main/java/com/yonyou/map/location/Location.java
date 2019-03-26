package com.yonyou.map.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by lxp on 2017/9/5.
 * 定位
 */

public abstract class Location<T extends Location.Builder> {
    public T mBuilder;

    public Location(T builder) {
        mBuilder = builder;
        builder.setLocation(this);
    }

    /**
     * 开始定位
     *
     * @param locationListener 监听
     */
    public abstract void start(LocationListener locationListener);

    /**
     * 停止定位
     */
    public abstract void stop();

    /**
     * 销毁
     */
    public abstract void destroy();

    public static abstract class Builder {

        public Activity mContext;

        public LocationListener mLocationListener;//监听

        public Location mLocation;

        /**
         * @param is 是否只定位一次
         * @return 本身
         */
        public abstract Builder setOnceLocation(boolean is);

        /**
         * 设置多久定位一次 毫秒
         *
         * @param time 时间
         * @return 本身
         */
        public abstract Builder setInterval(long time);

        /**
         * @return 构建
         */
        public abstract Location build();

        /**
         * 初始化
         *
         * @param mContext 上下文
         * @return 本身
         */
        public abstract Builder init(@NonNull Activity mContext);

        /**
         * 开始定位
         * @param locationListener 定位监听
         * @return 结果
         */
        public abstract Location start(LocationListener locationListener);


        /**
         * @param mLocationListener 设置定位监听
         */
        public void setLocationListener(LocationListener mLocationListener) {
            this.mLocationListener = mLocationListener;
        }

        /**
         * @param mLocation 定位类
         */
        public void setLocation(Location mLocation) {
            this.mLocation = mLocation;
        }
    }
}
