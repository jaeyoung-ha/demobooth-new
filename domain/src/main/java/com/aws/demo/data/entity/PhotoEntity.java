package com.aws.demo.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor
@Table(name = "photo")
public class PhotoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String bookingId;

    @Column(nullable = false)
    private String photoImg;

    @Column(nullable = false)
    private Integer checkIn;
}