package jp.co.gxp.domain;

import java.util.List;

public interface JobRepository {
    List<JobEntity> fetchJobsAll();
    List<ForeignRatioEntity> calculateForeignRatioById(Integer jobId);

    JobDetailAllEntity fetchJobDetailById(Integer jobId);

    List<JobEntity> fetchJobByCheckBoxes(List<String> selectedJobNames,
                                         List<String> selectedSkills,
                                         List<String> selectedJapaneseLevel,
                                         List<String> selectedLocations,
                                         List<String> selectedSalary);

    List<JobEntity> fetchJobByMoreCheckBoxes(List<String> selectedJobNames, List<String> selectedSkills,
                                             List<String> selectedJapaneseLevel, List<String> locations,
                                             List<String> selectedSalary, List<String> selectedIndustry,
                                             List<String> selectedWorkExperience, List<String> selectedEmployeeNum,
                                             List<String> selectedForeignCountries, List<String> selectedForeignRatio,
                                             List<String> selectedPickyAbout);

    void jobLikePost(Integer userId, Integer jobId);

    void jobLikeDelete(Integer userId, Integer jobId);

    List<Integer> fetchLikedJobIdsByUserId(Integer loginUserId);
}
