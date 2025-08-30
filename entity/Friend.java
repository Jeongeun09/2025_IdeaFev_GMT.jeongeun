package com.example.gmt_ideafev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "friends")
public class Friend {

    public Friend() {}

    public void setStatus() {
        this.status = status;
    }

    public enum Status {PENDING, ACCEPTED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 한 사람이 요청을 여러 개 보낼 수 있음
    @JoinColumn(name = "requester_id")
    private User requester; // 요청 보낸 사람

    @ManyToOne // 한 사람이 요청을 여러 개 받을 수 있음
    @JoinColumn(name = "addressee_id")
    private User addressee; // 요청 받고 승인 또는 삭제하는 사람

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING; // 기본 상태는 대기 상태

    private LocalDateTime createdAt = LocalDateTime.now();

    public Friend(User requester, User addressee, Status status) {
        this.requester = requester;
        this.addressee = addressee;
        this.status = status;
    }

    public Friend(User requester, User addressee) {
        this.requester = requester;
        this.addressee = addressee;
    }

    public User getAddressee() {
        return addressee;
    }

    public User getRequester() {
        return requester;
    }

}
