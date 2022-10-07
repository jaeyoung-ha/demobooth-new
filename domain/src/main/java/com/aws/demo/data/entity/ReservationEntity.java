package com.aws.demo.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "reservation")
public class ReservationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String bookingId;

    @Column(nullable = false, length = 120)
    private String nickname;

    @Column(nullable = false, length = 120)
    private String departure;

    @Column(nullable = false, length = 120)
    private String arrival;

    @Column(nullable = false)
    private String departureDate;

    @Column(nullable = false)
    private String returnDate;

    @Column(nullable = false)
    private Integer passengerAdult;

    @Column(nullable = false)
    private Integer passengerChild;

    @Column(nullable = false, length = 120)
    private String flightDeparture;

    @Column(nullable = false, length = 120)
    private String flightArrival;

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

    @Column
    private String photoImg;
}
