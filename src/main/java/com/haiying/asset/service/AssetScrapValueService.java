package com.haiying.asset.service;

import com.haiying.asset.model.entity.AssetScrapValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.vo.AssetScrapValueAfter;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废价值鉴定 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
public interface AssetScrapValueService extends IService<AssetScrapValue> {

    boolean btnHandle(AssetScrapValueAfter after, String menuName);
}
