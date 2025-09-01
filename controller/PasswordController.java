package com.example.gmt_ideafev.controller;

import com.example.gmt_ideafev.dto.ApiResponse;
import com.example.gmt_ideafev.dto.PasswordResetRequest;
import com.example.gmt_ideafev.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/password")
    public class PasswordController {


        private final PasswordResetService passwordResetService;

        public PasswordController(PasswordResetService passwordResetService) {
            this.passwordResetService = passwordResetService;
        }

        // 2. 4자리 암호가 맞으면 비밀번호 변경
        @PostMapping("/reset/pwd")
        public ResponseEntity<ApiResponse> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
            try {
                passwordResetService.resetPasswordWithCode(
                        passwordResetRequest.getUsername(),
                        passwordResetRequest.getFindpassword(),
                        passwordResetRequest.getNewpassword()
                );
                return ResponseEntity.ok(
                        new ApiResponse(true, 200, "PASSWORD_RESET_SUCCESS", "비밀번호가 변경되었습니다.")
                );
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(
                        new ApiResponse(false, 400, "INVALID_PASSWORD", "유효하지 않은 비밀번호입니다.")
                );
            } catch (Exception e) {
                return ResponseEntity.status(500).body(
                        new ApiResponse(false, 500, "SERVER_ERROR", "서버 오류 발생")
                );
            }

        }
    }


