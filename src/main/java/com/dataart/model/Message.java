package com.dataart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Роман on 22.11.2017.
 */
@Entity
@Table(name="messages")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="messageId")
    private Long id;

    @Column(name="isFromSender")
    private Boolean isFromSender;

    @Column(name="content")
    private String content;

    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name="isRead")
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "headerId", nullable = false)
    private Header header;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFromSender() {
        return isFromSender;
    }

    public void setFromSender(Boolean fromSender) {
        isFromSender = fromSender;
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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Message() {
    }

    public Message(Boolean isFromSender, String content, Date dateTime, Boolean isRead, Header header) {
        this.isFromSender = isFromSender;
        this.content = content;
        this.dateTime = dateTime;
        this.isRead = isRead;
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (isFromSender != null ? !isFromSender.equals(message.isFromSender) : message.isFromSender != null)
            return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (dateTime != null ? !dateTime.equals(message.dateTime) : message.dateTime != null) return false;
        if (isRead != null ? !isRead.equals(message.isRead) : message.isRead != null) return false;
        return header != null ? header.equals(message.header) : message.header == null;
    }

    @Override
    public int hashCode() {
        int result = isFromSender != null ? isFromSender.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (isRead != null ? isRead.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", isFromSender=" + isFromSender +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", isRead=" + isRead +
                ", header=" + header.getId() +
                '}';
    }
}
