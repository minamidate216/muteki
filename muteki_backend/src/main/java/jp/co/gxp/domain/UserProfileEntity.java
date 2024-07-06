package jp.co.gxp.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileEntity {
    private final Integer id;
    private final String name;
    private final String real_name;
    private final LocalDate birthday;
    private final Integer sex;
    private final String address;
    private final String address_detail;
    private final String tel;
    private final String user_email;
    private final String country;
    private final LocalDate zairyu_time;
    private final String japanese;
    private final Integer japanese_level;
    private final String work_status;
    private final String rireki_url;
}
