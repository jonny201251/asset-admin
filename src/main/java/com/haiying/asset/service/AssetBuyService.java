package com.haiying.asset.service;

import com.haiying.asset.model.entity.AssetBuy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.vo.AssetBuyAfter;

/**
 * <p>
 * 办公营具和设备仪器仪表-购置 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-23
 */
public interface AssetBuyService extends IService<AssetBuy> {

    boolean btnHandle(AssetBuyAfter after,String menuName);
}
