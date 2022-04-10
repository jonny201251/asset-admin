package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.AssetMove;
import com.haiying.asset.service.AssetMoveService;
import com.haiying.asset.service.InstrumentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 设备部门内的移动 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/assetMove")
@Wrapper
public class AssetMoveController {
    @Autowired
    AssetMoveService assetMoveService;
    @Autowired
    InstrumentCardService instrumentCardService;

    @PostMapping("list")
    public IPage<AssetMove> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<AssetMove> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        if (ObjectUtil.isNotEmpty(name)) {
            wrapper.like(AssetMove::getName, name);
        }
        return assetMoveService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody AssetMove assetMove) {
        assetMove.setNewLoginName(assetMove.getNewDisplayName());
        return assetMoveService.save(assetMove);
    }

    @GetMapping("get")
    public AssetMove get(String id) {
        return assetMoveService.getById(id);
    }
    @PostMapping("edit")
    public boolean edit(@RequestBody AssetMove assetMove) {
        assetMove.setNewLoginName(assetMove.getNewDisplayName());
        return assetMoveService.save(assetMove);
    }
}
