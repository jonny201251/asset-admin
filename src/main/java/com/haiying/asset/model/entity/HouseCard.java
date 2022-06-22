package com.haiying.asset.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 房屋及构建筑物登记卡片
 * </p>
 *
 * @author 作者
 * @since 2022-05-18
 */
@Getter
@Setter
@TableName("house_card")
public class HouseCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资产编号
     */
    private String code;

    private Integer categoryId;

    private String categoryName;

    private String name;

    /**
     * 结构
     */
    private String structure;

    /**
     * 房屋面积
     */
    private Double area;

    /**
     * 取得日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate getDate;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 资产原值
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

    /**
     * 是否旧数据
     */
    private String haveOld;

    /**
     * 期初，累计折旧(2021-12-31)
     */
    private Double startLoseMoney;

    /**
     * 凭证号
     */
    private String financeCode;
    //使用状况，在用，停用，报废，调拨
    private String useStatus;
    //是否提满折旧
    private String haveFull;
    //月折旧
    private Double monthLose;
    //结束年月
    private Integer endYearMonth;
    //从2022年1月开始算起，折旧月数
    private Integer monthLoseCount;
    //最后一次折旧
    private Double endLose;

    //权属情况-房屋权属证明、权属人、发证日期、房屋所有权证号、权属面积、权属性质
    private String own1;
    private String own2;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate own3;
    private String own4;
    private Double own5;
    private String own6;
    //办公用房-在用、出租出借、闲置、待处置（待报废、毁损等）
    private Double office1;
    private Double office2;
    private Double office3;
    private Double office4;
    //业务用房-在用、出租出借、闲置、待处置（待报废、毁损等）
    private Double business1;
    private Double business2;
    private Double business3;
    private Double business4;
    //其他用房-在用、出租出借、闲置、待处置（待报废、毁损等）
    private Double other1;
    private Double other2;
    private Double other3;
    private Double other4;


    @TableField(exist = false)
    private List<AssetFinanceCode> list;
}
