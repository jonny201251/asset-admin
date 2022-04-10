package com.haiying.asset.controller;

import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.entity.AssetBuy;
import com.haiying.asset.model.vo.UploadVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@Wrapper
public class Test {
    public static void main(String[] args) {
        List<AssetBuy> list = new ArrayList<>();
        AssetBuy buy1 = new AssetBuy();
        buy1.setStatus("1");
        AssetBuy buy2 = new AssetBuy();
        buy2.setStatus("2");
        list.add(buy1);
        list.add(buy2);
        Map<String, AssetBuy> map = list.stream().collect(Collectors.toMap(AssetBuy::getStatus, v -> v));

        AssetBuy buy=map.get("1");
        buy.setStatus("111");
        buy.setName("111111");
        System.out.println();
    }

    @PostMapping("test")
    public boolean a(@RequestBody UploadVO uploadVO) {
        System.out.println();
        return true;
    }
}
