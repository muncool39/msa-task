package com.sparta.msa_exam.auth.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id
    private String userId;
    private String password;

    @Builder(access = AccessLevel.PRIVATE)
    private User(String password, String userId) {
        this.password = password;
        this.userId = userId;
    }

    public static User create(String id, String password) {
        return User.builder()
                .userId(id)
                .password(password)
                .build();
    }

}
