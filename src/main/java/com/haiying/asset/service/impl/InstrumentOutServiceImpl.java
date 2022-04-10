package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.mapper.InstrumentOutMapper;
import com.haiying.asset.model.entity.InstrumentIn;
import com.haiying.asset.model.entity.InstrumentOut;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.service.InstrumentInService;
import com.haiying.asset.service.InstrumentOutService;
import com.haiying.asset.service.SysDeptService;
import com.haiying.asset.service.SysUserService;
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
 * 设备出库 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Service
public class InstrumentOutServiceImpl extends ServiceImpl<InstrumentOutMapper, InstrumentOut> implements InstrumentOutService {
    @Autowired
    HttpSession httpSession;
    @Autowired
    InstrumentInService instrumentInService;
    @Autowired
    SysDeptService deptService;
    @Autowired
    SysUserService userService;

    @Override
    public boolean add(List<InstrumentOut> list) {
        LocalDateTime createDatetime = LocalDateTime.now();
        SysUser user = (SysUser) httpSession.getAttribute("user");
        //
        List<String> loginNameList = list.stream().map(InstrumentOut::getUseLoginName).collect(Collectors.toList());
        List<SysUser> userList = userService.list(new LambdaQueryWrapper<SysUser>().in(SysUser::getLoginName, loginNameList));
        Map<String, SysUser> userMap = userList.stream().collect(Collectors.toMap(SysUser::getLoginName, v -> v));

        List<String> guidList = list.stream().map(InstrumentOut::getGuid).collect(Collectors.toList());
        List<InstrumentIn> instrumentInList = instrumentInService.list(new LambdaQueryWrapper<InstrumentIn>().in(InstrumentIn::getGuid, guidList));
        Map<String, InstrumentIn> instrumentInMap = instrumentInList.stream().collect(Collectors.toMap(InstrumentIn::getGuid, v -> v, (key1, key2) -> key2));
        //出库
        for (InstrumentOut instrumentOut : list) {
            InstrumentIn instrumentIn = instrumentInMap.get(instrumentOut.getGuid());
            instrumentIn.setStatus("出库");
            instrumentOut.setCode(instrumentIn.getCode());
            instrumentOut.setCategoryId(instrumentIn.getCategoryId());
            instrumentOut.setCategoryName(instrumentIn.getCategoryName());
            instrumentOut.setBuyDate(instrumentIn.getBuyDate());
            instrumentOut.setStartMoney(instrumentIn.getStartMoney());
            instrumentOut.setUseDate(LocalDate.now());
            //
            SysUser use = userMap.get(instrumentOut.getUseLoginName());
            if (use != null) {
                instrumentOut.setUseDeptId(use.getDeptId());
                instrumentOut.setUseDeptName(use.getDeptName());
                instrumentOut.setUseLoginName(use.getLoginName());
                instrumentOut.setDisplayName(use.getDisplayName());
            }

            instrumentOut.setCreateDatetime(createDatetime);
            instrumentOut.setDisplayName(user.getDisplayName());
            instrumentOut.setLoginName(user.getLoginName());
            instrumentOut.setDeptId(user.getDeptId());
            instrumentOut.setDeptName(user.getDeptName());
        }

        this.saveBatch(list);
        //
        instrumentInService.updateBatchById(instrumentInList);

        return true;
    }

    @Override
    public boolean edit(InstrumentOut instrumentOut) {
        this.updateById(instrumentOut);
        return true;
    }
}
