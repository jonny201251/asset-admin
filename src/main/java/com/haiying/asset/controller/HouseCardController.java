package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.HouseCard;
import com.haiying.asset.model.vo.HouseCardVO;
import com.haiying.asset.service.HouseCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 房屋及构建筑物登记卡片 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-18
 */
@RestController
@RequestMapping("/houseCard")
@Wrapper
public class HouseCardController {
    @Autowired
    HouseCardService houseCardService;

    @PostMapping("list")
    public IPage<HouseCard> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<HouseCard> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        if (ObjectUtil.isNotEmpty(type)) {
            wrapper.like(HouseCard::getDeptName, type);
        }
        if (ObjectUtil.isNotEmpty(name)) {
            wrapper.like(HouseCard::getName, name);
        }
        return houseCardService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody HouseCardVO houseCardVO) {
        return houseCardService.add(houseCardVO.getList());
    }

    @GetMapping("get")
    public HouseCard get(Integer id) {
        return houseCardService.getById(id);
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody HouseCard houseCard) {
        return houseCardService.edit(houseCard);
    }
}
