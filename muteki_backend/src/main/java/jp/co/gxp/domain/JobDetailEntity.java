package jp.co.gxp.domain;

import lombok.Data;

import java.util.List;

@Data
public class JobDetailEntity {
    private final Integer jobDetailId;
    private final Integer jobId;
    private final String benefits;
    private final String workDescription;
    private final String workHours;
    private final String workRequirements;
    private final String workWelcome;
    private final String workTrialTime;
    private final String workYearSalary;
    private final String workGoodPoints;
    private final String workNeedPerson;
    private final String workEnvironment;
}
