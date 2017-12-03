package com.dataart.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Роман on 03.12.2017.
 */
public class MarkerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double lat;

    private Double lng;

    private Integer access;

    private String description;

    private String name;

    private Date dateTime;

    public MarkerDTO() {
    }

    public MarkerDTO(Double lat, Double lng, Integer access, String description, String name, Date dateTime) {
        this.lat = lat;
        this.lng = lng;
        this.access = access;
        this.description = description;
        this.name = name;
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

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        MarkerDTO markerDTO = (MarkerDTO) o;

        if (lat != null ? !lat.equals(markerDTO.lat) : markerDTO.lat != null) return false;
        if (lng != null ? !lng.equals(markerDTO.lng) : markerDTO.lng != null) return false;
        if (access != null ? !access.equals(markerDTO.access) : markerDTO.access != null) return false;
        if (description != null ? !description.equals(markerDTO.description) : markerDTO.description != null)
            return false;
        if (name != null ? !name.equals(markerDTO.name) : markerDTO.name != null) return false;
        return dateTime != null ? dateTime.equals(markerDTO.dateTime) : markerDTO.dateTime == null;
    }

    @Override
    public int hashCode() {
        int result = lat != null ? lat.hashCode() : 0;
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (access != null ? access.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MarkerDTO{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", access=" + access +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
