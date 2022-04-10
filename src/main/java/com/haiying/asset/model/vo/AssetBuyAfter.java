package com.haiying.asset.model.vo;

import com.haiying.asset.model.entity.AssetBuy;
import com.haiying.asset.model.entity.BatchCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssetBuyAfter extends ProcessFormAfter{
    private BatchCheck formValue;
    private List<AssetBuy> list;
}
