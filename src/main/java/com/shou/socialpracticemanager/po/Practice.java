package com.shou.socialpracticemanager.po;

import com.shou.socialpracticemanager.utils.DateTimeUtil;

public class Practice {
    private int practiceID;
    private String practiceName;
    private String startTime;
    private String endTime;
    private int state;

    public Practice() {
    }

    public Practice(String practiceName) {
        this.practiceID = 0;
        this.practiceName = practiceName;
        this.startTime = DateTimeUtil.getSystemTime();
        this.endTime = null;
        this.state = 0;
    }

    public Practice(int practiceID, String practiceName, String startTime, String endTime, int state) {
        this.practiceID = practiceID;
        this.practiceName = practiceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
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
}
