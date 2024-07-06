package jp.co.gxp.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AuthenticatedUserEntity implements UserDetails {

    @Getter
    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE"));
    }

    @Override
    public String getPassword() {
        return userEntity.getHashedPassword();
    }

    @Override
    public String getUsername() {
        // SpringSecurityでのuserNameは
        return userEntity.getUserEmail();
    }

    public Integer getUserId() {
        return userEntity.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
