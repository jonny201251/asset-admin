package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haiying.asset.model.vo.FileVO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 办公营具和设备仪器仪表-购置
 * </p>
 *
 * @author 作者
 * @since 2022-03-23
 */
@Getter
@Setter
@TableName("asset_buy")
public class AssetBuy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

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
    //品牌
    private String brand;

    private String displayName;

    private String loginName;

    private Integer startDeptId;

    private String startDeptName;

    private Integer endDeptId;

    private String endDeptName;

    private String level;

    private String remark;

    private Integer batchId;

    /**
     * 资金来源
     */
    private String moneyFrom;

    private Integer categoryId;
    private String categoryName;

    /**
     * 办公营具，设备仪器仪表
     */
    private String assetType;

    /**
     * 计划内，计划外
     */
    private String planType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    @TableField(exist = false)
    private List<FileVO> fileList;
    //入库，入库
    private String status;
    private String guid;
    private Integer useYear;
}
