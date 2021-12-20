package com.shou.socialpracticemanager.dto;

import com.shou.socialpracticemanager.po.Practice;

public class PracticeMessage {
    private int practiceID;
    private String practiceName;
    private String startTime;
    private String endTime;
    private int state;
    private int isInside;

    public PracticeMessage() {
    }

    public PracticeMessage(int practiceID, String practiceName, String startTime, String endTime, int state, int isInside) {
        this.practiceID = practiceID;
        this.practiceName = practiceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.isInside = isInside;
    }

    public PracticeMessage(Practice practice,int isInside) {
        this.practiceID = practice.getPracticeID();
        this.practiceName = practice.getPracticeName();
        this.startTime = practice.getStartTime();
        this.endTime = practice.getEndTime();
        this.state = practice.getState();
        this.isInside = isInside;
    }

    public int getPracticeID() {
        return practiceID;
    }

    public void setPracticeID(int practiceID) {
        this.practiceID = practiceID;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
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
