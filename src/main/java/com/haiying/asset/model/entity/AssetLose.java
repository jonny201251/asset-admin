package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 资产折旧
 * </p>
 *
 * @author 作者
 * @since 2022-06-11
 */
@Getter
@Setter
@TableName("asset_lose")
public class AssetLose implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private String categoryName;

    private String guid;

    private Integer startYear;

    private Integer startMonth;

    private Integer nowYear;

    private Integer nowMonth;

    private String deptName;

    private Double monthLose;
    //最后一次折旧
    private Double endLose;
}
