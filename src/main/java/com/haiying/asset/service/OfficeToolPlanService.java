package com.haiying.asset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.entity.OfficeToolPlan;
import com.haiying.asset.model.vo.OfficeToolPlanAfter;

/**
 * <p>
 * 办公营具计划 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
public interface OfficeToolPlanService extends IService<OfficeToolPlan> {

    boolean btnHandle(OfficeToolPlanAfter after);
}
