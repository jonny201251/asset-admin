package com.haiying.asset.controller.report;

import com.haiying.asset.model.report.LoseFullReport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loseFull")
public class LoseFullController {

    @GetMapping("get")
    public Map<String, List<LoseFullReport>> get() {
        Map<String, List<LoseFullReport>> map = new HashMap<>();

        return map;
    }
}
