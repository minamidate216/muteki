package jp.co.gxp.controller.converter;

import jp.co.gxp.controller.model.*;
import jp.co.gxp.domain.JobDetailAllEntity;
import jp.co.gxp.domain.JobDetailEntity;

import java.util.stream.Collectors;

public class JobDetailModelConverter {
    public static JobDetailModel convert(JobDetailAllEntity jobDetailAll){
        return new JobDetailModel(jobDetailAll.getJobDetail().getJobId(),
                jobDetailAll.getJobDetail().getBenefits(),
                jobDetailAll.getJobDetail().getWorkDescription(),
                jobDetailAll.getJobDetail().getWorkHours(),
                jobDetailAll.getJobDetail().getWorkRequirements(),
                jobDetailAll.getJobDetail().getWorkWelcome(),
                jobDetailAll.getJobDetail().getWorkTrialTime(),
                jobDetailAll.getJobDetail().getWorkYearSalary(),
                jobDetailAll.getJobDetail().getWorkGoodPoints(),
                jobDetailAll.getJobDetail().getWorkNeedPerson(),
                jobDetailAll.getJobDetail().getWorkEnvironment(),
                jobDetailAll.getIndustry(),
                jobDetailAll.getSkills().stream().map(skill -> new SkillModel(
                        skill.getSkillId(),
                        skill.getSkillName()
                )).collect(Collectors.toList()),
                jobDetailAll.getJobImgs().stream().map(img -> new JobImgModel(
                        img.getImgId(),
                        img.getImgUrl(),
                        img.getJobDetailId()
                )).collect(Collectors.toList()),
                new JobModel(jobDetailAll.getJobInCard().getJobId(),
                        jobDetailAll.getJobInCard().getJobName(),
                        jobDetailAll.getJobInCard().getCompanyName(),
                        jobDetailAll.getJobInCard().getJapaneseLevel(),
                        jobDetailAll.getJobInCard().getMinSalary(),
                        jobDetailAll.getJobInCard().getMaxSalary(),
                        jobDetailAll.getJobInCard().getLocation(),
                        jobDetailAll.getJobInCard().getTag1(),
                        jobDetailAll.getJobInCard().getTag2(),
                        jobDetailAll.getJobInCard().getTag3(),
                        jobDetailAll.getForeignRatios().get(0).getForeignRatio()),
                jobDetailAll.getForeignRatios().stream().map(ratio ->new ForeignRatioModel(ratio.getForeignRatioId(),
                        ratio.getForeignRatio(),
                        ratio.getCountry())).collect(Collectors.toList())
                );
    }
}
