package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.AssetAccept;
import com.haiying.asset.model.vo.AssetAcceptVO;
import com.haiying.asset.service.AssetAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 维保检验收 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/assetAccept")
@Wrapper
public class AssetAcceptController {
    @Autowired
    AssetAcceptService assetAcceptService;

    @PostMapping("list")
    public IPage<AssetAccept> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<AssetAccept> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        if (ObjectUtil.isNotEmpty(type)) {
            wrapper.like(AssetAccept::getDeptName, type);
        }
        if (ObjectUtil.isNotEmpty(name)) {
            wrapper.like(AssetAccept::getName, name);
        }
        return assetAcceptService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody AssetAcceptVO assetAcceptVO) {
        return assetAcceptService.add(assetAcceptVO.getList());
    }

    @GetMapping("get")
    public AssetAccept get(Integer id) {
        return assetAcceptService.getById(id);
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody AssetAccept assetAccept) {
        return assetAcceptService.edit(assetAccept);
    }
}
