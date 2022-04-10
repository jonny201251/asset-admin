package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.InstrumentOut;
import com.haiying.asset.model.vo.InstrumentOutVO;
import com.haiying.asset.service.InstrumentOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 设备出库 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@RestController
@RequestMapping("/instrumentOut")
@Wrapper
public class InstrumentOutController {
    @Autowired
    InstrumentOutService instrumentOutService;

    @PostMapping("list")
    public IPage<InstrumentOut> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<InstrumentOut> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        if (ObjectUtil.isNotEmpty(type)) {
            wrapper.like(InstrumentOut::getDeptName, type);
        }
        if (ObjectUtil.isNotEmpty(name)) {
            wrapper.like(InstrumentOut::getName, name);
        }
        return instrumentOutService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody InstrumentOutVO instrumentOutVO) {
        return instrumentOutService.add(instrumentOutVO.getList());
    }

    @GetMapping("get")
    public InstrumentOut get(Integer id) {
        return instrumentOutService.getById(id);
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody InstrumentOut instrumentOut) {
        return instrumentOutService.edit(instrumentOut);
    }
}
