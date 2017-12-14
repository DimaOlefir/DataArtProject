package com.dataart.entity;

import com.dataart.entity.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Роман on 22.11.2017.
 */
@Entity
@Table(name="headers")
public class Header implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="headerId")
    private Long id;

    @Column(name="subject")
    private String subject;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dateTime;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "header")
    private List<Message> messages;

    //in user won't be opportunity to send message more than one user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fromId", nullable = false)
    private User userFromMsg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toId", nullable = false)
    private User userToMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User getUserFromMsg() {
        return userFromMsg;
    }

    public void setUserFromMsg(User userFromMsg) {
        this.userFromMsg = userFromMsg;
    }

    public User getUserToMsg() {
        return userToMsg;
    }

    public void setUserToMsg(User userToMsg) {
        this.userToMsg = userToMsg;
    }

    public Header() {
    }

    public Header(String subject, Status status, Date dateTime, User userFromMsg, User userToMsg) {
        this.subject = subject;
        this.status = status;
        this.dateTime = dateTime;
        //this.messages = messages;
        this.userFromMsg = userFromMsg;
        this.userToMsg = userToMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Header header = (Header) o;

        if (subject != null ? !subject.equals(header.subject) : header.subject != null) return false;
        if (status != header.status) return false;
        if (dateTime != null ? !dateTime.equals(header.dateTime) : header.dateTime != null) return false;
        if (messages != null ? !messages.equals(header.messages) : header.messages != null) return false;
        if (userFromMsg != null ? !userFromMsg.equals(header.userFromMsg) : header.userFromMsg != null) return false;
        return userToMsg != null ? userToMsg.equals(header.userToMsg) : header.userToMsg == null;
    }

    @Override
    public int hashCode() {
        int result = subject != null ? subject.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        result = 31 * result + (userFromMsg != null ? userFromMsg.hashCode() : 0);
        result = 31 * result + (userToMsg != null ? userToMsg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Header{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", status=" + status +
                ", dateTime=" + dateTime +
                ", messages=" + messages +
                ", userFromMsg=" + userFromMsg.getLogin() +
                ", userToMsg=" + userToMsg.getLogin() +
                '}';
    }
}
