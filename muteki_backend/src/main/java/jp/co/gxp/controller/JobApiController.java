package jp.co.gxp.controller;
import jp.co.gxp.controller.converter.JobDetailModelConverter;
import jp.co.gxp.controller.converter.JobsModelConverter;
import jp.co.gxp.controller.model.JobDetailModel;
import jp.co.gxp.controller.model.JobIsLoginLikeJobListGet200Response;
import jp.co.gxp.controller.model.JobsModel;
import jp.co.gxp.controller.model.LikeRequest;
import jp.co.gxp.domain.ForeignRatioEntity;
import jp.co.gxp.domain.JobDetailAllEntity;
import jp.co.gxp.domain.JobEntity;
import jp.co.gxp.domain.user.UserEntity;
import jp.co.gxp.exception.UnauthorizedException;
import jp.co.gxp.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static jp.co.gxp.domain.RegionEnum.Region.getPrefecturesFromRegions;

@RequestMapping("/api")
@RestController
@Slf4j
@RequiredArgsConstructor
public class JobApiController implements JobApi{
    private final JobService jobService;
    private static final Logger logger = LoggerFactory.getLogger(JobApiController.class);

    //求人リスト全部取得
    @Override
    public ResponseEntity<JobsModel> jobJobListGet() {
        List<JobEntity> jobs = jobService.fetchJobsAll();
        Map<Integer, Double> ForeignRatiosList = new HashMap<>();
        for (JobEntity job : jobs) {
            List<ForeignRatioEntity> foreignRatioEntities = jobService.calculateForeignRatioById(job.getJobId());
            ForeignRatiosList.put(job.getJobId(), 0.0);
            for (ForeignRatioEntity foreignRatioEntity : foreignRatioEntities) {
                ForeignRatiosList.put(job.getJobId(), foreignRatioEntity.getForeignRatio() + ForeignRatiosList.get(job.getJobId()));
            }
        }
        return new ResponseEntity<>(JobsModelConverter.convert(jobs,ForeignRatiosList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> jobIsLoginLikePost(LikeRequest likeRequest) {
        var jobId = likeRequest.getJobId();
        var loginUserId = AuthorizationUserAccessor.getUserEntity().map(UserEntity::getUserId)
                .orElseThrow(() -> new UnauthorizedException("unauthorized"));
        log.info("user id: {}, {}", loginUserId,jobId);
        jobService.jobLikePost(loginUserId, jobId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //求人お気に入り削除
    @Override
    public ResponseEntity<Void> jobIsLoginJobIdDelete(Integer jobId){
        var loginUserId = AuthorizationUserAccessor.getUserEntity().map(UserEntity::getUserId)
                .orElseThrow(() -> new UnauthorizedException("unauthorized"));
        log.info("user id: {}, {}", loginUserId,jobId);

        jobService.jobLikeDelete(loginUserId, jobId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //お気に入った求人リストjobId取得
    @Override
    public ResponseEntity<JobIsLoginLikeJobListGet200Response> jobIsLoginLikeJobListGet() {
        var loginUserId = AuthorizationUserAccessor.getUserEntity().map(UserEntity::getUserId)
                .orElseThrow(() -> new UnauthorizedException("unauthorized"));
        List<Integer> jobIds = jobService.fetchLikedJobIdsByUserId(loginUserId);
        return new ResponseEntity<>(new JobIsLoginLikeJobListGet200Response().jobIds(jobIds),HttpStatus.OK);
    }

    //求人詳細ページ用
    @Override
    public ResponseEntity<JobDetailModel> jobJobIdGet(Integer jobId) {
        JobDetailAllEntity jobDetail =  jobService.fetchJobDetailById(jobId);
        return new ResponseEntity<>(JobDetailModelConverter.convert(jobDetail), HttpStatus.OK);
    }

    //サイドバー検索
    @Override
    public ResponseEntity<JobsModel> jobSearchGet(List<String> selectedJobNames, List<String> selectedSkills, List<String> selectedJapaneseLevel, List<String> selectedLocations, List<String> selectedSalary) {
        if(selectedJobNames!=null)
            selectedJobNames=filterEmptyStrings(selectedJobNames);
        if(selectedSkills!=null)
            selectedSkills=filterEmptyStrings(selectedSkills);
        if(selectedLocations!=null)
            selectedLocations=filterEmptyStrings(selectedLocations);
        List<String> Locations = new ArrayList<>() ;
        //Locationsのパラメータで検査結果の都道府県を保存する
        try {
            Locations = getPrefecturesFromRegions(selectedLocations);
        } catch (Exception e) {
            logger.error("There is a error: {}", e.getMessage(), e);
        }
        List<JobEntity> jobs;
            //絞り込み機能
            jobs = jobService.fetchJobByCheckBoxes(selectedJobNames, selectedSkills, selectedJapaneseLevel, Locations, selectedSalary);
        //結果のListで外国人比率を計算する
        Map<Integer,Double> ForeignRatiosList= calculateForeignRatio(jobs);
        return new ResponseEntity<>(JobsModelConverter.convert(jobs,ForeignRatiosList), HttpStatus.OK);
    }

    //詳細検索
    @Override
    public ResponseEntity<JobsModel> jobSearchMoreGet(List<String> selectedJobNames, List<String> selectedSkills, List<String> selectedJapaneseLevel, List<String> selectedLocations,
                                                      List<String> selectedSalary, List<String> selectedIndustry, List<String> selectedWorkExperience, List<String> selectedEmployeeNum,
                                                      List<String> selectedForeignCountries, List<String> selectedForeignRatio, List<String> selectedPickyAbout) {

        if(selectedJobNames!=null)
            selectedJobNames=filterEmptyStrings(selectedJobNames);
        if(selectedSkills!=null)
            selectedSkills=filterEmptyStrings(selectedSkills);
        if(selectedLocations!=null)
            selectedLocations=filterEmptyStrings(selectedLocations);
        if(selectedIndustry!=null)
            selectedIndustry=filterEmptyStrings(selectedIndustry);
        if(selectedWorkExperience!=null)
            selectedWorkExperience=filterEmptyStrings(selectedWorkExperience);
        if(selectedEmployeeNum!=null)
            selectedEmployeeNum=filterEmptyStrings(selectedEmployeeNum);
        if(selectedForeignCountries!=null)
            selectedForeignCountries=filterEmptyStrings(selectedForeignCountries);
        if(selectedForeignRatio!=null)
            selectedForeignRatio=filterEmptyStrings(selectedForeignRatio);
        if(selectedPickyAbout!=null)
            selectedPickyAbout=filterEmptyStrings(selectedPickyAbout);
        List<String> Locations = new ArrayList<>() ;
        //Locationsのパラメータで検査結果の都道府県を保存する
        if(selectedLocations!=null){
        try {
            Locations = getPrefecturesFromRegions(selectedLocations);
        } catch (Exception e) {
            logger.error("There is a error: {}", e.getMessage(), e);
        }}else{
            Locations=selectedLocations;
        }
        List<JobEntity> jobs;
            //絞り込み機能
            jobs = jobService.fetchJobByMoreCheckBoxes(selectedJobNames, selectedSkills, selectedJapaneseLevel, Locations, selectedSalary, selectedIndustry,
                    selectedWorkExperience, selectedEmployeeNum, selectedForeignCountries, selectedForeignRatio, selectedPickyAbout);
//        }
        //結果のListで外国人比率を計算する
        Map<Integer,Double> ForeignRatiosList= calculateForeignRatio(jobs);
        return new ResponseEntity<>(JobsModelConverter.convert(jobs,ForeignRatiosList), HttpStatus.OK);
    }

    //結果のListで外国人比率を計算するメソッド
    public Map<Integer,Double> calculateForeignRatio(List<JobEntity> jobs){
        Map<Integer, Double> ForeignRatiosList = new HashMap<>();
        for (JobEntity job : jobs) {
            List<ForeignRatioEntity> foreignRatioEntities = jobService.calculateForeignRatioById(job.getJobId());
            ForeignRatiosList.put(job.getJobId(), 0.0);
            for (ForeignRatioEntity foreignRatioEntity : foreignRatioEntities) {
                ForeignRatiosList.put(job.getJobId(), foreignRatioEntity.getForeignRatio() + ForeignRatiosList.get(job.getJobId()));
            }
        }
        return ForeignRatiosList;
    }

    public static List<String> filterEmptyStrings(List<String> inputList) {
        return inputList.stream()
                .filter(value -> !value.isEmpty())
                .collect(Collectors.toList());
    }
}
