package com.example.gmt_ideafev.dto;

import com.example.gmt_ideafev.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequest {
    public static Object Status;
    private Long requesterId;
    private Long addresseeId;


}
