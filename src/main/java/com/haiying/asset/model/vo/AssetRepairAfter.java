package com.haiying.asset.model.vo;

import com.haiying.asset.model.entity.AssetRepair;
import com.haiying.asset.model.entity.BatchCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssetRepairAfter extends ProcessFormAfter{
    private BatchCheck formValue;
    private List<AssetRepair> list;
}
