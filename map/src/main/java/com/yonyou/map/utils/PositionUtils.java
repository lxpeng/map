package com.yonyou.map.utils;


import android.util.Log;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;

/**
 * Created by lxp on 2017/9/5.
 * 位置坐标转换
 */

public class PositionUtils {

    public static void z(CoordinateConverter.CoordType coordType, LatLng latLng) {
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        LatLng a = coordinateConverter.from(coordType).coord(latLng).convert();

        Log.e("定位", "zlat:" + a.latitude);
        Log.e("定位", "zlon:" + a.longitude);
    }
}
