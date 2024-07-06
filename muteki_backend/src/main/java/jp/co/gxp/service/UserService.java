package jp.co.gxp.service;

import jp.co.gxp.controller.model.UserRequest;
import jp.co.gxp.domain.user.UserEntity;
import jp.co.gxp.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Emailでユーザーを取得するメソッド
    public Optional<UserEntity> fetchByEmail(String email) {
        return userRepository.fetchByEmail(email);
    }

    // ユーザーを保存するメソッド

    public void save(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRepository.save(userRequest.getEmail(), encodedPassword);
    }
}

