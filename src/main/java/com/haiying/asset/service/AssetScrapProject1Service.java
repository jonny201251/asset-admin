package com.haiying.asset.service;

import com.haiying.asset.model.entity.AssetScrapProject1;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废项目1 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
public interface AssetScrapProject1Service extends IService<AssetScrapProject1> {

    boolean add(AssetScrapProject1 assetScrapProject1);

    boolean edit(AssetScrapProject1 assetScrapProject1);
}
