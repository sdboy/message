package com.kelan.message.entity;

import java.util.Date;

public class CarFlowRate {
    private Long sid;

    private Date cnDate;

    private String regionName;

    private Integer hisFlow;

    private Integer realFlow;

    private Double changeRate;

    public CarFlowRate(Long sid, Date cnDate, String regionName, Integer hisFlow, Integer realFlow, Double changeRate) {
        this.sid = sid;
        this.cnDate = cnDate;
        this.regionName = regionName;
        this.hisFlow = hisFlow;
        this.realFlow = realFlow;
        this.changeRate = changeRate;
    }

    public CarFlowRate() {
        super();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Date getCnDate() {
        return cnDate;
    }

    public void setCnDate(Date cnDate) {
        this.cnDate = cnDate;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getHisFlow() {
        return hisFlow;
    }

    public void setHisFlow(Integer hisFlow) {
        this.hisFlow = hisFlow;
    }

    public Integer getRealFlow() {
        return realFlow;
    }

    public void setRealFlow(Integer realFlow) {
        this.realFlow = realFlow;
    }

    public Double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }
}