package jp.co.gxp.service;


import jp.co.gxp.domain.MyPageRepository;
import jp.co.gxp.domain.UserProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageRepository myPageRepository;

    public UserProfileEntity fetchUserProfileById(Integer loginUserId) {
        return myPageRepository.fetchUserProfileById(loginUserId);
    }

}
