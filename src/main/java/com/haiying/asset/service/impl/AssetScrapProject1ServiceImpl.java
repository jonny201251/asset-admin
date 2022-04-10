package com.haiying.asset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiying.asset.mapper.AssetScrapProject1Mapper;
import com.haiying.asset.model.entity.AssetScrapProject1;
import com.haiying.asset.model.entity.AssetScrapProject2;
import com.haiying.asset.service.AssetScrapProject1Service;
import com.haiying.asset.service.AssetScrapProject2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废项目1 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@Service
public class AssetScrapProject1ServiceImpl extends ServiceImpl<AssetScrapProject1Mapper, AssetScrapProject1> implements AssetScrapProject1Service {
    @Autowired
    AssetScrapProject2Service assetScrapProject2Service;

    @Override
    public boolean add(AssetScrapProject1 assetScrapProject1) {
        this.save(assetScrapProject1);
        List<AssetScrapProject2> list = assetScrapProject1.getList();
        list.forEach(item -> item.setProject1Id(assetScrapProject1.getId()));
        assetScrapProject2Service.saveBatch(list);
        return true;
    }

    @Override
    public boolean edit(AssetScrapProject1 assetScrapProject1) {
        this.updateById(assetScrapProject1);
        assetScrapProject2Service.remove(new LambdaQueryWrapper<AssetScrapProject2>().eq(AssetScrapProject2::getProject1Id, assetScrapProject1.getId()));
        List<AssetScrapProject2> list = assetScrapProject1.getList();
        list.forEach(item -> {
            item.setId(null);
            item.setProject1Id(assetScrapProject1.getId());
        });
        assetScrapProject2Service.saveBatch(list);
        return true;
    }
}
