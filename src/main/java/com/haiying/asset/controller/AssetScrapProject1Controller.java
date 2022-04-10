package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.AssetScrapProject1;
import com.haiying.asset.model.entity.AssetScrapProject2;
import com.haiying.asset.service.AssetScrapProject1Service;
import com.haiying.asset.service.AssetScrapProject2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废项目1 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/assetScrapProject1")
@Wrapper
public class AssetScrapProject1Controller {
    @Autowired
    AssetScrapProject1Service assetScrapProject1Service;
    @Autowired
    AssetScrapProject2Service assetScrapProject2Service;

    @PostMapping("list")
    public IPage<AssetScrapProject1> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<AssetScrapProject1> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        if (ObjectUtil.isNotEmpty(type)) {
            wrapper.like(AssetScrapProject1::getId, type);
        }
        return assetScrapProject1Service.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody AssetScrapProject1 assetScrapProject1) {
        return assetScrapProject1Service.add(assetScrapProject1);
    }

    @GetMapping("get")
    public AssetScrapProject1 get(Integer id) {
        AssetScrapProject1 assetScrapProject1 = assetScrapProject1Service.getById(id);
        List<AssetScrapProject2> list = assetScrapProject2Service.list(new LambdaQueryWrapper<AssetScrapProject2>().eq(AssetScrapProject2::getProject1Id, id));
        assetScrapProject1.setList(list);
        return assetScrapProject1;
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody AssetScrapProject1 assetScrapProject1) {
        return assetScrapProject1Service.edit(assetScrapProject1);
    }

}
