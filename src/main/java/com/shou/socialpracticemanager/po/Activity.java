package com.shou.socialpracticemanager.po;

import com.shou.socialpracticemanager.utils.DateTimeUtil;

public class Activity {
    private int activityID;
    private String activityName;
    private int practiceID;
    private String startTime;
    private String endTime;
    private int state;

    public Activity() {
    }

    public Activity(int activityID, String activityName, int practiceID, String startTime, String endTime, int state) {
        this.activityID = activityID;
        this.activityName = activityName;
        this.practiceID = practiceID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }

    public Activity(String activityName, int practiceID) {
        this.activityID = 0;
        this.activityName = activityName;
        this.practiceID = practiceID;
        this.startTime = DateTimeUtil.getSystemTime();
        this.endTime = null;
        this.state = 0;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getPracticeID() {
        return practiceID;
    }

    public void setPracticeID(int practiceID) {
        this.practiceID = practiceID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
