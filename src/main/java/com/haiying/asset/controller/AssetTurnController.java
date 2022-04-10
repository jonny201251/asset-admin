package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.bean.ButtonHandleBean;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.AssetTurn;
import com.haiying.asset.model.entity.ProcessInst;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.model.vo.AssetTurnAfter;
import com.haiying.asset.service.AssetTurnService;
import com.haiying.asset.service.ProcessInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 设备调拨 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/assetTurn")
@Wrapper
public class AssetTurnController {
    @Autowired
    AssetTurnService assetTurnService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    ProcessInstService processInstService;
    @Autowired
    ButtonHandleBean buttonHandleBean;

    @PostMapping("list")
    public IPage<AssetTurn> list(@RequestBody Map<String, Object> paramMap) {
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        IPage<AssetTurn> page;
        LambdaQueryWrapper<AssetTurn> wrapper = new LambdaQueryWrapper<>();
        SysUser user = (SysUser) httpSession.getAttribute("user");
//        wrapper.like(ProviderScore1::getLoginName, user.getLoginName()).orderByDesc(ProviderScore1::getId);
        page = assetTurnService.page(new Page<>(current, pageSize), wrapper);
        List<AssetTurn> recordList = page.getRecords();
        if (ObjectUtil.isNotEmpty(recordList)) {
            List<ProcessInst> processInstList = processInstService.list(new LambdaQueryWrapper<ProcessInst>().in(ProcessInst::getId, recordList.stream().map(AssetTurn::getProcessInstId).collect(Collectors.toList())));
            Map<Integer, ProcessInst> processInstMap = processInstList.stream().collect(Collectors.toMap(ProcessInst::getId, v -> v));
            recordList.forEach(record -> record.setProcessInst(processInstMap.get(record.getProcessInstId())));
        }
        return page;
    }

    @GetMapping("get")
    public AssetTurn get(Integer id) {
        return assetTurnService.getById(id);
    }

    @PostMapping("btnHandle")
    public boolean btnHandle(@RequestBody AssetTurnAfter after) {
        return assetTurnService.btnHandle(after);
    }
}
