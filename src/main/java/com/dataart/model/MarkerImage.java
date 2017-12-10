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

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "markerId", nullable = false)
    private Marker marker;


}
