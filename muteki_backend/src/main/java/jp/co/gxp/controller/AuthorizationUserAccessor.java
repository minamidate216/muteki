package jp.co.gxp.controller;

import jp.co.gxp.domain.user.AuthenticatedUserEntity;
import jp.co.gxp.domain.user.UserEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthorizationUserAccessor {

    public static Optional<UserEntity> getUserEntity() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.of((AuthenticatedUserEntity) authentication.getPrincipal()).map(AuthenticatedUserEntity::getUserEntity);
    }

}
