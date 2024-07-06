package jp.co.gxp.infrastructure.repository;

import jp.co.gxp.domain.MyPageRepository;
import jp.co.gxp.domain.UserProfileEntity;
import jp.co.gxp.infrastructure.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class MyPageRepositoryDb implements MyPageRepository {

    private final MyPageMapper myPageMapper;

    @Override
    public UserProfileEntity fetchUserProfileById(Integer loginUserId) {
        return myPageMapper.fetchUserProfileById(loginUserId);
    }
}
