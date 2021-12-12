package com.shou.socialpracticemanager.po;

public class GroupParticipation {
    private int groupParticipationID;
    private int groupID;
    private int userID;

    public GroupParticipation() {
    }

    public GroupParticipation(int groupID, int userID) {
        this.groupID = groupID;
        this.userID = userID;
    }

    public GroupParticipation(int groupParticipationID, int groupID, int userID) {
        this.groupParticipationID = groupParticipationID;
        this.groupID = groupID;
        this.userID = userID;
    }

    public int getGroupParticipationID() {
        return groupParticipationID;
    }

    public void setGroupParticipationID(int groupParticipationID) {
        this.groupParticipationID = groupParticipationID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
