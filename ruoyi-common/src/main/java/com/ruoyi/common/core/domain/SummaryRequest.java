package com.ruoyi.common.core.domain;


import lombok.Data;

import java.util.List;

@Data
public class SummaryRequest {
    private List<SummaryColumn> columns;
    private List<SummaryColumn> query;
    private List<SummaryColumn> groupBy;
    private List<SummaryColumn> orderBy;
}
