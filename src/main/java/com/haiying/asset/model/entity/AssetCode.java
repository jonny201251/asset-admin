package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 资产编号
 * </p>
 *
 * @author 作者
 * @since 2022-03-28
 */
@Getter
@Setter
@TableName("asset_code")
public class AssetCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 年月
     */
    private Integer yearmonth;

    /**
     * 车辆-KCL,设备-KSB,其他设备-KQTSB,仪器仪表-KYQ
     */
    private String prefix;

    private Integer count;


}
