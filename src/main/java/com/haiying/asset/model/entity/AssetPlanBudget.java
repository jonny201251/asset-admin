package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 计划预算
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@Getter
@Setter
@TableName("asset_plan_budget")
public class AssetPlanBudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer year;

    private Integer deptId;

    private String deptName;

    private String assetCategory;

    private String lifeCycle;

    private Double planMoney;

    private Double giveMoney;

    private String status;


}
