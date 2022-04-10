package com.haiying.asset.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.InstrumentPlan;
import com.haiying.asset.service.InstrumentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 设备仪器仪表计划 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/instrumentPlan2")
@Wrapper
public class InstrumentPlan2Controller {
    @Autowired
    InstrumentPlanService instrumentPlanService;
    @Autowired
    HttpSession httpSession;

    @PostMapping("list")
    public IPage<InstrumentPlan> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<InstrumentPlan> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        return instrumentPlanService.page(new Page<>(current, pageSize), wrapper);
    }

    @GetMapping("get")
    public InstrumentPlan get(Integer id) {
       return  instrumentPlanService.getById(id);
    }
}
