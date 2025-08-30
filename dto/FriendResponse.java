package com.example.gmt_ideafev.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendResponse {
    public String success;
    private String message;
    private Long requestId;
    private Long requesterId;
    private String requesterName;
    private Long addresseeId;
    private String addresseeName;
    private String status;

}
