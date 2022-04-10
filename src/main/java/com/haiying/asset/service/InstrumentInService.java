package com.haiying.asset.service;

import com.haiying.asset.model.entity.InstrumentIn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 设备入库 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
public interface InstrumentInService extends IService<InstrumentIn> {

    boolean add(List<InstrumentIn> list);

    boolean edit(InstrumentIn instrumentIn);
}
