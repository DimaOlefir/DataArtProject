package com.dataart.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Роман on 24.11.2017.
 */
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long ifFromUser;

    private Long ifToUser;

    private String content;

    private Date dateTime;

    public MessageDTO() {
    }

    public MessageDTO(Long ifFromUser, Long ifToUser, String content, Date dateTime) {
        this.ifFromUser = ifFromUser;
        this.ifToUser = ifToUser;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Long getIfFromUser() {
        return ifFromUser;
    }

    public void setIfFromUser(Long ifFromUser) {
        this.ifFromUser = ifFromUser;
    }

    public Long getIfToUser() {
        return ifToUser;
    }

    public void setIfToUser(Long ifToUser) {
        this.ifToUser = ifToUser;
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

        if (ifFromUser != null ? !ifFromUser.equals(that.ifFromUser) : that.ifFromUser != null) return false;
        if (ifToUser != null ? !ifToUser.equals(that.ifToUser) : that.ifToUser != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return dateTime != null ? dateTime.equals(that.dateTime) : that.dateTime == null;
    }

    @Override
    public int hashCode() {
        int result = ifFromUser != null ? ifFromUser.hashCode() : 0;
        result = 31 * result + (ifToUser != null ? ifToUser.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "ifFromUser=" + ifFromUser +
                ", ifToUser=" + ifToUser +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
