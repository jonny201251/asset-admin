package com.haiying.asset.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.AssetFile;
import com.haiying.asset.model.entity.InstrumentIn;
import com.haiying.asset.model.vo.FileVO;
import com.haiying.asset.model.vo.InstrumentInVO;
import com.haiying.asset.service.AssetFileService;
import com.haiying.asset.service.InstrumentInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备入库 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@RestController
@RequestMapping("/instrumentIn")
@Wrapper
public class InstrumentInController {
    @Autowired
    InstrumentInService instrumentInService;
    @Autowired
    AssetFileService assetFileService;

    @PostMapping("list")
    public IPage<InstrumentIn> list(@RequestBody Map<String, Object> paramMap) {
        LambdaQueryWrapper<InstrumentIn> wrapper = new LambdaQueryWrapper<>();
        Integer current = (Integer) paramMap.get("current");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Object type = paramMap.get("type");
        Object name = paramMap.get("name");
        if (ObjectUtil.isNotEmpty(type)) {
            wrapper.like(InstrumentIn::getDeptName, type);
        }
        if (ObjectUtil.isNotEmpty(name)) {
            wrapper.like(InstrumentIn::getName, name);
        }
        return instrumentInService.page(new Page<>(current, pageSize), wrapper);
    }

    @PostMapping("add")
    public boolean add(@RequestBody InstrumentInVO instrumentInVO) {
        return instrumentInService.add(instrumentInVO.getList());
    }

    @GetMapping("get")
    public InstrumentIn get(Integer id) {
        InstrumentIn instrumentIn = instrumentInService.getById(id);
        //文件
        List<AssetFile> assetFileList = assetFileService.list(new LambdaQueryWrapper<AssetFile>().eq(AssetFile::getType, "InstrumentIn").eq(AssetFile::getAssetId, id));
        if (ObjectUtil.isNotEmpty(assetFileList)) {
            List<FileVO> fileList = new ArrayList<>();
            for (AssetFile assetFile : assetFileList) {
                if (instrumentIn.getId().equals(assetFile.getAssetId())) {
                    FileVO fileVO = new FileVO();
                    fileVO.setName(assetFile.getName());
                    fileVO.setUrl(assetFile.getUrl());
                    fileVO.setStatus("done");
                    fileList.add(fileVO);
                }
            }
            if (ObjectUtil.isNotEmpty(fileList)) {
                instrumentIn.setFileList(fileList);
            }
        }
        return instrumentIn;
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody InstrumentIn instrumentIn) {
        return instrumentInService.edit(instrumentIn);
    }

}
