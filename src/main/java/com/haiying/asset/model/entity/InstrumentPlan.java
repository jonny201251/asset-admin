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
 * 设备仪器仪表计划
 * </p>
 *
 * @author 作者
 * @since 2022-03-19
 */
@Getter
@Setter
@TableName("instrument_plan")
public class InstrumentPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 新购，维修。。。
     */
    private String lifeCycle;

    /**
     * 名称
     */
    private String name;

    private Integer categoryId;
    private String categoryName;

    /**
     * 型号规格
     */
    private String modelSpec;

    /**
     * 技术要求
     */
    private String techReq;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 价格
     */
    private Double price;

    /**
     * 生产厂商
     */
    private String factory;

    private String displayName;

    private String loginName;

    private Integer startDeptId;

    private String startDeptName;

    private Integer endDeptId;

    private Integer endDeptName;

    private String level;

    private String remark;

    private Integer batchId;

    private String guid;

    private Integer useYear;

    private Integer year;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;
}
