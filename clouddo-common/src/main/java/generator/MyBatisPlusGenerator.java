package generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus代码生成器(用于生成entity)<br>
 * @author tiger
 * @Date 2017/5/21 12:38
 */
public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:\\StudyTools\\IntelliJ IDEA 2019.1.1\\workspace\\Technique\\clouddo\\CodeGeneration")//这里写你自己的java目录
                .setFileOverride(true)//是否覆盖
                .setActiveRecord(true)
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setAuthor("zhangjinhu");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    @Override
                    public DbColumnType processTypeConvert(String fieldType) {
                        return super.processTypeConvert(fieldType);
                    }
                })
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456")
                .setUrl("jdbc:mysql://127.0.0.1:3306/clouddo?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{""})// 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null)
                .setEntity("com.henu.papermanage.common.system.model")
                .setMapper("com.henu.papermanage.common.system.dao")
                .setXml("com.henu.papermanage.common.system.dao.mapping")
                .setService("com.henu.papermanage.common.system.service")
                .setServiceImpl("com.henu.papermanage.common.system.service.impl")
                .setController("com.henu.papermanage.common.system.controller");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}