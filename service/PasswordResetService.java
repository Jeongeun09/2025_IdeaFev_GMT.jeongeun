package com.example.gmt_ideafev.service;

import com.example.gmt_ideafev.entity.User;
import com.example.gmt_ideafev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PasswordResetService {

   private static final Logger logger = LoggerFactory.getLogger(PasswordResetService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static class PasswordResetResponse {
        private final boolean success;
        private final String message;

        public PasswordResetResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

    }

    public PasswordResetResponse resetPasswordWithCode(String email, String findpassword, String newpassword) {
        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {
            return new PasswordResetResponse(false, "등록되지 않은 이메일입니다.");
        }

        if (!user.getFindpassword().equals(findpassword)) {
            return new PasswordResetResponse(false, "비밀번호 찾기 암호가 일치하지 않습니다.");
        }

        user.setPassword(passwordEncoder.encode(newpassword));
        userRepository.save(user);

        return new PasswordResetResponse(true, "비밀번호가 변경되었습니다.");
    }
}

