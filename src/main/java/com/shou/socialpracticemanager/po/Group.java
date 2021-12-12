package com.shou.socialpracticemanager.po;

public class Group {
    private int groupID;
    private String groupName;
    private int practiceID;

    public Group() {
    }

    public Group(int groupID, String groupName, int practiceID) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.practiceID = practiceID;
    }

    public Group(String groupName, int practiceID) {
        this.groupName = groupName;
        this.practiceID = practiceID;
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
}
