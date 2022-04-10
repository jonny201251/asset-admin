package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.bean.ButtonHandleBean;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.*;
import com.haiying.asset.model.vo.AssetRepairAfter;
import com.haiying.asset.model.vo.FileVO;
import com.haiying.asset.service.AssetFileService;
import com.haiying.asset.service.AssetRepairService;
import com.haiying.asset.service.BatchCheckService;
import com.haiying.asset.service.ProcessInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 维保检申请 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/assetRepair")
@Wrapper
public class AssetRepairController {
    @Autowired
    AssetRepairService assetRepairService;
    @Autowired
    BatchCheckService batchCheckService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    ProcessInstService processInstService;
    @Autowired
    ButtonHandleBean buttonHandleBean;
    @Autowired
    AssetFileService assetFileService;

    @PostMapping("list")
    public IPage<BatchCheck> list(@RequestBody Map<String, Object> paramMap) {
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        IPage<BatchCheck> page;
        LambdaQueryWrapper<BatchCheck> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BatchCheck::getMenuName, "维保检申请").orderByDesc(BatchCheck::getId);
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
        List<AssetRepair> list = assetRepairService.list(new LambdaQueryWrapper<AssetRepair>().eq(AssetRepair::getBatchId, id));
        //文件
        List<Integer> idList = list.stream().map(AssetRepair::getId).collect(Collectors.toList());
        List<AssetFile> assetFileList = assetFileService.list(new LambdaQueryWrapper<AssetFile>().eq(AssetFile::getType, "AssetRepair").in(AssetFile::getAssetId, idList));
        if (ObjectUtil.isNotEmpty(assetFileList)) {
            for (AssetRepair assetRepair : list) {
                List<FileVO> fileList = new ArrayList<>();
                for (AssetFile assetFile : assetFileList) {
                    if (assetRepair.getId().equals(assetFile.getAssetId())) {
                        FileVO fileVO = new FileVO();
                        fileVO.setName(assetFile.getName());
                        fileVO.setUrl(assetFile.getUrl());
                        fileVO.setStatus("done");
                        fileList.add(fileVO);
                    }
                }
                if (ObjectUtil.isNotEmpty(fileList)) {
                    assetRepair.setFileList(fileList);
                }
            }
        }
        batchCheck.setList(list);
        return batchCheck;
    }

    @PostMapping("btnHandle")
    public boolean btnHandle(@RequestBody AssetRepairAfter after) {
        return assetRepairService.btnHandle(after, "维保检申请");
    }

    @PostMapping("dialogList")
    public List<AssetRepair> dialogList(@RequestBody Map<String, Object> paramMap) {
//        String current = (String) paramMap.get("current");
        return assetRepairService.list();
    }
}
