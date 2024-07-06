package jp.co.gxp.domain.user;

import lombok.Data;

@Data
public class UserEntity {
    private final Integer userId;
    private final String userName;
    private final String userEmail;
    private final String hashedPassword;

    public UserEntity(Integer userId, String username, String userEmail, String encodedPassword) {
        this.userId = userId;
        this.userName = username;
        this.userEmail = userEmail;
        this.hashedPassword = encodedPassword;
    }
}
