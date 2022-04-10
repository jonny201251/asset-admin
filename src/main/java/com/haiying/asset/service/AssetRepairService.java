package com.haiying.asset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.entity.AssetRepair;
import com.haiying.asset.model.vo.AssetRepairAfter;

/**
 * <p>
 * 维保检申请 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-31
 */
public interface AssetRepairService extends IService<AssetRepair> {

    boolean btnHandle(AssetRepairAfter after, String menuName);
}
