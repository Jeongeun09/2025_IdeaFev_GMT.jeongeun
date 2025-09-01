package com.example.gmt_ideafev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "username", nullable = false)
        private String username;

        @Column(name = "findpassword", nullable = false)
        private String findpassword;

        @Column(name = "requester", nullable = false)
        private String requester;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("user"));
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() { return true; }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

