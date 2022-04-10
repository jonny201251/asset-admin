package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备登记卡片
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Getter
@Setter
@TableName("instrument_card")
public class InstrumentCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *  资产编号
     */
    private String code;

    private Integer categoryId;
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
     * 资金来源
     */
    private String moneyFrom;

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
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 资产原值=入库中的单价*数量+运输费+安装费
     */
    private Double startMoney;

    /**
     * 使用年限
     */
    private Integer useYear;

    /**
     * 已使用年限
     */
    private Integer yesUseYear;

    /**
     * 未使用年限
     */
    private Integer noUseYear;

    /**
     * 年折旧率
     */
    private String yearRate;

    private Double yearLoseMoney;

    private String monthRate;

    private Double monthLoseMoney;

    private Double totalLoseMoney;

    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDatetime;

    private String displayName;

    private String loginName;

    private Integer deptId;

    private String deptName;

    /**
     * 取得方式，购置，无偿转入
     */
    private String getStyle;

    private Integer useDeptId;

    private String useDeptName;

    private String useDisplayName;

    private String useLoginName;

    /**
     * 存放地点
     */
    private String location;

    /**
     * 车牌号
     */
    private String carCode;

    private String guid;
}
