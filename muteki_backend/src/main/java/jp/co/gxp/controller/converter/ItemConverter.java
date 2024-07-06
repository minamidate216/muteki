package jp.co.gxp.controller.converter;

import jp.co.gxp.controller.model.SkillModel;
import jp.co.gxp.controller.model.SkillsModel;
import jp.co.gxp.domain.SkillEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ItemConverter {
    //JobのListをJobsModelに変わる
    public static SkillsModel convert(List<SkillEntity> skills) {
        return new SkillsModel(
                skills.stream().map(e -> new SkillModel(
                        e.getSkillId(),
                        e.getSkillName())
                ).collect(Collectors.toList())
        );
    }
}
