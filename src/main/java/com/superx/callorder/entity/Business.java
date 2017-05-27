package com.superx.callorder.entity;

import java.util.Date;

public class Business {
    private Integer id;

    private String name;

    private String status;

    private String type;

    private String pauseReason;

    private String content;

    private Integer maxNum;

    private Date amStartTime;

    private Date amEndTime;

    private Date pmStartTime;

    private Date pmEndTime;
    
    private String amStart;

    private String amEnd;

    private String pmStart;

    private String pmEnd;

    private Integer operatorId;

    private String operatorName;

    private Date operatorTime;

    private Integer creatorId;

    private String creatorName;

    private Date creatorTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPauseReason() {
        return pauseReason;
    }

    public void setPauseReason(String pauseReason) {
        this.pauseReason = pauseReason == null ? null : pauseReason.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Date getAmStartTime() {
        return amStartTime;
    }

    public void setAmStartTime(Date amStartTime) {
        this.amStartTime = amStartTime;
    }

    public Date getAmEndTime() {
        return amEndTime;
    }

    public void setAmEndTime(Date amEndTime) {
        this.amEndTime = amEndTime;
    }

    public Date getPmStartTime() {
        return pmStartTime;
    }

    public void setPmStartTime(Date pmStartTime) {
        this.pmStartTime = pmStartTime;
    }

    public Date getPmEndTime() {
        return pmEndTime;
    }

    public void setPmEndTime(Date pmEndTime) {
        this.pmEndTime = pmEndTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getCreatorTime() {
        return creatorTime;
    }

    public void setCreatorTime(Date creatorTime) {
        this.creatorTime = creatorTime;
    }

	public String getAmStart() {
		return amStart;
	}

	public void setAmStart(String amStart) {
		this.amStart = amStart;
	}

	public String getAmEnd() {
		return amEnd;
	}

	public void setAmEnd(String amEnd) {
		this.amEnd = amEnd;
	}

	public String getPmStart() {
		return pmStart;
	}

	public void setPmStart(String pmStart) {
		this.pmStart = pmStart;
	}

	public String getPmEnd() {
		return pmEnd;
	}

	public void setPmEnd(String pmEnd) {
		this.pmEnd = pmEnd;
	}
}