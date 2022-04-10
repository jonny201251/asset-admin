package com.haiying.asset.service;

import com.haiying.asset.model.entity.OfficeToolCard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 办公营具登记卡片 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
public interface OfficeToolCardService extends IService<OfficeToolCard> {

    boolean add(List<OfficeToolCard> list);

    boolean edit(OfficeToolCard officeToolCard);
}
