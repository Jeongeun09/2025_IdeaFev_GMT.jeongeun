package com.example.gmt_ideafev.controller;

import com.example.gmt_ideafev.dto.FriendRequest;
import com.example.gmt_ideafev.dto.FriendResponse;
import com.example.gmt_ideafev.entity.User;
import com.example.gmt_ideafev.repository.UserRepository;
import com.example.gmt_ideafev.service.FriendService;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
@Builder
public class FriendController {

    private final FriendService friendService;
    private final UserRepository userRepository;


    // 친구 요청 보내기
    @PostMapping("/request")
    public ResponseEntity<FriendResponse> friendRequest(@RequestBody FriendRequest friendRequest) {
        // Long -> User 조회
        User requester = userRepository.findById(friendRequest.getRequesterId())
                .orElseThrow(() -> new RuntimeException("요청자를 찾을 수 없습니다."));
        User addresse = userRepository.findById(friendRequest.getAddresseeId())
                .orElseThrow(() -> new RuntimeException("대상자를 찾을 수 없습니다."));


        // 서비스 호출
        friendService.sendFriendRequest(requester, addresse);
        // 응답 생성
        FriendResponse response= FriendResponse.builder()
                .message("친구 요청을 보냈습니다.")
                .success("SUCCESS")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // 친구 요청 받기
    @PostMapping("/accept")
    public ResponseEntity<String> friendAccept(@RequestBody FriendRequest friendRequest) {
        User requester = userRepository.findById(friendRequest.getAddresseeId())
                .orElseThrow(() -> new RuntimeException("요청자를 찾을 수 없습니다."));
        User addressee = userRepository.findById(friendRequest.getAddresseeId())
                .orElseThrow(() -> new RuntimeException("대상자를 찾을 수 없습니다."));

        friendService.acceptFriendRequest(requester.getId(), addressee.getId());

        return ResponseEntity.ok("친구 요청을 수락했습니다.");
    }
}
