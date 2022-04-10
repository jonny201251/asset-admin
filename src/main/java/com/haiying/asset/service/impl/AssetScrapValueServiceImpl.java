package com.haiying.asset.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.bean.ButtonHandleBean;
import com.haiying.asset.mapper.AssetScrapValueMapper;
import com.haiying.asset.model.entity.AssetFile;
import com.haiying.asset.model.entity.AssetScrapValue;
import com.haiying.asset.model.entity.BatchCheck;
import com.haiying.asset.model.vo.AssetScrapValueAfter;
import com.haiying.asset.model.vo.FileVO;
import com.haiying.asset.service.AssetFileService;
import com.haiying.asset.service.AssetScrapValueService;
import com.haiying.asset.service.BatchCheckService;
import com.haiying.asset.service.ProcessInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废价值鉴定 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@Service
public class AssetScrapValueServiceImpl extends ServiceImpl<AssetScrapValueMapper, AssetScrapValue> implements AssetScrapValueService {

    @Autowired
    ButtonHandleBean buttonHandleBean;
    @Autowired
    BatchCheckService batchCheckService;
    @Autowired
    ProcessInstService processInstService;
    @Autowired
    AssetFileService assetFileService;

    private void add(BatchCheck formValue, List<AssetScrapValue> list, String menuName) {
        formValue.setMenuName(menuName);
        batchCheckService.save(formValue);
        for (AssetScrapValue assetScrapValue : list) {
            assetScrapValue.setBatchId(formValue.getId());
//            assetScrapValue.setStatus("已鉴定项目");
            //
        }
        this.saveBatch(list);
        //
        List<AssetFile> list2 = new ArrayList<>();
        for (AssetScrapValue assetScrapValue : list) {
            List<FileVO> fileList = assetScrapValue.getFileList();
            if (ObjectUtil.isNotEmpty(fileList)) {
                for (FileVO fileVO : fileList) {
                    AssetFile assetFile = new AssetFile();
                    assetFile.setType("AssetScrapValue");
                    assetFile.setAssetId(assetScrapValue.getId());
                    assetFile.setName(fileVO.getName());
                    assetFile.setUrl(fileVO.getUrl());
                    list2.add(assetFile);
                }
            }
        }
        if (ObjectUtil.isNotEmpty(list2)) {
            assetFileService.saveBatch(list2);
        }
    }

    private void edit(BatchCheck formValue, List<AssetScrapValue> list, String menuName) {
        Integer batchId = formValue.getId();
        this.remove(new LambdaQueryWrapper<AssetScrapValue>().eq(AssetScrapValue::getBatchId, batchId));
        List<Integer> idList = list.stream().map(AssetScrapValue::getId).collect(Collectors.toList());
        assetFileService.remove(new LambdaQueryWrapper<AssetFile>().eq(AssetFile::getType,"AssetScrapValue").in(AssetFile::getAssetId, idList));
        for (AssetScrapValue assetScrapValue : list) {
            assetScrapValue.setBatchId(formValue.getId());
//            assetScrapValue.setStatus("已鉴定项目");
        }
        this.saveBatch(list);
        //
        List<AssetFile> list2 = new ArrayList<>();
        for (AssetScrapValue assetScrapValue : list) {
            List<FileVO> fileList = assetScrapValue.getFileList();
            if (ObjectUtil.isNotEmpty(fileList)) {
                for (FileVO fileVO : fileList) {
                    AssetFile assetFile = new AssetFile();
                    assetFile.setType("AssetScrapValue");
                    assetFile.setAssetId(assetScrapValue.getId());
                    assetFile.setName(fileVO.getName());
                    assetFile.setUrl(fileVO.getUrl());
                    list2.add(assetFile);
                }
            }
        }
        if (ObjectUtil.isNotEmpty(list2)) {
            assetFileService.saveBatch(list2);
        }
    }

    private void delete(Integer batchId) {
        batchCheckService.removeById(batchId);
        this.remove(new LambdaQueryWrapper<AssetScrapValue>().eq(AssetScrapValue::getBatchId, batchId));
    }


    @Override
    public synchronized boolean btnHandle(AssetScrapValueAfter after, String menuName) {
        BatchCheck formValue = after.getFormValue();
        List<AssetScrapValue> list = after.getList();
        String type = after.getType();
        String buttonName = after.getButtonName();
        String path = after.getPath();
        if (type.equals("add")) {
            if (buttonName.equals("草稿")) {
                add(formValue, list, menuName);
            } else {
                add(formValue, list, menuName);
                Integer processInstId = buttonHandleBean.addEdit(path, formValue, buttonName, formValue.getId(), formValue.getCheckName());
                //
                formValue.setProcessInstId(processInstId);
                batchCheckService.updateById(formValue);
            }
        } else if (type.equals("edit")) {
            if (buttonName.equals("草稿")) {
                edit(formValue, list, menuName);
            } else {
                edit(formValue, list, menuName);
                Integer processInstId = buttonHandleBean.addEdit(path, formValue, buttonName, formValue.getId(), formValue.getCheckName());
                //
                formValue.setProcessInstId(processInstId);
                batchCheckService.updateById(formValue);
            }
        } else if (type.equals("check") || type.equals("reject")) {
            String haveEditForm = after.getHaveEditForm();
            if (haveEditForm.equals("是")) {
                edit(formValue, list, menuName);
            }
            boolean flag = buttonHandleBean.checkReject(formValue.getProcessInstId(), formValue, buttonName, after.getComment());
            if (flag) {

            }
        } else if (type.equals("recall")) {
            buttonHandleBean.recall(formValue.getProcessInstId(), buttonName);
        } else if (type.equals("delete")) {
            delete(formValue.getId());
            buttonHandleBean.delete(formValue.getProcessInstId());
        }
        return true;
    }
}
