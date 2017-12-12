package com.dataart.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Роман on 10.12.2017.
 */
@Entity
@Table(name="markerImages")
public class MarkerImage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="imageId")
    private Long id;

    @Column(name = "s3_key")
    private String key;

    /*@Column(name = "url")
    private String url;*/

    @Column(name="isExist")
    private Boolean isExist;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "markerId", nullable = false)
    private Marker marker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getExist() {
        return isExist;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public MarkerImage() {
    }

    public MarkerImage(String key, Boolean isExist, Marker marker) {
        this.key = key;
        this.isExist = isExist;
        this.marker = marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkerImage that = (MarkerImage) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (isExist != null ? !isExist.equals(that.isExist) : that.isExist != null) return false;
        return marker != null ? marker.equals(that.marker) : that.marker == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (isExist != null ? isExist.hashCode() : 0);
        result = 31 * result + (marker != null ? marker.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MarkerImage{" +
                "key='" + key + '\'' +
                ", isExist=" + isExist +
                ", marker=" + marker +
                '}';
    }
}
