package test.com.java.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * Mybatis-plus 反向代码生成器
 * 由表结构生成Java代码
 *
 * 可以不事先创建包目录
 * MyBatis-Plus 从 3.0.3 之后移除了代码生成器与模板引擎的默认依赖，需要手动添加相关依赖
 *
 * 参考
 * https://baomidou.com/guide/generator.html#%E4%BD%BF%E7%94%A8%E6%95%99%E7%A8%8B
 *
 */
public class MPGenerator {
    @Test
    public void testGenerator() {
        // 1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // String projectPath = "E:\\mylab\\JavaEE\\MyBatisPlus\\mp03\\src\\main\\java";
        String projectPath = "E:\\dev\\JavaEE\\MyBatisPlus\\mp03\\src\\main\\java";
        config.setAuthor("hanxiao2100@qq.com")  // 作者信息
                .setActiveRecord(true)  // 是否使用AR模式
                .setFileOverride(true)  // 覆盖原来的文件
                .setIdType(IdType.AUTO)  // 主键策略
                .setServiceName("%sService")  // service接口的名称
                .setControllerName("%sController")  // Controller名称
                .setOpen(false)  // 生成代码后是否打开输出目录
                .setBaseResultMap(true)  // 基本结果集合
                .setBaseColumnList(true)  // 设置基本的列
                .setOutputDir(projectPath);  // 生成的代码输出目录

        // 2. 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/mp?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false")
                .setUsername("root")
                .setPassword("py123456");

        // 3. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)  // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel)  // 表下划线列名转实体时使用驼峰
                .setTablePrefix("tbl_")   // 表前缀
                .setInclude("tbl_employee");  // 需要生成的表，多个，则传多个字符串参数，因为形参为：String... include，如果不指定 include，这表示此库中的所有表都生成代码

        // 4. 包名策略
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.java.mp")
                .setEntity("bean")
                .setService("service")
                .setController("controller")
                .setMapper("mapper")
                .setXml("mapper");

        // 5. 创建代码生成器实例，整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        // 6. 执行
        autoGenerator.execute();
    }
}
