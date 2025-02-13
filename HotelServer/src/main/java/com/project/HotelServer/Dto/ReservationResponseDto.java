package com.project.HotelServer.Dto;

import java.util.List;

public class ReservationResponseDto {
    private Integer totalPages;
    private Integer pageNumber;
    private List<ReservationDto> reservationDtoList;

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setReservationDtoList(List<ReservationDto> reservationDtoList) {
        this.reservationDtoList = reservationDtoList;
    }
}
