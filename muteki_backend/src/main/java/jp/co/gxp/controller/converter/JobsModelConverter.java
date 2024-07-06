package jp.co.gxp.controller.converter;

import jp.co.gxp.controller.model.JobModel;
import jp.co.gxp.controller.model.JobsModel;
import jp.co.gxp.domain.JobEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobsModelConverter {
    //JobのListをJobsModelに変わる
    public static JobsModel convert(List<JobEntity> jobEntityList, Map<Integer, Double> ForeignRatiosList) {
        return new JobsModel(
                jobEntityList.stream().map(e -> new JobModel(
                        e.getJobId(),
                        e.getJobName(),
                        e.getCompanyName(),
                        e.getJapaneseLevel(),
                        e.getMinSalary(),
                        e.getMaxSalary(),
                        e.getLocation(),
                        e.getTag1(),
                        e.getTag2(),
                        e.getTag3(),
                        ForeignRatiosList.get(e.getJobId()))//求人ごとの全部国籍の外国人/全社員
                ).collect(Collectors.toList())
        );
    }
}
