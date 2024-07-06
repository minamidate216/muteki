package jp.co.gxp.controller;

import jp.co.gxp.controller.converter.ItemConverter;
import jp.co.gxp.controller.model.SkillsModel;
import jp.co.gxp.domain.SkillEntity;
import jp.co.gxp.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemApiController implements ItemApi{

    private final ItemService itemService;

    //skillリストを取得
    @Override
    public ResponseEntity<SkillsModel> itemSkillListGet() {
        List<SkillEntity> skills = itemService.fetchSkills();
        return  new ResponseEntity<>(ItemConverter.convert(skills), HttpStatus.OK);
    }
}
