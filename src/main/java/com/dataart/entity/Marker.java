package com.dataart.entity;

import com.dataart.entity.enums.AccessMarker;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Роман on 02.12.2017.
 */
@Entity
@Table(name="markers")
public class Marker implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="markerId")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name="address")
    private String address;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name = "lat", nullable = false)
    private Double lat;

    @Column(name = "lng", nullable = false)
    private Double lng;

    @Column(name = "access", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessMarker accessMarker;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "marker")
    private List<CommentMarker> commentsMarker;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "marker")
    private List<MarkerImage> markerImages;

    public Marker() {
    }

    public Marker(User user, String address, Date dateTime, Double lat, Double lng, AccessMarker accessMarker) {
        this.user = user;
        this.address = address;
        this.dateTime = dateTime;
        this.lat = lat;
        this.lng = lng;
        this.accessMarker = accessMarker;
    }

    public Marker(User user, String address, String name, String description, Date dateTime, Double lat, Double lng, AccessMarker accessMarker) {
        this.user = user;
        this.address = address;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.lat = lat;
        this.lng = lng;
        this.accessMarker = accessMarker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public AccessMarker getAccessMarker() {
        return accessMarker;
    }

    public void setAccessMarker(AccessMarker accessMarker) {
        this.accessMarker = accessMarker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommentMarker> getCommentsMarker() {
        return commentsMarker;
    }

    public void setCommentsMarker(List<CommentMarker> commentsMarker) {
        this.commentsMarker = commentsMarker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marker marker = (Marker) o;

        if (user != null ? !user.equals(marker.user) : marker.user != null) return false;
        if (address != null ? !address.equals(marker.address) : marker.address != null) return false;
        if (name != null ? !name.equals(marker.name) : marker.name != null) return false;
        if (description != null ? !description.equals(marker.description) : marker.description != null) return false;
        if (dateTime != null ? !dateTime.equals(marker.dateTime) : marker.dateTime != null) return false;
        if (lat != null ? !lat.equals(marker.lat) : marker.lat != null) return false;
        if (lng != null ? !lng.equals(marker.lng) : marker.lng != null) return false;
        return accessMarker == marker.accessMarker;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (accessMarker != null ? accessMarker.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "user=" + user +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", lat=" + lat +
                ", lng=" + lng +
                ", accessMarker=" + accessMarker +
                '}';
    }
}
