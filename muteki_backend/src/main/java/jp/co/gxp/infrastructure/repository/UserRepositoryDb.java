package jp.co.gxp.infrastructure.repository;

import jp.co.gxp.domain.user.UserEntity;
import jp.co.gxp.domain.user.UserRepository;
import jp.co.gxp.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryDb implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public Optional<UserEntity> fetchById(Integer userId) {
        return userMapper.fetchById(userId);
    }

    @Override
    public Optional<UserEntity> fetchByEmail(String email) {
        return userMapper.fetchByEmail(email);
    }

    @Override
    public void save(String email, String password) {
        userMapper.insertUser(email, password);
    }

}
