package jp.co.gxp.infrastructure.repository;

import jp.co.gxp.domain.*;
import jp.co.gxp.domain.user.UserEntity;
import jp.co.gxp.infrastructure.mapper.JobMapper;
import jp.co.gxp.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
public class JobRepositoryDb implements JobRepository {

    private final JobMapper jobMapper;
    private final UserMapper userMapper;

    @Override
    public List<JobEntity> fetchJobsAll() {
        return jobMapper.fetchJobsAll();
    }

    @Override
    public List<ForeignRatioEntity> calculateForeignRatioById(Integer jobId) {
        return jobMapper.calculateForeignRatioById(jobId);
    }

    //求人詳細の全情報の取得
    @Override
    public JobDetailAllEntity fetchJobDetailById(Integer jobId) {
        JobDetailEntity jobDetail = jobMapper.fetchJobDetailByJobId(jobId);
        List<JobImgEntity> jobImgs = jobMapper.fetchJobImgByJobDetailId(jobDetail.getJobDetailId());
        CompanyJobEntity companyJob = jobMapper.fetchCompanyJobByJobId(jobId);
        List<SkillEntity> skills = jobMapper.fetchSkillByJobId(jobId);
        List<ForeignRatioEntity> foreignRatios = jobMapper.calculateForeignRatioById(jobId);
        return new JobDetailAllEntity(jobDetail,
                companyJob.getIndustryName(),
                foreignRatios,skills,jobImgs,companyJob);
    }

    @Override
    public List<JobEntity> fetchJobByCheckBoxes(List<String> selectedJobNames, List<String> selectedSkills, List<String> selectedJapaneseLevel, List<String> selectedLocations, List<String> selectedSalary) {
        // List<String>は一つの数字だけなので、データを事前に処理します
        Integer selectedJapaneseLevel1 = null;
        Integer selectedSalary1 = null;
        if (selectedJapaneseLevel != null && !selectedJapaneseLevel.isEmpty()) {
            try {
                selectedJapaneseLevel1 = Integer.parseInt(selectedJapaneseLevel.getFirst());
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse selectedJapaneseLevel: " + e.getMessage());
            }
        }
        if (selectedSalary != null && !selectedSalary.isEmpty()) {
            try {
                selectedSalary1 = Integer.parseInt(selectedSalary.getFirst());
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse selectedSalary: " + e.getMessage());
            }
        }
        return jobMapper.fetchJobByCheckBoxes(selectedJobNames, selectedSkills, selectedJapaneseLevel1, selectedLocations, selectedSalary1);
    }

    @Override
    public List<JobEntity> fetchJobByMoreCheckBoxes(List<String> selectedJobNames, List<String> selectedSkills, List<String> selectedJapaneseLevel, List<String> selectedLocations, List<String> selectedSalary, List<String> selectedIndustry, List<String> selectedWorkExperience, List<String> selectedEmployeeNum, List<String> selectedForeignCountries, List<String> selectedForeignRatio, List<String> selectedPickyAbout) {
        // List<String>は一つの数字だけなので、データを事前に処理します
        Integer selectedJapaneseLevel1 = null;
        Integer selectedSalary1 = null;
        Double selectedForeignRatio1 =null;
        //範囲なので、最大最小でやる
        Integer selectedWorkExperienceMin = null;
        Integer selectedWorkExperienceMax = null;
        Integer selectedEmployeeNumMin = null;
        Integer selectedEmployeeNumMax = null;


        if (selectedJapaneseLevel != null && !selectedJapaneseLevel.isEmpty()) {
            try {
                selectedJapaneseLevel1 = Integer.parseInt(selectedJapaneseLevel.getFirst());
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse selectedJapaneseLevel: " + e.getMessage());
            }
        }
        if (selectedSalary != null && !selectedSalary.isEmpty()) {
            try {
                selectedSalary1 = Integer.parseInt(selectedSalary.getFirst());
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse selectedSalary: " + e.getMessage());
            }
        }
        if (selectedForeignRatio != null && !selectedForeignRatio.isEmpty()) {
            String ratioString = selectedForeignRatio.getFirst();
            try {
                // 正規表現で数字を取得
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(ratioString);
                if (matcher.find()) {
                    // 数字
                    String numberString = matcher.group();
                    // 比率に転換
                    selectedForeignRatio1 = Double.parseDouble(numberString) / 100.0;
                    System.out.println(selectedForeignRatio1);
                } else {
                    System.err.println("No number found in selectedForeignRatio: " + ratioString);
                }
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse selectedForeignRatio: " + e.getMessage());
            }
        }
        //企業規模データの処理
        if (selectedEmployeeNum != null && !selectedEmployeeNum.isEmpty()) {
            if(selectedEmployeeNum.getFirst().equals("11人から50人")){
                selectedEmployeeNumMin=11;
                selectedEmployeeNumMax=50;}
            if(selectedEmployeeNum.getFirst().equals("51人から200人の従業員")){
                selectedEmployeeNumMin=51;
                selectedEmployeeNumMax=200;}
            if(selectedEmployeeNum.getFirst().equals("201人から500人の従業員")){
                selectedEmployeeNumMin=201;
                selectedEmployeeNumMax=500;}
            if(selectedEmployeeNum.getFirst().equals("501人から1000人の従業員")){
                selectedEmployeeNumMin=501;
                selectedEmployeeNumMax=1000;}
            if(selectedEmployeeNum.getFirst().equals("1001人以上の従業員")){
                selectedEmployeeNumMin=1001;
                selectedEmployeeNumMax=20000;}
        }
        //仕事経験データの処理
        if (selectedWorkExperience != null && !selectedWorkExperience.isEmpty()) {
            if(selectedWorkExperience.getFirst().equals("仕事経験なし")){
                selectedWorkExperienceMin=0;
                selectedWorkExperienceMax=0;}
            if(selectedWorkExperience.getFirst().equals("1-3年の仕事経験")){
                selectedWorkExperienceMin=1;
                selectedWorkExperienceMax=3;}
            if(selectedWorkExperience.getFirst().equals("3-5年の仕事経験")){
                selectedWorkExperienceMin=3;
                selectedWorkExperienceMax=5;}
            if(selectedWorkExperience.getFirst().equals("5年以上の仕事経験")){
                selectedWorkExperienceMin=5;
                selectedWorkExperienceMax=100;}
        }
        return jobMapper.fetchJobByMoreCheckBoxes(selectedJobNames, selectedSkills, selectedJapaneseLevel1, selectedLocations, selectedSalary1,selectedIndustry,
                selectedWorkExperienceMin,selectedWorkExperienceMax,selectedEmployeeNumMin,selectedEmployeeNumMax,selectedForeignCountries,selectedForeignRatio1,selectedPickyAbout);
    }

    @Override
    public void jobLikePost(Integer userId, Integer jobId) {
        jobMapper.jobLikePost(userId,jobId);
    }

    @Override
    public void jobLikeDelete(Integer userId, Integer jobId){
        jobMapper.jobLikeDelete(userId, jobId);
    }

    @Override
    public List<Integer> fetchLikedJobIdsByUserId(Integer loginUserId) {
        return jobMapper.fetchLikedJobIdsByUserId(loginUserId);
    }
}
