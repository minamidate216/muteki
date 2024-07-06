package jp.co.gxp.controller.converter;

import jp.co.gxp.controller.model.UserProfileModel;
import jp.co.gxp.domain.UserProfileEntity;

public class ProfileConverter {
    public static UserProfileModel convert(UserProfileEntity userProfile) {
        return new UserProfileModel(
                userProfile.getId(),
                userProfile.getName(),
                userProfile.getReal_name(),
                userProfile.getBirthday(),
                userProfile.getSex(),
                userProfile.getAddress(),
                userProfile.getAddress_detail(),
                userProfile.getTel(),
                userProfile.getUser_email(),
                userProfile.getCountry(),
                userProfile.getZairyu_time(),
                userProfile.getJapanese(),
                userProfile.getJapanese_level(),
                userProfile.getWork_status(),
                userProfile.getRireki_url()
        );
    }
}