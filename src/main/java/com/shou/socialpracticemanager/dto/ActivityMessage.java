package com.shou.socialpracticemanager.dto;

import com.shou.socialpracticemanager.po.Activity;

public class ActivityMessage {
    private int activityID;
    private String activityName;
    private int practiceID;
    private String startTime;
    private String endTime;
    private int state;
    private int isInside;

    public ActivityMessage() {
    }

    public ActivityMessage(int activityID, String activityName, int practiceID, String startTime, String endTime, int state, int isInside) {
        this.activityID = activityID;
        this.activityName = activityName;
        this.practiceID = practiceID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.isInside = isInside;
    }

    public ActivityMessage(Activity activity, int isInside) {
        this.activityID = activity.getActivityID();
        this.activityName = activity.getActivityName();
        this.practiceID = activity.getPracticeID();
        this.startTime = activity.getStartTime();
        this.endTime = activity.getEndTime();
        this.state = activity.getState();
        this.isInside = isInside;
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

    public int getIsInside() {
        return isInside;
    }

    public void setIsInside(int isInside) {
        this.isInside = isInside;
    }
}
