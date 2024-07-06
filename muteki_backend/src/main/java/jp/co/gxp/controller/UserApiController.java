package jp.co.gxp.controller;

import jp.co.gxp.controller.model.UserModel;
import jp.co.gxp.controller.model.UserRequest;
import jp.co.gxp.domain.user.UserEntity;
import jp.co.gxp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/api")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserApiController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserModel> usersMeGet() {

        var userEntityOptional = AuthorizationUserAccessor.getUserEntity();
        var responseModel = new UserModel(userEntityOptional.isPresent())
                .userEmail(userEntityOptional.map(UserEntity::getUserEmail).orElse(null));


        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> usersSignUpPost(UserRequest userRequest) {
        Optional<UserEntity> users = userService.fetchByEmail(userRequest.getEmail());
        if (users.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userService.save(userRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

