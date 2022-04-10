package com.haiying.asset.service;

import com.haiying.asset.model.entity.AssetAccept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 维保检验收 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-31
 */
public interface AssetAcceptService extends IService<AssetAccept> {

    boolean edit(AssetAccept assetAccept);

    boolean add(List<AssetAccept> list);
}
