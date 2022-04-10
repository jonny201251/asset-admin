package com.haiying.asset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.entity.ProcessInst;

/**
 * <p>
 * 流程实例 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-02-14
 */
public interface ProcessInstService extends IService<ProcessInst> {

    void delete(ProcessInst processInst);
}
