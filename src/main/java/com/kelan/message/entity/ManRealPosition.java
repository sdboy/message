package com.kelan.message.entity;

import java.util.Date;

public class ManRealPosition {
    private Long sid;

    private String gpsNo;

    private Date createTime;

    private String deviceType;

    private String dirction;

    private Double latitude;

    private Double longitude;

    private String onlineStatus;

    private Date reportTime;

    private Double speed;

    private String uuid;

    public ManRealPosition(Long sid, String gpsNo, Date createTime, String deviceType, String dirction, Double latitude, Double longitude, String onlineStatus, Date reportTime, Double speed, String uuid) {
        this.sid = sid;
        this.gpsNo = gpsNo;
        this.createTime = createTime;
        this.deviceType = deviceType;
        this.dirction = dirction;
        this.latitude = latitude;
        this.longitude = longitude;
        this.onlineStatus = onlineStatus;
        this.reportTime = reportTime;
        this.speed = speed;
        this.uuid = uuid;
    }

    public ManRealPosition() {
        super();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getGpsNo() {
        return gpsNo;
    }

    public void setGpsNo(String gpsNo) {
        this.gpsNo = gpsNo == null ? null : gpsNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getDirction() {
        return dirction;
    }

    public void setDirction(String dirction) {
        this.dirction = dirction == null ? null : dirction.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus == null ? null : onlineStatus.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }
}