package com.dataart.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Роман on 05.12.2017.
 */
public class CommentMarkerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long markerId;

    private Long userId;

    private String text;

    private Date dateTime;

    public CommentMarkerDTO() {
    }

    public CommentMarkerDTO(Long markerId, Long userId, String text, Date dateTime) {
        this.markerId = markerId;
        this.userId = userId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getMarkerId() {
        return markerId;
    }

    public void setMarkerId(Long markerId) {
        this.markerId = markerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentMarkerDTO that = (CommentMarkerDTO) o;

        if (markerId != null ? !markerId.equals(that.markerId) : that.markerId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return dateTime != null ? dateTime.equals(that.dateTime) : that.dateTime == null;
    }

    @Override
    public int hashCode() {
        int result = markerId != null ? markerId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentMarkerDTO{" +
                "markerId=" + markerId +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
