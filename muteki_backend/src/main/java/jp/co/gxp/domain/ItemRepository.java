package jp.co.gxp.domain;

import java.util.List;

public interface ItemRepository {
    List<SkillEntity> fetchSkills();
}
