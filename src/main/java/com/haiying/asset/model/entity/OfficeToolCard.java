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
 * 办公营具登记卡片
 * </p>
 *
 * @author 作者
 * @since 2022-03-26
 */
@Getter
@Setter
@TableName("office_tool_card")
public class OfficeToolCard implements Serializable {

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
     * 资金来源
     */
    private String moneyFrom;

    /**
     * 购置日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;
    //购置或安装日期，用于旧数据
    private String buy2Date;

    /**
     * 使用日期
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

    //净值
    private Double endMoney;

    /**
     * 使用年限
     */
    private Integer useYear;

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

    private String guid;

    //是否旧数据
    private String haveOld;
    //期初，累计折旧(2021-12-31)
    private Double startLoseMoney;
    //凭证号
    private String financeCode;
    //使用状况，在用，停用，报废，调拨
    private String useStatus;
    //是否提满折旧
    private String haveFull;
    //月折旧
    private Double monthLose;
}
