package com.yonyou.map.location.gaode;

import android.Manifest;
import android.app.Activity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yonyou.map.entity.LocationInfo;
import com.yonyou.map.location.Location;
import com.yonyou.map.location.LocationListener;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by lxp on 2017/9/5.
 * 高德定位
 */
public class GaoDeLocation extends Location<GaoDeLocation.Builder> {

    public GaoDeLocation(Builder builder) {
        super(builder);
    }

    @Override
    public void start(LocationListener locationListener) {
        mBuilder.setLocationListener(locationListener);
        new RxPermissions(mBuilder.mContext)
                .requestEach(Manifest.permission.ACCESS_COARSE_LOCATION
                        , ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            mBuilder.mlocationClient.startLocation();
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

    @Override
    public void stop() {
        mBuilder.mlocationClient.stopLocation();
    }

    @Override
    public void destroy() {
        mBuilder.mlocationClient.onDestroy();
    }

    public static class Builder extends Location.Builder implements AMapLocationListener {
        //声明mLocationOption对象
        public AMapLocationClientOption mLocationOption = null;

        private AMapLocationClient mlocationClient;


        @Override
        public Location.Builder setOnceLocation(boolean is) {
            mLocationOption.setOnceLocation(is);
            mLocationOption.setOnceLocationLatest(is);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.setLocationListener(this);
            return this;
        }

        @Override
        public Location.Builder setInterval(long time) {
            mLocationOption.setInterval(time);
            return this;
        }

        @Override
        public Location build() {
            return new GaoDeLocation(this);
        }

        @Override
        public Location.Builder init(Activity mContext) {
            this.mContext = mContext;
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            mlocationClient = new AMapLocationClient(mContext);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            return this;
        }

        @Override
        public GaoDeLocation start(LocationListener locationListener) {
            GaoDeLocation g = new GaoDeLocation(this);
            g.start(locationListener);
            return g;
        }

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (null != aMapLocation) {
                if (0 == aMapLocation.getErrorCode()) {
                    if (null != mLocationListener && null != mLocation) {
                        mLocationListener.onLocationChanged(mLocation, new LocationInfo(aMapLocation));
                    }
                } else {
                    Log.e("定位", "定位失败：" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                    if (null != mLocationListener) {
                        mLocationListener.onLocationErr(aMapLocation.getErrorInfo());
                    }
                }
            } else {
                Log.e("定位", "定位失败");
                mLocationListener.onLocationErr("未知");
            }
        }
    }

}
