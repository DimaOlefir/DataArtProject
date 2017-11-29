package com.dataart.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Роман on 24.11.2017.
 */
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idFromUser;

    private Long idToUser;

    private String content;
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dateTime;

    public MessageDTO() {
    }

    public MessageDTO(Long idFromUser, Long idToUser, String content, Date dateTime) {
        this.idFromUser = idFromUser;
        this.idToUser = idToUser;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Long getIdFromUser() {
        return idFromUser;
    }

    public void setIdFromUser(Long idFromUser) {
        this.idFromUser = idFromUser;
    }

    public Long getIdToUser() {
        return idToUser;
    }

    public void setIdToUser(Long idToUser) {
        this.idToUser = idToUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        MessageDTO that = (MessageDTO) o;

        if (idFromUser != null ? !idFromUser.equals(that.idFromUser) : that.idFromUser != null) return false;
        if (idToUser != null ? !idToUser.equals(that.idToUser) : that.idToUser != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return dateTime != null ? dateTime.equals(that.dateTime) : that.dateTime == null;
    }

    @Override
    public int hashCode() {
        int result = idFromUser != null ? idFromUser.hashCode() : 0;
        result = 31 * result + (idToUser != null ? idToUser.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "idFromUser=" + idFromUser +
                ", idToUser=" + idToUser +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
