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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 办公营具和设备仪器仪表-报废申请
 * </p>
 *
 * @author 作者
 * @since 2022-04-06
 */
@Getter
@Setter
@TableName("asset_scrap")
public class AssetScrap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String planType;

    /**
     *  资产编号
     */
    private String code;

    private Integer categoryId;

    /**
     * 资产类别
     */
    private String categoryName;

    private String name;

    /**
     * 型号规格
     */
    private String modelSpec;

    /**
     * 生产厂商
     */
    private String factory;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 购置日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    /**
     * 启用日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate useDate;

    /**
     * 资产原值=入库中的单价*数量+运输费+安装费
     */
    private Double startMoney;

    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private String displayName;

    private String loginName;

    private Integer deptId;

    private String deptName;

    /**
     * 车牌号
     */
    private String carCode;

    private String guid;

    /**
     * 报废日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scrapDate;

    private Integer batchId;

    @TableField(exist = false)
    private List<FileVO> fileList;

    private String status;
}
