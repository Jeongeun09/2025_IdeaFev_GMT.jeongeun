package com.example.gmt_ideafev.service;

import com.example.gmt_ideafev.dto.FriendResponse;
import com.example.gmt_ideafev.entity.Friend;
import com.example.gmt_ideafev.entity.User;
import com.example.gmt_ideafev.repository.FriendRepository;
import com.example.gmt_ideafev.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.gmt_ideafev.entity.Friend.Status.ACCEPTED;

@Service
@RequiredArgsConstructor
@Getter
public class FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;


    // 친구 요청 보내기
    // 자기 자신한테는 요청 못 보냄
    public void sendFriendRequest(User requesterId, User addresseeId) {
        if (requesterId.equals(addresseeId)) {
            throw new IllegalArgumentException("자신에게 요청할 수 없습니다.");
        }

        // 찾을 수 없는 상황
        User requester = userRepository.findById(requesterId.getId())
                .orElseThrow(() -> new RuntimeException("요청자를 찾을 수 없습니다."));
        User addressee = userRepository.findById(addresseeId.getId())
                .orElseThrow(() -> new RuntimeException("대상자를 찾을 수 없습니다."));

        // 친구인 지 확인
        if (friendRepository.existsByRequesterAndAddresseeAndStatus(requester, addressee, ACCEPTED)) {
            throw new IllegalStateException("이미 친구입니다.");
        }

        // 요청이 왔었는 지 확인하기
        if (friendRepository.existsByRequesterAndAddresseeAndStatus(requester, addressee, Friend.Status.PENDING)) {
            throw new IllegalStateException("이미 요청이 존재합니다.");
        }

        Friend friend = new Friend(requester, addressee, Friend.Status.PENDING);
        Friend saved = friendRepository.save(friend);

        FriendResponse.builder()
                .requesterId(saved.getId())
                .requestId(saved.getRequester().getId())
                .requesterName(saved.getRequester().getUsername())
                .addresseeId(saved.getAddressee().getId())
                .addresseeName(saved.getAddressee().getUsername())
                .status(saved.getStatus().name())
                .message("친구 요청을 보냈습니다.")
                .success("SUCCESS")
                .build();
    }

    // 친구 요청 수락
    public FriendResponse acceptFriendRequest(Long requesterId, Long addresseeId) {
        User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("요청자를 찾을 수 없습니다."));
        User addressee = userRepository.findById(addresseeId)
                .orElseThrow(() -> new RuntimeException("대상자를 찾을 수 없습니다."));
        Friend request = friendRepository.findByRequesterAndAddressee(requester, addressee)
                .orElseThrow(() -> new RuntimeException("요청이 존재하지 않습니다."));

        // 받은 사람만 수락
        request.setStatus();
        Friend saved = friendRepository.save(request);

        return FriendResponse.builder()
                .requestId(saved.getId())
                .requesterId(saved.getRequester().getId())
                .requesterName(saved.getRequester().getUsername())
                .addresseeId(saved.getAddressee().getId())
                .addresseeName(saved.getAddressee().getUsername())
                .status(saved.getStatus().name())
                .build();

    }

}
