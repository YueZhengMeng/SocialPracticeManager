package com.shou.socialpracticemanager.dto;

import com.shou.socialpracticemanager.po.ActivityParticipation;
import com.shou.socialpracticemanager.po.Group;

public class FinishStateMessage {
    private int activityID;
    private int groupID;
    private String groupName;
    private String finishTime;
    private int state;

    public FinishStateMessage() {
    }

    public FinishStateMessage(int activityID, int groupID, String groupName, String finishTime, int state) {
        this.activityID = activityID;
        this.groupID = groupID;
        this.groupName = groupName;
        this.finishTime = finishTime;
        this.state = state;
    }

    public FinishStateMessage(Group group, ActivityParticipation activityParticipation) {
        this.activityID = activityParticipation.getActivityID();
        this.groupID = activityParticipation.getGroupID();
        this.groupName = getGroupName();
        this.finishTime = activityParticipation.getFinishTime();
        this.state = activityParticipation.getState();
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
