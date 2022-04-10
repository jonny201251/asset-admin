package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 生命周期
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@Getter
@Setter
@TableName("asset_life_cycle")
public class AssetLifeCycle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer batchId;

    private String lifeCycle;

    private Integer assetId;

    private Integer assetNo;

    private String assetName;

    private String assetCategory;

    /**
     * 计划内，计划外
     */
    private String planType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDatetime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDatetime;

    private String displayName;

    private String loginName;

    private Integer deptId;

    private String deptName;


}
