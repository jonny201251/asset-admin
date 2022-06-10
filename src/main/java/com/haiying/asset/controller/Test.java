package com.haiying.asset.controller;

import cn.hutool.core.io.FileUtil;
import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.vo.UploadVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@Wrapper
public class Test {
    public static void main(String[] args) {
        List<String> list = FileUtil.readUtf8Lines("d:/部门.txt");
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String s : list) {
            map.merge(s, 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }

    @PostMapping("test")
    public boolean a(@RequestBody UploadVO uploadVO) {
        System.out.println();
        return true;
    }
}
