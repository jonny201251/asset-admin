package com.haiying.asset.service.impl;

import com.haiying.asset.model.entity.HouseCard;
import com.haiying.asset.mapper.HouseCardMapper;
import com.haiying.asset.service.HouseCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 房屋及构建筑物登记卡片 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-18
 */
@Service
public class HouseCardServiceImpl extends ServiceImpl<HouseCardMapper, HouseCard> implements HouseCardService {

    @Override
    public boolean add(List<HouseCard> list) {
        return false;
    }

    @Override
    public boolean edit(HouseCard houseCard) {
        return false;
    }
}
