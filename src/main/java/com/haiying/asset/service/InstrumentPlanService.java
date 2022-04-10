package com.haiying.asset.service;

import com.haiying.asset.model.entity.InstrumentPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.vo.InstrumentPlanAfter;

/**
 * <p>
 * 设备仪器仪表计划 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
public interface InstrumentPlanService extends IService<InstrumentPlan> {

    boolean btnHandle(InstrumentPlanAfter after);
}
