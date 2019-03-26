package com.yonyou.map.location.baidu;

import android.Manifest;
import android.app.Activity;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yonyou.map.entity.LocationInfo;
import com.yonyou.map.location.Location;
import com.yonyou.map.location.LocationListener;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by lxp on 2017/9/5.
 */

public class BaiduLocation extends Location<BaiduLocation.Builder> {

    public BaiduLocation(BaiduLocation.Builder builder) {
        super(builder);
    }

    @Override
    public void start(LocationListener locationListener) {
        if (!mBuilder.mLocationClient.isStarted()) {
            mBuilder.setLocationListener(locationListener);
            new RxPermissions(mBuilder.mContext)
                    .requestEach(ACCESS_FINE_LOCATION)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                            if (permission.granted) {
                                mBuilder.mLocationClient.start();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                Log.e("定位", "未获取权限");
                                if (null != mBuilder.mLocationListener) {
                                    mBuilder.mLocationListener.onLocationErr("无定位权限");
                                }
                            } else {
                                Log.e("定位", "定位已被权限，去设置页面设置");
                                if (null != mBuilder.mLocationListener) {
                                    mBuilder.mLocationListener.onLocationErr("无定位权限");
                                }
                            }
                        }
                    });
        }
    }

    @Override
    public void stop() {
        if (mBuilder.mLocationClient.isStarted()) {
            mBuilder.mLocationClient.stop();
        }
    }

    @Override
    public void destroy() {
        mBuilder.mLocationClient.unRegisterLocationListener(mBuilder);
    }

    public static class Builder extends Location.Builder implements BDLocationListener {

        public LocationClient mLocationClient = null;
        public LocationClientOption mLocationClientOption = null;
        private boolean OnceLocation = false;//是否只定位一次

        @Override
        public Location.Builder init(Activity mContext) {
            this.mContext = mContext;
            mLocationClientOption = new LocationClientOption();
            mLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

            mLocationClientOption.setCoorType("bd09ll");
            //可选，默认gcj02，设置返回的定位结果坐标系


            mLocationClientOption.setIsNeedAddress(true);
            //可选，设置是否需要地址信息，默认不需要

            mLocationClientOption.setOpenGps(true);
            //可选，默认false,设置是否使用gps

            mLocationClientOption.setIsNeedLocationDescribe(true);
            //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”


            mLocationClient = new LocationClient(mContext);
            //声明LocationClient类
            mLocationClient.registerLocationListener(this);
            //注册监听函数
            mLocationClient.setLocOption(mLocationClientOption);
            return this;
        }

        @Override
        public BaiduLocation start(LocationListener locationListener) {
            BaiduLocation m = new BaiduLocation(this);
            m.start(locationListener);
            return m;
        }

        @Override
        public Location.Builder setOnceLocation(boolean is) {
            OnceLocation = is;
            if (is) {
                mLocationClientOption.setScanSpan(0);
            } else {
                mLocationClientOption.setScanSpan(2000);
            }
            mLocationClient.setLocOption(mLocationClientOption);
            return this;
        }

        @Override
        public Location.Builder setInterval(long time) {
            mLocationClientOption.setScanSpan((int) time);
            return this;
        }

        @Override
        public Location build() {
            return new BaiduLocation(this);
        }


        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (null != mLocationListener && null != mLocation && null != bdLocation) {
                mLocationListener.onLocationChanged(mLocation, new LocationInfo(bdLocation));
                if (OnceLocation) {
                    mLocationClient.stop();
                }
            } else {
                mLocationListener.onLocationErr("未知");
            }
        }

    }
}
