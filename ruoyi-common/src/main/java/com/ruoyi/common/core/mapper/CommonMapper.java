package com.ruoyi.common.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.common.core.domain.SummaryRequest;

import java.util.List;

public interface CommonMapper<T> extends BaseMapper<T> {
     T getSummary(T T);
     List<T> summary(SummaryRequest T);
     T allSummary(SummaryRequest T);

     int batchInsertOrUpdate(List<T> list);
}
