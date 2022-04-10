package com.haiying.asset.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.mapper.OfficeToolCardMapper;
import com.haiying.asset.model.entity.*;
import com.haiying.asset.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 办公营具登记卡片 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Service
public class OfficeToolCardServiceImpl extends ServiceImpl<OfficeToolCardMapper, OfficeToolCard> implements OfficeToolCardService {
    @Autowired
    HttpSession httpSession;
    @Autowired
    AssetCodeService assetCodeService;
    @Autowired
    AssetBuyService assetBuyService;
    @Autowired
    SysDeptService sysDeptService;
    @Autowired
    SysUserService userService;

    private String getCode(AssetCode code, SysDept dept) {
        int count = code.getCount() + 1;
        code.setCount(count);

        String countStr = count + "";
        if (countStr.length() == 1) {
            countStr = "00" + count;
        } else if (countStr.length() == 2) {
            countStr = "0" + count;
        }
        return code.getPrefix() + dept.getPrefix() + code.getYearmonth() + countStr;
    }

    @Override
    public boolean add(List<OfficeToolCard> list) {
        LocalDateTime createDatetime = LocalDateTime.now();
        SysUser user = (SysUser) httpSession.getAttribute("user");
        SysDept dept = sysDeptService.getById(user.getDeptId());

        //
        List<String> loginNameList = list.stream().map(OfficeToolCard::getUseLoginName).collect(Collectors.toList());
        List<SysUser> userList = userService.list(new LambdaQueryWrapper<SysUser>().in(SysUser::getLoginName, loginNameList));
        Map<String, SysUser> userMap = userList.stream().collect(Collectors.toMap(SysUser::getLoginName, v -> v));

        String yearmonth = DateUtil.format(DateUtil.date(), "yyyyMM");
        AssetCode assetCode = assetCodeService.getOne(new LambdaQueryWrapper<AssetCode>().eq(AssetCode::getPrefix, "YJ").eq(AssetCode::getYearmonth, yearmonth));
        //
        List<String> guidList = list.stream().map(OfficeToolCard::getGuid).collect(Collectors.toList());
        List<AssetBuy> buyList = assetBuyService.list(new LambdaQueryWrapper<AssetBuy>().in(AssetBuy::getGuid, guidList));
        Map<String, AssetBuy> buyMap = buyList.stream().collect(Collectors.toMap(AssetBuy::getGuid, v -> v, (key1, key2) -> key2));
        for (OfficeToolCard officeToolCard : list) {
            AssetBuy assetBuy = buyMap.get(officeToolCard.getGuid());
            assetBuy.setStatus("登记卡片");
            //资产编号
            String code = getCode(assetCode,dept);
            officeToolCard.setCode(code);
            //
            officeToolCard.setName(assetBuy.getName());
            officeToolCard.setModelSpec(assetBuy.getModelSpec());
            officeToolCard.setMoneyFrom(assetBuy.getMoneyFrom());
            officeToolCard.setUseDate(LocalDate.now());
            officeToolCard.setUnit(assetBuy.getUnit());
            officeToolCard.setNumber(assetBuy.getNumber());
            officeToolCard.setStartMoney(assetBuy.getPrice());
            //
            SysUser use = userMap.get(officeToolCard.getUseLoginName());
            if (use != null) {
                officeToolCard.setUseDeptId(use.getDeptId());
                officeToolCard.setUseDeptName(use.getDeptName());
                officeToolCard.setUseLoginName(use.getLoginName());
                officeToolCard.setDisplayName(use.getDisplayName());
            }

            officeToolCard.setCreateDatetime(createDatetime);
            officeToolCard.setDisplayName(user.getDisplayName());
            officeToolCard.setLoginName(user.getLoginName());
            officeToolCard.setDeptId(user.getDeptId());
            officeToolCard.setDeptName(user.getDeptName());

        }
        this.saveBatch(list);
        //资产编号
        assetCodeService.updateById(assetCode);
        //
        if (ObjectUtil.isNotEmpty(buyList)) {
            assetBuyService.updateBatchById(buyList);
        }
        return true;
    }

    @Override
    public boolean edit(OfficeToolCard officeToolCard) {
        return true;
    }
}
