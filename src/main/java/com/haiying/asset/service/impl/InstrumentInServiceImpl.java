package com.haiying.asset.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.mapper.InstrumentInMapper;
import com.haiying.asset.model.entity.*;
import com.haiying.asset.model.vo.FileVO;
import com.haiying.asset.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 设备入库 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Service
public class InstrumentInServiceImpl extends ServiceImpl<InstrumentInMapper, InstrumentIn> implements InstrumentInService {
    @Autowired
    HttpSession httpSession;
    @Autowired
    AssetBuyService assetBuyService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AssetCodeService assetCodeService;
    @Autowired
    AssetFileService assetFileService;

    private String getCode(AssetCode code) {
        int count = code.getCount() + 1;
        code.setCount(count);

        String countStr = count + "";
        if (countStr.length() == 1) {
            countStr = "00" + count;
        } else if (countStr.length() == 2) {
            countStr = "0" + count;
        }
        return code.getPrefix() + code.getYearmonth() + countStr;
    }

    @Override
    public boolean add(List<InstrumentIn> list) {
        LocalDateTime createDatetime = LocalDateTime.now();
        SysUser user = (SysUser) httpSession.getAttribute("user");

        List<Category> categoryList = categoryService.list();
        Map<Integer, Category> categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId, v -> v));

        String yearmonth = DateUtil.format(DateUtil.date(), "yyyyMM");
        List<AssetCode> codeList = assetCodeService.list(new LambdaQueryWrapper<AssetCode>().eq(AssetCode::getYearmonth, yearmonth));
        Map<String, AssetCode> codeMap = new HashMap<>();
        for (AssetCode assetCode : codeList) {
            codeMap.put(assetCode.getPrefix(), assetCode);
        }

        List<String> guidList = list.stream().map(InstrumentIn::getGuid).collect(Collectors.toList());
        List<AssetBuy> buyList = assetBuyService.list(new LambdaQueryWrapper<AssetBuy>().in(AssetBuy::getGuid, guidList));
        Map<String, AssetBuy> buyMap = buyList.stream().collect(Collectors.toMap(AssetBuy::getGuid, v -> v, (key1, key2) -> key2));
        //入库
        for (InstrumentIn instrumentIn : list) {
            String getStyle = instrumentIn.getGetStyle();
            if (getStyle.equals("新购")) {
                AssetBuy assetBuy = buyMap.get(instrumentIn.getGuid());
                assetBuy.setStatus("入库");
                instrumentIn.setCategoryId(assetBuy.getCategoryId());
                instrumentIn.setCategoryName(assetBuy.getCategoryName());
                //资产编号
                Category category = categoryMap.get(assetBuy.getCategoryId());
                AssetCode assetCode = codeMap.get(category.getCodePrefix());
                String code = getCode(assetCode);
                instrumentIn.setCode(code);
                instrumentIn.setName(assetBuy.getName());
                instrumentIn.setModelSpec(assetBuy.getModelSpec());
                instrumentIn.setFactory(assetBuy.getFactory());
                instrumentIn.setBrand(assetBuy.getBrand());
                instrumentIn.setMoneyFrom(assetBuy.getMoneyFrom());
                instrumentIn.setBuyDate(LocalDate.now());
                instrumentIn.setUnit(assetBuy.getUnit());
//                instrumentIn.setNumber(assetBuy.getNumber());
                instrumentIn.setPrice(assetBuy.getPrice());
                //useYear
                instrumentIn.setCreateDatetime(createDatetime);
                instrumentIn.setDisplayName(user.getDisplayName());
                instrumentIn.setLoginName(user.getLoginName());
                instrumentIn.setDeptId(user.getDeptId());
                instrumentIn.setDeptName(user.getDeptName());
                //设备原值
                Double startMoney = instrumentIn.getNumber() * instrumentIn.getPrice() + instrumentIn.getMoveMoney() + instrumentIn.getInstallMoney();
                instrumentIn.setStartMoney(startMoney);
            } else {
                Category category = categoryMap.get(instrumentIn.getCategoryId());
                instrumentIn.setCategoryName(category.getName());
                //资产编号
                AssetCode assetCode = codeMap.get(category.getCodePrefix());
                String code = getCode(assetCode);
                instrumentIn.setCode(code);

                instrumentIn.setBuyDate(LocalDate.now());
                instrumentIn.setCreateDatetime(createDatetime);
                instrumentIn.setDisplayName(user.getDisplayName());
                instrumentIn.setLoginName(user.getLoginName());
                instrumentIn.setDeptId(user.getDeptId());
                instrumentIn.setDeptName(user.getDeptName());
                //设备原值
                Double startMoney = instrumentIn.getNumber() * instrumentIn.getPrice() + instrumentIn.getMoveMoney() + instrumentIn.getInstallMoney();
                instrumentIn.setStartMoney(startMoney);

                instrumentIn.setGuid(IdUtil.fastSimpleUUID());
            }
        }
        this.saveBatch(list);
        //资产编号
        assetCodeService.updateBatchById(codeList);
        //附件
        List<AssetFile> assetFileList = new ArrayList<>();
        for (InstrumentIn item : list) {
            List<FileVO> fileList = item.getFileList();
            if (ObjectUtil.isNotEmpty(fileList)) {
                for (FileVO fileVO : fileList) {
                    AssetFile assetFile = new AssetFile();
                    assetFile.setType("InstrumentIn");
                    assetFile.setAssetId(item.getId());
                    assetFile.setName(fileVO.getName());
                    assetFile.setUrl(fileVO.getUrl());
                    assetFileList.add(assetFile);
                }
            }
        }
        if (ObjectUtil.isNotEmpty(assetFileList)) {
            assetFileService.saveBatch(assetFileList);
        }
        //
        if (ObjectUtil.isNotEmpty(buyList)) {
            assetBuyService.updateBatchById(buyList);
        }
        return true;
    }

    @Override
    public boolean edit(InstrumentIn instrumentIn) {
        this.updateById(instrumentIn);
        //附件
        assetFileService.remove(new LambdaQueryWrapper<AssetFile>().eq(AssetFile::getType, "InstrumentIn").eq(AssetFile::getAssetId, instrumentIn.getId()));
        List<AssetFile> assetFileList = new ArrayList<>();
        List<FileVO> fileList = instrumentIn.getFileList();
        if (ObjectUtil.isNotEmpty(fileList)) {
            for (FileVO fileVO : fileList) {
                AssetFile assetFile = new AssetFile();
                assetFile.setType("InstrumentIn");
                assetFile.setAssetId(instrumentIn.getId());
                assetFile.setName(fileVO.getName());
                assetFile.setUrl(fileVO.getUrl());
                assetFileList.add(assetFile);
            }
        }
        if (ObjectUtil.isNotEmpty(assetFileList)) {
            assetFileService.saveBatch(assetFileList);
        }
        return true;
    }
}
