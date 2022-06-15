package com.haiying.asset.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.*;
import com.haiying.asset.model.excel.*;
import com.haiying.asset.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/initCard")
@Wrapper
public class InitCardController {
    @Autowired
    InstrumentCardService instrumentCardService;
    @Autowired
    OfficeToolCardService officeToolCardService;
    @Autowired
    HouseCardService houseCardService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SysDeptService sysDeptService;
    @Autowired
    AssetLoseService assetLoseService;

    @GetMapping("car1")
    public boolean car1() throws Exception {
        InputStream inputStream = new FileInputStream("d:/b/车辆.xls");
        //
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        //
        ExcelListener<Car1> listener = new ExcelListener<>();
        //获取sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(Car1.class).registerReadListener(listener).build();
        //读取数据
        excelReader.read(sheet0);
        //获取数据
        List<Car1> list = listener.getData();


        List<InstrumentCard> data = new ArrayList<>();
        for (Car1 car1 : list) {
            if (ObjectUtil.isNotEmpty(car1.getCode()) && ObjectUtil.isNotEmpty(car1.getName())) {
                InstrumentCard card = new InstrumentCard();
                BeanUtils.copyProperties(car1, card);
                //累计折旧
                if (ObjectUtil.isEmpty(card.getStartLoseMoney())) {
                    throw new RuntimeException("累计折旧为空！！！");
                }
                Double d = NumberUtil.roundHalfEven(card.getStartLoseMoney(), 2).doubleValue();
                String d2 = NumberUtil.toStr(d);
                card.setStartLoseMoney(Double.valueOf(d2));

                card.setHaveOld("旧");
                card.setGuid(IdUtil.fastSimpleUUID());
                data.add(card);
            }
        }
        instrumentCardService.saveBatch(data);

        return true;
    }

    @GetMapping("yq")
    public boolean yq() throws Exception {
        InputStream inputStream = new FileInputStream("d:/b/仪器.xls");
        //
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        //
        ExcelListener<YQ> listener = new ExcelListener<>();
        //获取sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(YQ.class).registerReadListener(listener).build();
        //读取数据
        excelReader.read(sheet0);
        //获取数据
        List<YQ> list = listener.getData();


        List<InstrumentCard> data = new ArrayList<>();
        for (YQ yq : list) {
            if (ObjectUtil.isNotEmpty(yq.getCode()) && ObjectUtil.isNotEmpty(yq.getName())) {
                InstrumentCard card = new InstrumentCard();
                BeanUtils.copyProperties(yq, card);
                //累计折旧
                if (ObjectUtil.isEmpty(card.getStartLoseMoney())) {
                    throw new RuntimeException("累计折旧为空！！！");
                }
                Double d = NumberUtil.roundHalfEven(card.getStartLoseMoney(), 2).doubleValue();
                String d2 = NumberUtil.toStr(d);
                card.setStartLoseMoney(Double.valueOf(d2));

                card.setHaveOld("旧");
                card.setGuid(IdUtil.fastSimpleUUID());
                //资产分类、使用年限
                card.setCategoryId(3);
                card.setCategoryName("仪器仪表");
                card.setUseYear(10);

                data.add(card);
            }
        }
        instrumentCardService.saveBatch(data);

        return true;
    }

    @GetMapping("ot")
    public boolean ot() throws Exception {
        InputStream inputStream = new FileInputStream("d:/b/办公营具.xls");
        //
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        //
        ExcelListener<OT> listener = new ExcelListener<>();
        //获取sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(OT.class).registerReadListener(listener).build();
        //读取数据
        excelReader.read(sheet0);
        //获取数据
        List<OT> list = listener.getData();


        List<OfficeToolCard> data = new ArrayList<>();
        for (OT ot : list) {
            if (ObjectUtil.isNotEmpty(ot.getCode()) && ObjectUtil.isNotEmpty(ot.getName())) {
                OfficeToolCard card = new OfficeToolCard();
                BeanUtils.copyProperties(ot, card);
                //累计折旧
                if (ObjectUtil.isEmpty(card.getStartLoseMoney())) {
                    throw new RuntimeException("累计折旧为空！！！");
                }
                Double d = NumberUtil.roundHalfEven(card.getStartLoseMoney(), 2).doubleValue();
                String d2 = NumberUtil.toStr(d);
                card.setStartLoseMoney(Double.valueOf(d2));

                card.setHaveOld("旧");
                card.setGuid(IdUtil.fastSimpleUUID());
                //资产分类、使用年限
                card.setCategoryId(2);
                card.setCategoryName("办公营具");
                card.setUseYear(4);

                data.add(card);
            }
        }
        officeToolCardService.saveBatch(data);

        return true;
    }

    @GetMapping("house")
    public boolean house() throws Exception {
        InputStream inputStream = new FileInputStream("d:/b/房屋.xls");
        //
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        //
        ExcelListener<House> listener = new ExcelListener<>();
        //获取sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(House.class).registerReadListener(listener).build();
        //读取数据
        excelReader.read(sheet0);
        //获取数据
        List<House> list = listener.getData();


        List<HouseCard> data = new ArrayList<>();
        for (House house : list) {
            if (ObjectUtil.isNotEmpty(house.getCode()) && ObjectUtil.isNotEmpty(house.getName())) {
                HouseCard card = new HouseCard();
                BeanUtils.copyProperties(house, card);
                //累计折旧
                if (ObjectUtil.isEmpty(card.getStartLoseMoney())) {
                    throw new RuntimeException("累计折旧为空！！！");
                }
                Double d = NumberUtil.roundHalfEven(card.getStartLoseMoney(), 2).doubleValue();
                String d2 = NumberUtil.toStr(d);
                card.setStartLoseMoney(Double.valueOf(d2));

                card.setHaveOld("旧");
                card.setGuid(IdUtil.fastSimpleUUID());
                //资产分类、使用年限
                card.setCategoryId(23);
                card.setCategoryName("房屋及构建筑物");
                card.setUseYear(35);

                data.add(card);
            }
        }
        houseCardService.saveBatch(data);

        return true;
    }

    @GetMapping("sb")
    public boolean sb() throws Exception {
        InputStream inputStream = new FileInputStream("d:/b/设备.xls");
        //
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        //
        ExcelListener<SB> listener = new ExcelListener<>();
        //获取sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(SB.class).registerReadListener(listener).build();
        //读取数据
        excelReader.read(sheet0);
        //获取数据
        List<SB> list = listener.getData();


        List<InstrumentCard> data = new ArrayList<>();
        for (SB sb : list) {
            if (ObjectUtil.isNotEmpty(sb.getCode()) && ObjectUtil.isNotEmpty(sb.getName())) {
                InstrumentCard card = new InstrumentCard();
                BeanUtils.copyProperties(sb, card);
                //累计折旧
                if (ObjectUtil.isEmpty(card.getStartLoseMoney())) {
                    throw new RuntimeException("累计折旧为空！！！");
                }
                Double d = NumberUtil.roundHalfEven(card.getStartLoseMoney(), 2).doubleValue();
                String d2 = NumberUtil.toStr(d);
                card.setStartLoseMoney(Double.valueOf(d2));

                card.setHaveOld("旧");
                card.setGuid(IdUtil.fastSimpleUUID());
                //使用年限
                card.setUseYear(10);

                data.add(card);
            }
        }
        instrumentCardService.saveBatch(data);

        return true;
    }

    @GetMapping("qt")
    public boolean qt() throws Exception {
        InputStream inputStream = new FileInputStream("d:/b/其他.xls");
        //
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        //
        ExcelListener<QT> listener = new ExcelListener<>();
        //获取sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(QT.class).registerReadListener(listener).build();
        //读取数据
        excelReader.read(sheet0);
        //获取数据
        List<QT> list = listener.getData();


        List<InstrumentCard> data = new ArrayList<>();
        for (QT qt : list) {
            if (ObjectUtil.isNotEmpty(qt.getCode()) && ObjectUtil.isNotEmpty(qt.getName())) {
                InstrumentCard card = new InstrumentCard();
                BeanUtils.copyProperties(qt, card);
                //累计折旧
                if (ObjectUtil.isEmpty(card.getStartLoseMoney())) {
                    throw new RuntimeException("累计折旧为空！！！");
                }
                Double d = NumberUtil.roundHalfEven(card.getStartLoseMoney(), 2).doubleValue();
                String d2 = NumberUtil.toStr(d);
                card.setStartLoseMoney(Double.valueOf(d2));

                card.setHaveOld("旧");
                card.setGuid(IdUtil.fastSimpleUUID());
                //使用年限
                card.setUseYear(4);
                //凭证
                String financeCode = qt.getFinanceCode1();
                String financeCode2 = qt.getFinanceCode2();
                if (ObjectUtil.isNotEmpty(financeCode2)) {
                    if (ObjectUtil.isEmpty(financeCode)) {
                        financeCode = financeCode2;
                    } else {
                        financeCode = financeCode + "," + financeCode2;
                    }
                }
                card.setFinanceCode(financeCode);

                data.add(card);
            }
        }
        instrumentCardService.saveBatch(data);

        return true;
    }

    private LocalDate getDate(String str) {
        System.out.println(str);
        String[] arr = str.split("[-/]");
        String year = arr[0];
        String month = arr[1];
        String day = arr[2];
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String strDate = year + "-" + month + "-" + day;

        LocalDate ld = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
        return ld;
    }

    @GetMapping("house2")
    public boolean house2() throws Exception {
        InputStream inputStream = new FileInputStream("d:/b/房屋情况表.xls");
        //
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        //
        ExcelListener<House2> listener = new ExcelListener<>();
        //获取sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(House2.class).registerReadListener(listener).build();
        //读取数据
        excelReader.read(sheet0);
        //获取数据
        List<House2> list = listener.getData();

        List<HouseCard> old = houseCardService.list();
        Map<String, HouseCard> oldMap = old.stream().collect(Collectors.toMap(HouseCard::getCode, v -> v));

        List<HouseCard> nn = new ArrayList<>();

        for (House2 house2 : list) {
            //取得日期
            LocalDate getDate = getDate(house2.getGetDatee());
            //发证日期
            LocalDate own3 = getDate(house2.getOwn33());

            HouseCard hc = oldMap.get(house2.getCode());
            if (hc == null) {
                HouseCard card = new HouseCard();
                BeanUtils.copyProperties(house2, card);
                //累计折旧
                if (ObjectUtil.isEmpty(card.getStartLoseMoney())) {
                    throw new RuntimeException("累计折旧为空！！！");
                }
                Double d = NumberUtil.roundHalfEven(card.getStartLoseMoney(), 2).doubleValue();
                String d2 = NumberUtil.toStr(d);
                card.setStartLoseMoney(Double.valueOf(d2));

                card.setHaveOld("旧");
                card.setGuid(IdUtil.fastSimpleUUID());
                //资产分类、使用年限
                card.setCategoryId(23);
                card.setCategoryName("房屋及构建筑物");
                card.setUseYear(35);

                //
                card.setGetDate(getDate);
                card.setOwn3(own3);
                //
                card.setNumber(1);

                nn.add(card);
            } else {
                hc.setGetDate(getDate);
                hc.setOwn1(house2.getOwn1());
                hc.setOwn2(house2.getOwn2());
                hc.setOwn3(own3);
                hc.setOwn4(house2.getOwn4());
                hc.setOwn5(house2.getOwn5());
                hc.setOwn6(house2.getOwn6());

                hc.setOffice1(house2.getOffice1());
                hc.setOffice2(house2.getOffice2());
                hc.setOffice3(house2.getOffice3());
                hc.setOffice4(house2.getOffice4());

                hc.setBusiness1(house2.getBusiness1());
                hc.setBusiness2(house2.getBusiness2());
                hc.setBusiness3(house2.getBusiness3());
                hc.setBusiness4(house2.getBusiness4());

                hc.setOther1(house2.getOther1());
                hc.setOther2(house2.getOther2());
                hc.setOther3(house2.getOther3());
                hc.setOther4(house2.getOther4());

                hc.setRemark(house2.getRemark());
            }
        }
        houseCardService.updateBatchById(old);
        houseCardService.saveBatch(nn);

        return true;
    }

    //11条房屋数据
    @GetMapping("a11")
    public boolean a11() throws Exception {
        List<HouseCard> list = houseCardService.list(new LambdaQueryWrapper<HouseCard>().isNull(HouseCard::getUseDeptName));
        List<String> nameList = list.stream().map(HouseCard::getName).collect(Collectors.toList());
        Map<String, HouseCard> map = list.stream().collect(Collectors.toMap(HouseCard::getName, v -> v));
        //
        List<HouseCard> listt = houseCardService.list(new LambdaQueryWrapper<HouseCard>().in(HouseCard::getName, nameList).lt(HouseCard::getId, 354));
        for (HouseCard houseCard : listt) {
            HouseCard h = map.get(houseCard.getName());
            houseCard.setOwn1(h.getOwn1());
            houseCard.setOwn2(h.getOwn2());
            houseCard.setOwn3(h.getOwn3());
            houseCard.setOwn4(h.getOwn4());
            houseCard.setOwn5(h.getOwn5());
            houseCard.setOwn6(h.getOwn6());

            houseCard.setOffice1(h.getOffice1());
            houseCard.setOffice2(h.getOffice2());
            houseCard.setOffice3(h.getOffice3());
            houseCard.setOffice4(h.getOffice4());

            houseCard.setBusiness1(h.getBusiness1());
            houseCard.setBusiness2(h.getBusiness2());
            houseCard.setBusiness3(h.getBusiness3());
            houseCard.setBusiness4(h.getBusiness4());

            houseCard.setOther1(h.getOther1());
            houseCard.setOther2(h.getOther2());
            houseCard.setOther3(h.getOther3());
            houseCard.setOther4(h.getOther4());
        }

        houseCardService.updateBatchById(listt);
        return true;
    }

    //instrument，category_id
    @GetMapping("a")
    public boolean a() throws Exception {
        List<Category> categoryList = categoryService.list();
        Map<String, Integer> categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getName, Category::getId));

        List<InstrumentCard> list = instrumentCardService.list(new LambdaQueryWrapper<InstrumentCard>().isNull(InstrumentCard::getCategoryId));
        for (InstrumentCard card : list) {
            card.setCategoryId(categoryMap.get(card.getCategoryName()));
        }
        instrumentCardService.updateBatchById(list);
        return true;
    }

    //计算出-净值
    @GetMapping("a1")
    public boolean a1() throws Exception {
        List<SysDept> deptList = sysDeptService.list();
        Map<String, Integer> deptMap = deptList.stream().collect(Collectors.toMap(SysDept::getName, SysDept::getId));

        List<OfficeToolCard> list = officeToolCardService.list(new LambdaQueryWrapper<OfficeToolCard>());
        for (OfficeToolCard card : list) {
            card.setUseDeptId(deptMap.get(card.getUseDeptName()));
            if (card.getEndMoney().equals(0d)) {
                card.setHaveFull("是");
            }
        }
        officeToolCardService.updateBatchById(list);

        return true;
    }

    //end_year_month
    @GetMapping("a2")
    public boolean a2() throws Exception {
        //
        List<HouseCard> list1 = houseCardService.list(new LambdaQueryWrapper<>());
        for (HouseCard card : list1) {
            String d = card.getGetDate().toString().replaceAll("-", "");
            System.out.println();
            Integer year = null;
            String month = null;
            if (d.length() == 4) {
                year = Integer.parseInt(d) + card.getUseYear();
                month = "01";
            } else {
                year = Integer.parseInt(d.substring(0, 4)) + card.getUseYear();
                month = d.substring(4, 6);
            }
            card.setEndYearMonth(Integer.parseInt(year + month));
        }
        houseCardService.updateBatchById(list1);

        return true;
    }

    /*
    往asset_lose表中写入折旧
    未提满的数据
 */
    @GetMapping("a3")
    public boolean a3() throws Exception {
        List<AssetLose> result = new ArrayList<>();
        //未提满
        List<HouseCard> list = houseCardService.list(new LambdaQueryWrapper<HouseCard>().eq(HouseCard::getHaveFull, "否"));
        for (HouseCard card : list) {
            double d = card.getEndMoney() - card.getMonthLose();
            card.setEndMoney(d);
            card.setMonthLoseCount(1);

            AssetLose assetLose = new AssetLose();
            assetLose.setCode(card.getCode());
            assetLose.setName(card.getName());
            assetLose.setCategoryName(card.getCategoryName());
            assetLose.setGuid(card.getGuid());
            assetLose.setDeptName(card.getUseDeptName());
            assetLose.setMonthLose(card.getMonthLose());
            //
            assetLose.setStartYear(2022);
            assetLose.setStartMonth(1);
            assetLose.setNowYear(2022);
            assetLose.setNowMonth(1);

            result.add(assetLose);
        }

        assetLoseService.saveBatch(result);
        houseCardService.updateBatchById(list);
        return true;
    }


    //开始提已存在的折旧
    @GetMapping("a4")
    public boolean a4() throws Exception {
        List<AssetLose> loseList = assetLoseService.list();
        Map<String, AssetLose> loseMap = loseList.stream().collect(Collectors.toMap(AssetLose::getGuid, v -> v));

        List<OfficeToolCard> list = officeToolCardService.list(new LambdaQueryWrapper<OfficeToolCard>().eq(OfficeToolCard::getHaveFull, "否"));
        for (OfficeToolCard card : list) {
            int i1 = (int) Math.floor(card.getEndMoney());
            int i2 = (int) Math.floor(card.getMonthLose());
            double d = card.getEndMoney() - card.getMonthLose();
            if (i1 == i2) {
                //接着本次折旧
                AssetLose lose = loseMap.get(card.getGuid());
                lose.setNowMonth(lose.getNowMonth() + 1);
                lose.setEndLose(0d);
                //
                card.setEndMoney(0d);
                card.setMonthLoseCount(card.getMonthLoseCount() + 1);
                card.setHaveFull("是");
                card.setEndLose(0d);
            } else if (i1 < i2) {
                //折旧结束
                AssetLose lose = loseMap.get(card.getGuid());
                lose.setEndLose(card.getEndMoney());
                //
                card.setEndLose(card.getEndMoney());
                card.setEndMoney(0d);
                card.setHaveFull("是");
            } else {
                //i1>i2
                if (d <= 5) {
                    //接着本次折旧
                    AssetLose lose = loseMap.get(card.getGuid());
                    lose.setNowMonth(lose.getNowMonth() + 1);
                    lose.setEndLose(0d);
                    //
                    card.setEndMoney(0d);
                    card.setMonthLoseCount(card.getMonthLoseCount() + 1);
                    card.setHaveFull("是");
                    card.setEndLose(0d);
                } else {
                    //继续折旧
                    AssetLose lose = loseMap.get(card.getGuid());
                    lose.setNowMonth(lose.getNowMonth() + 1);
                    //
                    card.setEndMoney(d);
                    card.setMonthLoseCount(card.getMonthLoseCount() + 1);
                }
            }
        }

        assetLoseService.updateBatchById(loseList);
        officeToolCardService.updateBatchById(list);
        return true;
    }
}
