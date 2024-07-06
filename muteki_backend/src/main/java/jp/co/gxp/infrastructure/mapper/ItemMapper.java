package jp.co.gxp.infrastructure.mapper;

import jp.co.gxp.domain.SkillEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<SkillEntity> fetchSkills();
}
