package jp.co.gxp.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> fetchById(Integer userId);

    Optional<UserEntity> fetchByEmail(String email);

    void save(String email, String encodedPassword);


}