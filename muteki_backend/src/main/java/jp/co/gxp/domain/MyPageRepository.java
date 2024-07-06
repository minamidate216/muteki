package jp.co.gxp.domain;

public interface MyPageRepository {
    UserProfileEntity fetchUserProfileById(Integer loginUserId);
}
