package com.yonyou.map.entity;

import com.amap.api.location.AMapLocation;
import com.baidu.location.BDLocation;

/**
 * Created by lxp on 2017/9/5.
 * 定位信息是体力
 */

public class LocationInfo {
    private double lat;
    private double lon;
    private String province;//省
    private String city;//城市
    private String district;//区
    private String street;//街道
    private String address;//详细地址
    private float radius;//定位精度
    private String errMsg;//错误信息
    private String cityId;//城市ID


    public LocationInfo(String errMsg) {
        this.errMsg = errMsg;
    }

    public LocationInfo(AMapLocation aMapLocation) {
        this.lat = aMapLocation.getLatitude();
        this.lon = aMapLocation.getLongitude();
        this.city = aMapLocation.getCity();
        this.address = aMapLocation.getAddress();
        this.district = aMapLocation.getDistrict();
        this.radius = aMapLocation.getAccuracy();
        this.street = aMapLocation.getStreet();
        this.province = aMapLocation.getProvince();

    }

    public LocationInfo(BDLocation bDLocation) {
        this.lat = bDLocation.getLatitude();
        this.lon = bDLocation.getLongitude();
        this.city = bDLocation.getCity();
        this.address = bDLocation.getAddrStr();
        this.district = bDLocation.getDistrict();
        this.radius = bDLocation.getRadius();
        this.street = bDLocation.getStreet();
        this.province = bDLocation.getProvince();
    }


    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
