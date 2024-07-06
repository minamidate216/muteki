package jp.co.gxp.controller;

import jp.co.gxp.controller.converter.ProfileConverter;
import jp.co.gxp.controller.model.UserProfileModel;
import jp.co.gxp.domain.UserProfileEntity;
import jp.co.gxp.domain.user.UserEntity;
import jp.co.gxp.exception.UnauthorizedException;
import jp.co.gxp.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MyPageApiController implements MypageApi{
    private final MyPageService myPageService;

    @Override
    public ResponseEntity<UserProfileModel> mypageProfileGet() {
        var loginUserId = AuthorizationUserAccessor.getUserEntity().map(UserEntity::getUserId)
                .orElseThrow(() -> new UnauthorizedException("unauthorized"));
        UserProfileEntity profile = myPageService.fetchUserProfileById(loginUserId);
        return new ResponseEntity<>(ProfileConverter.convert(profile), HttpStatus.OK);
    }
}
