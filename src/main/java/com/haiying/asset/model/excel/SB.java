package com.haiying.asset.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SB {
    @ExcelProperty("资产编号")
    private String code;
    @ExcelProperty("资产名称")
    private String name;
    @ExcelProperty("资产类别")
    private String categoryName;
    @ExcelProperty("规格型号")
    private String modelSpec;
    @ExcelProperty("数量")
    private Integer number;
    @ExcelProperty("购置或安装日期")
    private String buy2Date;
    @ExcelProperty("原值")
    private Double startMoney;
    @ExcelProperty("存放地点")
    private String location;
    @ExcelProperty("使用部门")
    private String useDeptName;
    @ExcelProperty("取得方式")
    private String getStyle;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("累计折旧")
    private Double startLoseMoney;
    @ExcelProperty("凭证号")
    private String financeCode;
}
