package com.yonyou.mymap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.yonyou.map.entity.LocationInfo;
import com.yonyou.map.location.Location;
import com.yonyou.map.location.LocationFactory;
import com.yonyou.map.location.LocationListener;
import com.yonyou.map.location.LocationType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLo();
    }

    private void getLo() {
        LocationFactory.createLocation(LocationType.BAIDU)
                .init(MainActivity.this)
                .setOnceLocation(true)
                .start(new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location, LocationInfo info) {
                        Log.e("定位_百度", "结果：" + info.getAddress());
                        Log.e("定位_百度", "结果：" + info.getLat());
                        Log.e("定位_百度", "结果：" + info.getLon());
                    }

                    @Override
                    public void onLocationErr(String errMsg) {
                        Log.e("定位_百度", "结果：" + errMsg);
                    }
                });

        LocationFactory.createLocation(LocationType.GAODE)
                .init(MainActivity.this)
                .setOnceLocation(true)
                .start(new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location, LocationInfo info) {
                        Log.e("定位_高德", "结果：" + info.getAddress());
                        Log.e("定位_高德", "结果：" + info.getLat());
                        Log.e("定位_高德", "结果：" + info.getLon());
                    }

                    @Override
                    public void onLocationErr(String errMsg) {
                        Log.e("定位_高德", "结果：" + errMsg);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
