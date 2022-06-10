package com.haiying.asset.service;

import com.haiying.asset.model.entity.HouseCard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 房屋及构建筑物登记卡片 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-18
 */
public interface HouseCardService extends IService<HouseCard> {

    boolean add(List<HouseCard> list);

    boolean edit(HouseCard houseCard);
}
