package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.bean.ButtonHandleBean;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.*;
import com.haiying.asset.model.vo.AssetScrapValueAfter;
import com.haiying.asset.model.vo.FileVO;
import com.haiying.asset.service.AssetFileService;
import com.haiying.asset.service.AssetScrapValueService;
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
 * 办公营具和设备仪器仪表-报废价值鉴定 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/assetScrapValue")
@Wrapper
public class AssetScrapValueController {
    @Autowired
    AssetScrapValueService assetScrapValueService;
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
        wrapper.eq(BatchCheck::getMenuName, "报废价值鉴定").orderByDesc(BatchCheck::getId);
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
        List<AssetScrapValue> list = assetScrapValueService.list(new LambdaQueryWrapper<AssetScrapValue>().eq(AssetScrapValue::getBatchId, id));
        //文件
        List<Integer> idList = list.stream().map(AssetScrapValue::getId).collect(Collectors.toList());
        List<AssetFile> assetFileList = assetFileService.list(new LambdaQueryWrapper<AssetFile>().eq(AssetFile::getType,"AssetScrapValue2").in(AssetFile::getAssetId, idList));
        if (ObjectUtil.isNotEmpty(assetFileList)) {
            for (AssetScrapValue assetScrapValue : list) {
                List<FileVO> fileList = new ArrayList<>();
                for (AssetFile assetFile : assetFileList) {
                    if (assetScrapValue.getId().equals(assetFile.getAssetId())) {
                        FileVO fileVO = new FileVO();
                        fileVO.setName(assetFile.getName());
                        fileVO.setUrl(assetFile.getUrl());
                        fileVO.setStatus("done");
                        fileList.add(fileVO);
                    }
                }
                if (ObjectUtil.isNotEmpty(fileList)) {
                    assetScrapValue.setFileList(fileList);
                }
            }
        }
        batchCheck.setList(list);
        return batchCheck;
    }

    @PostMapping("btnHandle")
    public boolean btnHandle(@RequestBody AssetScrapValueAfter after) {
        return assetScrapValueService.btnHandle(after, "报废价值鉴定");
    }

    @PostMapping("dialogList")
    public List<AssetScrapValue> dialogList(@RequestBody Map<String, Object> paramMap) {
//        String current = (String) paramMap.get("current");
        return assetScrapValueService.list();
    }
}
