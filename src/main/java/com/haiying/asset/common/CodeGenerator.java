package com.haiying.asset.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    //表名称
    private static String[] includeArr = {"asset_finance_code"};

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/asset", "root", "root")
                .globalConfig(builder -> {
                    builder.fileOverride()
                            .disableOpenDir()
                            .outputDir(projectPath + "/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.haiying.asset")
                            .entity("model.entity")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.enableCapitalMode()
                            .addInclude(includeArr);
                    builder.entityBuilder()
                            .idType(IdType.AUTO)
                            .enableLombok();
                    builder.controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController");
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl");
                    builder.mapperBuilder()
                            .enableMapperAnnotation()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}