package com.project.HotelServer.Dto;

import java.util.List;

public class RoomResponseDto {
    private List<RoomDto> roomDtoList;
    private Integer totalpages;
    private Integer pageNumber;

    public List<RoomDto> getRoomDtoList() {
        return roomDtoList;
    }

    public Integer getTotalpages() {
        return totalpages;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setRoomDtoList(List<RoomDto> roomDtoList) {
        this.roomDtoList = roomDtoList;
    }

    public void setTotalpages(Integer totalpages) {
        this.totalpages = totalpages;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
