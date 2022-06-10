package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.InstrumentCard;
import com.haiying.asset.model.vo.InstrumentCardVO;
import com.haiying.asset.service.InstrumentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 设备登记卡片 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@RestController
@RequestMapping("/instrumentCard")
@Wrapper
public class InstrumentCardController {
    @Autowired
    InstrumentCardService instrumentCardService;

    @PostMapping("list")
    public IPage<InstrumentCard> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<InstrumentCard> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object code = paramMap.get("code");
        if (ObjectUtil.isNotEmpty(code)) {
            wrapper.like(InstrumentCard::getCode, code);
        }
        return instrumentCardService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody InstrumentCardVO instrumentCardVO) {
        return instrumentCardService.add(instrumentCardVO.getList());
    }

    @GetMapping("get")
    public InstrumentCard get(Integer id) {
        return instrumentCardService.getById(id);
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody InstrumentCard instrumentCard) {
        return instrumentCardService.edit(instrumentCard);
    }
}
