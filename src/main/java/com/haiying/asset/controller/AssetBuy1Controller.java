package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.bean.ButtonHandleBean;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.BatchCheck;
import com.haiying.asset.model.entity.AssetBuy;
import com.haiying.asset.model.entity.ProcessInst;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.model.vo.AssetBuyAfter;
import com.haiying.asset.service.BatchCheckService;
import com.haiying.asset.service.AssetBuyService;
import com.haiying.asset.service.ProcessInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 设备仪器仪表计划 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/assetBuy1")
@Wrapper
public class AssetBuy1Controller {
    @Autowired
    AssetBuyService assetBuyService;
    @Autowired
    BatchCheckService batchCheckService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    ProcessInstService processInstService;
    @Autowired
    ButtonHandleBean buttonHandleBean;

    @PostMapping("list")
    public IPage<BatchCheck> list(@RequestBody Map<String, Object> paramMap) {
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        IPage<BatchCheck> page;
        LambdaQueryWrapper<BatchCheck> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BatchCheck::getMenuName, "办公营具购置").orderByDesc(BatchCheck::getId);
        SysUser user = (SysUser) httpSession.getAttribute("user");
//        wrapper.like(ProviderScore1::getLoginName, user.getLoginName()).orderByDesc(ProviderScore1::getId);
        page = batchCheckService.page(new Page<>(current, pageSize), wrapper);
        List<BatchCheck> recordList = page.getRecords();
        if (ObjectUtil.isNotEmpty(recordList)) {
            List<ProcessInst> processInstList = processInstService.list(new LambdaQueryWrapper<ProcessInst>().in(ProcessInst::getId, recordList.stream().map(BatchCheck::getProcessInstId).collect(Collectors.toList())));
            Map<Integer, ProcessInst> processInstMap = processInstList.stream().collect(Collectors.toMap(ProcessInst::getId, v -> v));
            recordList.forEach(record -> record.setProcessInst(processInstMap.get(record.getProcessInstId())));
        }
        return page;
    }

    @GetMapping("get")
    public BatchCheck get(Integer id) {
        BatchCheck batchCheck = batchCheckService.getById(id);
        List<AssetBuy> list = assetBuyService.list(new LambdaQueryWrapper<AssetBuy>().eq(AssetBuy::getBatchId, id));
        batchCheck.setList(list);
        return batchCheck;
    }

    @PostMapping("btnHandle")
    public boolean btnHandle(@RequestBody AssetBuyAfter after) {
        return assetBuyService.btnHandle(after,"办公营具购置");
    }
}
