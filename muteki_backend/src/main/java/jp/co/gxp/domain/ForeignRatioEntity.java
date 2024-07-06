package jp.co.gxp.domain;

import lombok.Data;

@Data
public class ForeignRatioEntity {
    private final Integer foreignRatioId;
    private final Integer jobId;
    private final String country;
    private final Double foreignRatio;
}
