package jp.co.gxp.service;

import jp.co.gxp.domain.ItemRepository;
import jp.co.gxp.domain.SkillEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<SkillEntity> fetchSkills() {
        return itemRepository.fetchSkills();
    }
}
