package jp.co.gxp.domain;

import lombok.Data;

@Data
public class JobEntity {
    private final Integer jobId;
    private final String jobName;
    private final String companyName;
    private final Integer japaneseLevel;
    private final Integer minSalary;
    private final Integer maxSalary;
    private final String location;
    private final String tag1;
    private final String tag2;
    private final String tag3;
}
