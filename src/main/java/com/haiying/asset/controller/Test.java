package com.haiying.asset.controller;

import com.haiying.asset.common.result.Wrapper;
import com.haiying.asset.model.vo.UploadVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Wrapper
public class Test {
    public static void main(String[] args) {
        String s="200704";
        System.out.println(s.substring(0,4));
        System.out.println(s.substring(4));
    }

    @PostMapping("test")
    public boolean a(@RequestBody UploadVO uploadVO) {
        System.out.println();
        return true;
    }
}
