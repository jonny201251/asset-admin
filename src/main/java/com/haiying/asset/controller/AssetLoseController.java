package com.haiying.asset.controller;


import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haiying.asset.model.entity.HouseCard;
import com.haiying.asset.model.excel.HouseExcel;
import com.haiying.asset.service.AssetLoseService;
import com.haiying.asset.service.HouseCardService;
import com.haiying.asset.service.InstrumentCardService;
import com.haiying.asset.service.OfficeToolCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资产折旧 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-06-11
 */
@Controller
@RequestMapping("/assetLose")
public class AssetLoseController {
    @Autowired
    InstrumentCardService instrumentCardService;
    @Autowired
    OfficeToolCardService officeToolCardService;
    @Autowired
    HouseCardService houseCardService;
    @Autowired
    AssetLoseService assetLoseService;

    //提满折旧
    @ResponseBody
    @GetMapping("fullLose")
    public void fullLose(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("截止" + DateUtil.today() + "-提满折旧", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        //
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).useDefaultStyle(false).excelType(ExcelTypeEnum.XLS).build();
        //
        List<HouseExcel> data0List = new ArrayList<>();
        List<HouseCard> houseList = houseCardService.list(new LambdaQueryWrapper<HouseCard>().eq(HouseCard::getHaveFull, "是"));
        for (HouseCard houseCard : houseList) {
            HouseExcel houseExcel = new HouseExcel();
            BeanUtils.copyProperties(houseCard, houseExcel);
            houseExcel.setGetDatee(houseCard.getGetDate().toString());
            data0List.add(houseExcel);
        }

        WriteSheet sheet0 = EasyExcel.writerSheet(0, "房屋及构建筑物").head(HouseExcel.class).build();
//        //
        excelWriter.write(data0List, sheet0);
        //
        excelWriter.finish();
    }

    //计提折旧
    @GetMapping("haveLose")
    public void haveLose(String type, String yearMonth, HttpServletResponse response) throws IOException {
        String[] typeArr = type.split(",");
        String[] yearMonthArr = yearMonth.split(",");

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(yearMonthArr[0] + "-" + yearMonthArr[1] + "-计提折旧", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        //
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).useDefaultStyle(false).excelType(ExcelTypeEnum.XLS).build();

        for (int i = 0; i < typeArr.length; i++) {
            if (typeArr[i].equals("办公营具")) {

            } else if (typeArr[i].equals("仪器仪表")) {

            } else if (typeArr[i].equals("房屋及构建筑物")) {

            } else if (typeArr[i].equals("办公设备+信息化设备+其他设备")) {

            } else if (typeArr[i].equals("生产设备+传导管线")) {

            } else if (typeArr[i].equals("运输工具")) {

            }
        }
        //
        List<HouseExcel> data0List = new ArrayList<>();
        List<HouseCard> houseList = houseCardService.list(new LambdaQueryWrapper<HouseCard>().eq(HouseCard::getHaveFull, "是"));
        for (HouseCard houseCard : houseList) {
            HouseExcel houseExcel = new HouseExcel();
            BeanUtils.copyProperties(houseCard, houseExcel);
            houseExcel.setGetDatee(houseCard.getGetDate().toString());
            data0List.add(houseExcel);
        }
        WriteSheet sheet0 = EasyExcel.writerSheet(0, "房屋及构建筑物").head(HouseExcel.class).build();
//        //
        excelWriter.write(data0List, sheet0);
        //
        excelWriter.finish();
    }
}
