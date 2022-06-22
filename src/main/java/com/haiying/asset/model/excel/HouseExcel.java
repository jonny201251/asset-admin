package com.haiying.asset.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class HouseExcel {
    @ExcelProperty("资产编号")
    @ColumnWidth(15)
    private String code;
    @ExcelProperty("资产类别")
    @ColumnWidth(15)
    private String categoryName;
    @ExcelProperty("资产名称")
    @ColumnWidth(25)
    private String name;
    @ExcelProperty("结构")
    private String structure;
    @ExcelProperty("房屋面积")
    private Double area;
    @ExcelProperty("取得日期")
    @ColumnWidth(15)
    private String getDatee;
    @ExcelProperty("数量")
    private Integer number;
    @ExcelProperty("原值")
    private Double startMoney;
    @ExcelProperty("净值")
    private Double endMoney;
    @ExcelProperty("使用年限")
    private Integer useYear;
    @ExcelProperty("取得方式")
    private String getStyle;
    @ExcelProperty("使用部门")
    @ColumnWidth(25)
    private String useDeptName;
    @ExcelProperty("使用人")
    private String useDisplayName;
    @ExcelProperty("存放地点")
    @ColumnWidth(25)
    private String location;
    @ExcelProperty("月折旧")
    private Double monthLose;
    @ExcelProperty("累计折旧")
    private Double totalLoseMoney;
    @ExcelProperty("新旧数据")
    private String haveOld;
    @ExcelProperty("使用状况")
    private String useStatus;

}
