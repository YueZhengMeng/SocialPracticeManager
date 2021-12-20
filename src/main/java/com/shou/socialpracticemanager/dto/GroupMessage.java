package com.shou.socialpracticemanager.dto;

import com.shou.socialpracticemanager.po.Group;

public class GroupMessage {
    private int groupID;
    private String groupName;
    private int practiceID;
    private int score;
    private int isInside;

    public GroupMessage() {
    }

    public GroupMessage(int groupID, String groupName, int practiceID, int score, int isInside) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.practiceID = practiceID;
        this.score = score;
        this.isInside = isInside;
    }

    public GroupMessage(Group group, int isInside) {
        this.groupID = group.getGroupID();
        this.groupName = group.getGroupName();
        this.practiceID = group.getPracticeID();
        this.score = group.getScore();
        this.isInside = isInside;
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

    public int getPracticeID() {
        return practiceID;
    }

    public void setPracticeID(int practiceID) {
        this.practiceID = practiceID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIsInside() {
        return isInside;
    }

    public void setIsInside(int isInside) {
        this.isInside = isInside;
    }
}
