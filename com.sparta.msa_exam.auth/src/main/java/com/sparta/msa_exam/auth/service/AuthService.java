package com.sparta.msa_exam.auth.service;


import com.sparta.msa_exam.auth.api.request.SignInRequest;
import com.sparta.msa_exam.auth.api.request.SignUpRequest;
import com.sparta.msa_exam.auth.config.TokenProvider;
import com.sparta.msa_exam.auth.domain.User;
import com.sparta.msa_exam.auth.exception.AuthCustomException;
import com.sparta.msa_exam.auth.exception.AuthErrorCode;
import com.sparta.msa_exam.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public void signUp(SignUpRequest request) {
        if(isIdDuplicated(request.user_id())) {
            throw new AuthCustomException(AuthErrorCode.ID_ALREADY_EXIST);
        }
        userRepository.save(User.create(
                request.user_id(),
                passwordEncoder.encode(request.password())
        ));
    }

    public String signIn(SignInRequest request) {
        User user = userRepository.findById(request.user_id()).orElseThrow(() ->
                new AuthCustomException(AuthErrorCode.USER_NOT_FOUND)
        );
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AuthCustomException(AuthErrorCode.INVALID_PASSWORD);
        }
        return tokenProvider.generateToken(user.getUserId());
    }

    private boolean isIdDuplicated(String userId){
        return userRepository.existsByUserId(userId);
    }

}
