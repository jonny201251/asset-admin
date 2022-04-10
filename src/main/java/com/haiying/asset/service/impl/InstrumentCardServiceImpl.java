package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.mapper.InstrumentCardMapper;
import com.haiying.asset.model.entity.InstrumentCard;
import com.haiying.asset.model.entity.InstrumentIn;
import com.haiying.asset.model.entity.InstrumentOut;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 设备登记卡片 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Service
public class InstrumentCardServiceImpl extends ServiceImpl<InstrumentCardMapper, InstrumentCard> implements InstrumentCardService {
    @Autowired
    HttpSession httpSession;
    @Autowired
    InstrumentInService instrumentInService;
    @Autowired
    InstrumentOutService instrumentOutService;
    @Autowired
    SysDeptService deptService;
    @Autowired
    SysUserService userService;

    @Override
    public boolean add(List<InstrumentCard> list) {
        LocalDateTime createDatetime = LocalDateTime.now();
        SysUser user = (SysUser) httpSession.getAttribute("user");
        //
        List<String> guidList = list.stream().map(InstrumentCard::getGuid).collect(Collectors.toList());
        List<InstrumentIn> instrumentInList = instrumentInService.list(new LambdaQueryWrapper<InstrumentIn>().in(InstrumentIn::getGuid, guidList));
        Map<String, InstrumentIn> instrumentInMap = instrumentInList.stream().collect(Collectors.toMap(InstrumentIn::getGuid, v -> v, (key1, key2) -> key2));
        List<InstrumentOut> instrumentOutList = instrumentOutService.list(new LambdaQueryWrapper<InstrumentOut>().in(InstrumentOut::getGuid, guidList));
        Map<String, InstrumentOut> instrumentOutMap = instrumentOutList.stream().collect(Collectors.toMap(InstrumentOut::getGuid, v -> v, (key1, key2) -> key2));
        //卡片
        for (InstrumentCard instrumentCard : list) {
            InstrumentIn instrumentIn = instrumentInMap.get(instrumentCard.getGuid());
            InstrumentOut instrumentOut = instrumentOutMap.get(instrumentCard.getGuid());
            instrumentOut.setStatus("登记卡片");

            instrumentCard.setCode(instrumentIn.getCode());
            instrumentCard.setCategoryId(instrumentIn.getCategoryId());
            instrumentCard.setCategoryName(instrumentIn.getCategoryName());
            instrumentCard.setModelSpec(instrumentIn.getModelSpec());
            instrumentCard.setFactory(instrumentIn.getFactory());
            instrumentCard.setBrand(instrumentIn.getBrand());
            instrumentCard.setMoneyFrom(instrumentIn.getMoneyFrom());
            instrumentCard.setBuyDate(instrumentIn.getBuyDate());
            instrumentCard.setUseDate(instrumentOut.getUseDate());
            instrumentCard.setUnit(instrumentIn.getUnit());
            instrumentCard.setNumber(instrumentIn.getNumber());
            instrumentCard.setStartMoney(instrumentIn.getStartMoney());
            instrumentCard.setUseYear(instrumentIn.getUseYear());
            instrumentCard.setGetStyle(instrumentIn.getGetStyle());
            instrumentCard.setUseDeptId(instrumentOut.getDeptId());
            instrumentCard.setUseDeptName(instrumentOut.getDeptName());
            instrumentCard.setUseLoginName(instrumentOut.getLoginName());
            instrumentCard.setDisplayName(instrumentOut.getDisplayName());
            instrumentCard.setLocation(instrumentOut.getLocation());

            instrumentCard.setCreateDatetime(createDatetime);
            instrumentCard.setDisplayName(user.getDisplayName());
            instrumentCard.setLoginName(user.getLoginName());
            instrumentCard.setDeptId(user.getDeptId());
            instrumentCard.setDeptName(user.getDeptName());
        }

        this.saveBatch(list);
        //
        instrumentOutService.updateBatchById(instrumentOutList);

        return true;
    }

    @Override
    public boolean edit(InstrumentCard instrumentCard) {
        this.updateById(instrumentCard);
        return true;
    }
}
