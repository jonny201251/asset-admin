package com.haiying.asset.model.vo;

import com.haiying.asset.model.entity.AssetTurn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssetTurnAfter extends ProcessFormAfter{
    private AssetTurn formValue;
}
