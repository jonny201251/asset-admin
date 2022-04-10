package com.haiying.asset.service;

import com.haiying.asset.model.entity.InstrumentCard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 设备登记卡片 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
public interface InstrumentCardService extends IService<InstrumentCard> {

    boolean add(List<InstrumentCard> list);

    boolean edit(InstrumentCard instrumentCard);
}
