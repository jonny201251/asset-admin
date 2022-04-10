package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.mapper.AssetAcceptMapper;
import com.haiying.asset.model.entity.AssetAccept;
import com.haiying.asset.model.entity.AssetRepair;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.service.AssetAcceptService;
import com.haiying.asset.service.AssetRepairService;
import com.haiying.asset.service.SysDeptService;
import com.haiying.asset.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 维保检验收 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-31
 */
@Service
public class AssetAcceptServiceImpl extends ServiceImpl<AssetAcceptMapper, AssetAccept> implements AssetAcceptService {
    @Autowired
    HttpSession httpSession;
    @Autowired
    AssetRepairService assetRepairService;
    @Autowired
    SysDeptService deptService;
    @Autowired
    SysUserService userService;

    @Override
    public boolean add(List<AssetAccept> list) {
        LocalDateTime createDatetime = LocalDateTime.now();
        SysUser user = (SysUser) httpSession.getAttribute("user");
        //
        List<String> guidList = list.stream().map(AssetAccept::getGuid).collect(Collectors.toList());
        List<AssetRepair> assetRepairList = assetRepairService.list(new LambdaQueryWrapper<AssetRepair>().in(AssetRepair::getGuid, guidList));
        Map<String, AssetRepair> assetRepairMap = assetRepairList.stream().collect(Collectors.toMap(AssetRepair::getGuid, v -> v, (key1, key2) -> key2));
        //卡片
        for (AssetAccept assetAccept : list) {
            AssetRepair assetRepair = assetRepairMap.get(assetAccept.getGuid());

            assetAccept.setCode(assetRepair.getCode());
            assetAccept.setCategoryId(assetRepair.getCategoryId());
            assetAccept.setCategoryName(assetRepair.getCategoryName());
            assetAccept.setModelSpec(assetRepair.getModelSpec());
            assetAccept.setBuyDate(assetRepair.getBuyDate());
            assetAccept.setUseDate(assetRepair.getUseDate());
            assetAccept.setUseDeptId(assetRepair.getDeptId());
            assetAccept.setUseDeptName(assetRepair.getDeptName());
            assetAccept.setDisplayName(assetRepair.getDisplayName());
            assetAccept.setLocation(assetRepair.getLocation());

            assetAccept.setCreateDatetime(createDatetime);
            assetAccept.setDisplayName(user.getDisplayName());
            assetAccept.setLoginName(user.getLoginName());
            assetAccept.setDeptId(user.getDeptId());
            assetAccept.setDeptName(user.getDeptName());
        }

        return this.saveBatch(list);
    }

    @Override
    public boolean edit(AssetAccept assetAccept) {
        this.updateById(assetAccept);
        return true;
    }
}
