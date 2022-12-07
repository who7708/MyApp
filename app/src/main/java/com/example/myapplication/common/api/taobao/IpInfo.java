package com.example.myapplication.common.api.taobao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IpInfo {

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("isp_id")
    @Expose
    private String ispId;

    @SerializedName("queryIp")
    @Expose
    private String queryIp;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("ip")
    @Expose
    private String ip;

    @SerializedName("isp")
    @Expose
    private String isp;

    @SerializedName("county")
    @Expose
    private String county;

    @SerializedName("region_id")
    @Expose
    private String regionId;

    @SerializedName("area_id")
    @Expose
    private String areaId;

    @SerializedName("county_id")
    @Expose
    private Object countyId;

    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("country_id")
    @Expose
    private String countryId;

    @SerializedName("city_id")
    @Expose
    private String cityId;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIspId() {
        return ispId;
    }

    public void setIspId(String ispId) {
        this.ispId = ispId;
    }

    public String getQueryIp() {
        return queryIp;
    }

    public void setQueryIp(String queryIp) {
        this.queryIp = queryIp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Object getCountyId() {
        return countyId;
    }

    public void setCountyId(Object countyId) {
        this.countyId = countyId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "IpInfo{" +
                "area='" + area + '\'' +
                ", country='" + country + '\'' +
                ", ispId='" + ispId + '\'' +
                ", queryIp='" + queryIp + '\'' +
                ", city='" + city + '\'' +
                ", ip='" + ip + '\'' +
                ", isp='" + isp + '\'' +
                ", county='" + county + '\'' +
                ", regionId='" + regionId + '\'' +
                ", areaId='" + areaId + '\'' +
                ", countyId=" + countyId +
                ", region='" + region + '\'' +
                ", countryId='" + countryId + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }
}