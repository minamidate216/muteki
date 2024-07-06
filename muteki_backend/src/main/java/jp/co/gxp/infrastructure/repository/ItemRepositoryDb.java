package jp.co.gxp.infrastructure.repository;

import jp.co.gxp.domain.ItemRepository;
import jp.co.gxp.domain.SkillEntity;
import jp.co.gxp.infrastructure.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryDb implements ItemRepository {

    private final ItemMapper itemMapper;

    @Override
    public List<SkillEntity> fetchSkills() {
        return itemMapper.fetchSkills();
    }
}
