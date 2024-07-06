package jp.co.gxp.infrastructure.mapper;

import jp.co.gxp.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobMapper {
    List<JobEntity> fetchJobsAll();

    //求人IDによって各国の外国人比率のリストをFetch
    List<ForeignRatioEntity> calculateForeignRatioById(@Param("jobId")Integer jobId);

    //求人詳細を求人IDから取得
    JobDetailEntity fetchJobDetailByJobId(@Param("jobId")Integer jobId);

    //求人詳細ページに表す画像取得
    List<JobImgEntity> fetchJobImgByJobDetailId(@Param("jobDetailId")Integer jobDetailId);

    //求人List情報と会社と業界情報を取得
    CompanyJobEntity fetchCompanyJobByJobId(@Param("jobId")Integer jobId);

    //スキルリストを求人IDで取得
    List<SkillEntity> fetchSkillByJobId(@Param("jobId")Integer jobId);

    List<JobEntity> fetchJobByCheckBoxes(@Param("selectedJobNames")List<String> selectedJobNames,
                                         @Param("selectedSkills") List<String> selectedSkills,
                                         @Param("selectedJapaneseLevel") Integer selectedJapaneseLevel,
                                         @Param("selectedLocations") List<String> selectedLocations,
                                         @Param("selectedSalary") Integer selectedSalary);

    List<JobEntity> fetchJobByMoreCheckBoxes(@Param("selectedJobNames")List<String> selectedJobNames,
                                             @Param("selectedSkills")List<String> selectedSkills,
                                             @Param("selectedJapaneseLevel")Integer selectedJapaneseLevel,
                                             @Param("selectedLocations")List<String> selectedLocations,
                                             @Param("selectedSalary")Integer selectedSalary,
                                             @Param("selectedIndustries")List<String> selectedIndustries,
                                             @Param("selectedWorkExperienceMin")Integer selectedWorkExperienceMin,
                                             @Param("selectedWorkExperienceMax")Integer selectedWorkExperienceMax,
                                             @Param("selectedEmployeeNumMin")Integer selectedEmployeeNumMin,
                                             @Param("selectedEmployeeNumMax")Integer selectedEmployeeNumMax,
                                             @Param("selectedForeignCountries")List<String> selectedForeignCountries,
                                             @Param("selectedForeignRatio")Double selectedForeignRatio,
                                             @Param("selectedPickyAbout")List<String> selectedPickyAbout);

    void jobLikePost( @Param("userId")Integer userId, @Param("jobId")Integer jobId);

    void jobLikeDelete(@Param("userId")Integer userId, @Param("jobId")Integer jobId);

    List<Integer> fetchLikedJobIdsByUserId(@Param("userId") Integer loginUserId);
}
