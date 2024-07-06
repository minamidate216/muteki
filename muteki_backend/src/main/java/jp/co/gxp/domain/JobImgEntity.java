package jp.co.gxp.domain;

import lombok.Data;

@Data
public class JobImgEntity {
    private final Integer imgId;
    private final String imgUrl;
    private final Integer jobDetailId;
}
