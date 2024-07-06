package jp.co.gxp.domain;

import lombok.Data;

import java.util.List;

@Data
public class JobDetailAllEntity {
    private final JobDetailEntity jobDetail;
    private final String industry;
    private final List<ForeignRatioEntity> foreignRatios;
    private final List<SkillEntity> skills;
    private final List<JobImgEntity> jobImgs;
    private final CompanyJobEntity jobInCard;
}
