package com.aws.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "reservation")
public class ReservationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String bookingId;

    @Column(nullable = false, length = 120)
    private String departure;

    @Column(nullable = false)
    private String departureDate;

    @Column(nullable = false)
    private String returnDate;

    @Column(nullable = false)
    private Integer passengerAdult;

    @Column(nullable = false)
    private Integer passengerChild;

    @Column(nullable = false, length = 120)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String checkinDate;

    @Column(nullable = false)
    private String checkoutDate;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false)
    private Integer hotelAdult;

    @Column(nullable = false)
    private Integer hotelChild;

    @Column(nullable = false)
    private String phoneNum;

    @Column
    private String photoImg;
}
