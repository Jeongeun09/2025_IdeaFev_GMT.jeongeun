package com.example.gmt_ideafev.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "server_members")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServerMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    private String role;
}
