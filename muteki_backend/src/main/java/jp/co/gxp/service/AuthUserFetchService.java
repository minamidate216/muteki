package jp.co.gxp.service;

import jp.co.gxp.domain.user.AuthenticatedUserEntity;
import jp.co.gxp.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 認証用のユーザ取得サービス
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserFetchService implements UserDetailsService {
    // Bean(=Component)として登録しておけば自動的にこのUserDetailsServiceが使われる

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("load user: {}", email);
        return userRepository.fetchByEmail(email)
                .map(AuthenticatedUserEntity::new)
                .orElseThrow(() -> new UsernameNotFoundException("user is not found"));
    }
}
