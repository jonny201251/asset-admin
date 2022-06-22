package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.AssetFinanceCode;
import com.haiying.asset.model.entity.OfficeToolCard;
import com.haiying.asset.model.vo.OfficeToolCardVO;
import com.haiying.asset.service.AssetFinanceCodeService;
import com.haiying.asset.service.OfficeToolCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 办公营具登记卡片 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@RestController
@RequestMapping("/officeToolCard")
@Wrapper
public class OfficeToolCardController {
    @Autowired
    OfficeToolCardService officeToolCardService;
    @Autowired
    AssetFinanceCodeService assetFinanceCodeService;

    @PostMapping("list")
    public IPage<OfficeToolCard> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<OfficeToolCard> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        if (ObjectUtil.isNotEmpty(type)) {
            wrapper.like(OfficeToolCard::getDeptName, type);
        }
        if (ObjectUtil.isNotEmpty(name)) {
            wrapper.like(OfficeToolCard::getName, name);
        }
        return officeToolCardService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody OfficeToolCardVO officeToolCardVO) {
        return officeToolCardService.add(officeToolCardVO.getList());
    }

    @GetMapping("get")
    public OfficeToolCard get(Integer id) {
        OfficeToolCard officeToolCard = officeToolCardService.getById(id);
        officeToolCard.setList(assetFinanceCodeService.list(new LambdaQueryWrapper<AssetFinanceCode>().eq(AssetFinanceCode::getGuid, officeToolCard.getGuid())));
        return officeToolCard;
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody OfficeToolCard officeToolCard) {
        return officeToolCardService.edit(officeToolCard);
    }
}
