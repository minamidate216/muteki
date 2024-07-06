package jp.co.gxp.infrastructure.mapper;

import jp.co.gxp.domain.user.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface UserMapper {

    Optional<UserEntity> fetchById(@Param("userId") Integer userId);

    Optional<UserEntity> fetchByEmail(@Param("email") String email);

    void insertUser(@Param("userEmail") String userEmail,
                    @Param("hashedPassword") String hashedPassword);
}
