package com.haiying.asset.model.vo;

import com.haiying.asset.model.entity.BatchCheck;
import com.haiying.asset.model.entity.InstrumentPlan;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class InstrumentPlanAfter extends ProcessFormAfter{
    private BatchCheck formValue;
    private List<InstrumentPlan> list;
}
