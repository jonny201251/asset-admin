package com.haiying.asset.service;

import com.haiying.asset.model.entity.AssetScrap;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haiying.asset.model.vo.AssetScrapAfter;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废申请 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
public interface AssetScrapService extends IService<AssetScrap> {

    boolean btnHandle(AssetScrapAfter after, String menuName);
}
