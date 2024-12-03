package com.sparta.msa_exam.auth.api;


import com.sparta.msa_exam.auth.api.request.SignInRequest;
import com.sparta.msa_exam.auth.api.request.SignUpRequest;
import com.sparta.msa_exam.auth.service.AuthService;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest request) {
        authService.signUp(request);
    }

    @PostMapping("/sign-in")
    public Map<String, String> signUp(@Valid @RequestBody SignInRequest request) {
        return Map.of("token", authService.signIn(request));
    }

}
