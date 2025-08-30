package com.example.gmt_ideafev.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servers")
@Getter @Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String inviteCode;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
