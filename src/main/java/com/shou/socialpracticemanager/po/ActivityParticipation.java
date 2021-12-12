package com.shou.socialpracticemanager.po;

public class ActivityParticipation {
    private int activityParticipationID;
    private int activityID;
    private int userID;
    private String finishTime;
    private int state;

    public ActivityParticipation() {
    }

    public ActivityParticipation(int activityParticipationID, int activityID, int userID, String finishTime, int state) {
        this.activityParticipationID = activityParticipationID;
        this.activityID = activityID;
        this.userID = userID;
        this.finishTime = finishTime;
        this.state = state;
    }

    public ActivityParticipation(int activityID, int userID) {
        this.activityParticipationID = 0;
        this.activityID = activityID;
        this.userID = userID;
        this.finishTime = null;
        this.state = 0;
    }

    public int getActivityParticipationID() {
        return activityParticipationID;
    }

    public void setActivityParticipationID(int activityParticipationID) {
        this.activityParticipationID = activityParticipationID;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
