package com.example.gmt_ideafev.repository;

import com.example.gmt_ideafev.entity.Friend;
import com.example.gmt_ideafev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    boolean existsByRequesterAndAddresseeAndStatus(User requester, User addressee, Friend.Status status);

    Optional<Friend> findByRequesterAndAddressee(User requester, User addressee);
}
