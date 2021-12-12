package com.shou.socialpracticemanager.po;

public class ActivityParticipation {
    private int activityParticipationID;
    private int activityID;
    private int groupID;
    private String finishTime;
    private int state;

    public ActivityParticipation() {
    }

    public ActivityParticipation(int activityParticipationID, int activityID, int groupID, String finishTime, int state) {
        this.activityParticipationID = activityParticipationID;
        this.activityID = activityID;
        this.groupID = groupID;
        this.finishTime = finishTime;
        this.state = state;
    }

    public ActivityParticipation(int activityID, int groupID) {
        this.activityParticipationID = 0;
        this.activityID = activityID;
        this.groupID = groupID;
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

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
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
