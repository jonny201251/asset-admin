package com.haiying.asset.service;

import com.haiying.asset.model.entity.AssetTurn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.vo.AssetTurnAfter;

/**
 * <p>
 * 设备调拨 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
public interface AssetTurnService extends IService<AssetTurn> {

    boolean btnHandle(AssetTurnAfter after);
}
