package com.project.HotelServer.Dto;

import com.project.HotelServer.Enums.ReservationStatus;

import java.time.LocalDate;

public class ReservationDto {
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus reservationStatus;

    private Long roomId;

    private String roomType;

    private String roomName;

    private Long userId;

    private String userName;

    public Long getId() {
        return id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public ReservationDto() {
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public Long getPrice() {
        return price;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
