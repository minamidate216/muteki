package jp.co.gxp.service;

import jp.co.gxp.domain.ForeignRatioEntity;
import jp.co.gxp.domain.JobDetailAllEntity;
import jp.co.gxp.domain.JobEntity;
import jp.co.gxp.domain.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    //全求人リスト取得
    public List<JobEntity> fetchJobsAll() {
        return jobRepository.fetchJobsAll();
    }

    //（リスト表示用）求人IDで全部国籍の人数の比率を取得
    public List<ForeignRatioEntity> calculateForeignRatioById(Integer jobId) {
        return jobRepository.calculateForeignRatioById(jobId);
    }

    //求人の詳細をIDで探す
    public JobDetailAllEntity fetchJobDetailById(Integer jobId) {
        return jobRepository.fetchJobDetailById(jobId);
    }

    //サイドバー検索
    public List<JobEntity> fetchJobByCheckBoxes(List<String> selectedJobNames, List<String> selectedSkills, List<String> selectedJapaneseLevel, List<String> selectedLocations, List<String> selectedSalary) {
        return jobRepository.fetchJobByCheckBoxes(selectedJobNames, selectedSkills, selectedJapaneseLevel, selectedLocations, selectedSalary);
    }

    //詳細検索
    public List<JobEntity> fetchJobByMoreCheckBoxes(List<String> selectedJobNames, List<String> selectedSkills, List<String> selectedJapaneseLevel, List<String> selectedLocations, List<String> selectedSalary, List<String> selectedIndustry, List<String> selectedWorkExperience, List<String> selectedEmployeeNum, List<String> selectedForeignCountries, List<String> selectedForeignRatio, List<String> selectedPickyAbout) {
        return jobRepository.fetchJobByMoreCheckBoxes(selectedJobNames, selectedSkills, selectedJapaneseLevel, selectedLocations, selectedSalary,selectedIndustry,
                selectedWorkExperience,selectedEmployeeNum,selectedForeignCountries,selectedForeignRatio,selectedPickyAbout);
    }

    public void jobLikePost(Integer userId, Integer jobId) {
        jobRepository.jobLikePost(userId,jobId);
    }
    //求人お気に入り削除
    public void jobLikeDelete(Integer userId, Integer jobId) {
        jobRepository.jobLikeDelete(userId,jobId);
    }

    public List<Integer> fetchLikedJobIdsByUserId(Integer loginUserId) {
        return jobRepository.fetchLikedJobIdsByUserId(loginUserId);
    }
}
