package com.haiying.asset.service;

import com.haiying.asset.model.entity.InstrumentOut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 设备出库 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
public interface InstrumentOutService extends IService<InstrumentOut> {

    boolean add(List<InstrumentOut> list);

    boolean edit(InstrumentOut instrumentOut);
}
