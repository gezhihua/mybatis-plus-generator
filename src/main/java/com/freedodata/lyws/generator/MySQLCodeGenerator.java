package com.freedodata.lyws.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.querys.ClickHouseQuery;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author: gezh
 * @email: rasonboy@163.com
 * @date: 2023/1/11-16:11
 * @package: com.freedodata.lyws.generator
 * @project: mybatis-plus-generator
 * @description: 类功能描述
 * @version: v1.0.0
 * @Copyright 2023 FreedoData Co. Ltd
 * @since JDK1.8
 */
public class MySQLCodeGenerator {
    private static final String REPLACE_LOCAL_PATH = "/target/test-classes";
    private static final String JAVA_PATH = "/src/main/java";
    public static void main(String[] args) {

        // 获取项目路径
        String projectPath = ClassLoader.getSystemResource("").getPath().replace(REPLACE_LOCAL_PATH, "");
        // 全局配置
        GlobalConfig gc = GeneratorBuilder.globalConfigBuilder()
                .fileOverride().openDir(false)
                .outputDir(projectPath + JAVA_PATH)
                .author("gezh")
                .commentDate("yyyy-MM-dd").build();


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder("jdbc:mysql://192.168.1.59:3306/classicrealmd", "root", "123456")
                .dbQuery(new MySqlQuery()).schema("classicrealmd").build();


        // 包配置
        PackageConfig pc = GeneratorBuilder.packageConfigBuilder().parent("com.xxx.xxx").build();

        String[] tables = {"account", "account_banned"};
        // 策略配置
        StrategyConfig strategy = GeneratorBuilder.strategyConfigBuilder()
                .addInclude(tables)
                .addTablePrefix(pc.getModuleName() + "_")
                .controllerBuilder().enableHyphenStyle()
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .versionColumnName("version").logicDeleteColumnName("isDelete")
                .enableLombok()
                .build();

        TemplateConfig templateConfig = GeneratorBuilder.templateConfigBuilder().build();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dsc).global(gc).strategy(strategy).template(templateConfig).packageInfo(pc);
        mpg.execute(new FreemarkerTemplateEngine());

    }
}
