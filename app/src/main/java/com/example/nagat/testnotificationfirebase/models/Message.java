package com.example.nagat.testnotificationfirebase.models;

import java.util.Date;

/**
 * Created by ducng on 03/13/2018.
 */

public class Message {
    private String senderID;
    private String content;
    private long timestamp;

    public Message() {
    }

    public Message(String senderID, String content) {
        this.senderID = senderID;
        this.content = content;
        this.timestamp = new Date().getTime();
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
