package jp.co.gxp.infrastructure.mapper;

import jp.co.gxp.domain.UserProfileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyPageMapper {

    UserProfileEntity fetchUserProfileById(@Param("userId") Integer loginUserId);
}
