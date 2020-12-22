package test.com.java.mp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import org.junit.Test;

/**
 * Mybatis-plus 代码生成器
 *
 * MyBatis-Plus 从 3.0.3 之后移除了代码生成器与模板引擎的默认依赖，需要手动添加相关依赖
 * https://baomidou.com/guide/generator.html#%E4%BD%BF%E7%94%A8%E6%95%99%E7%A8%8B
 *
 */
public class MPGenerator {
    @Test
    public void testGenerator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1. 全局配置
        GlobalConfig config = new GlobalConfig();
        String projectPath = System.getProperty("E:\\dev\\JavaEE\\MyBatisPlus\\mp03\\java");
        config.setAuthor("hanxiao2100@qq.com")
                .setActiveRecord(true)
                .setFileOverride(true)
                .setIdType(IdType.AUTO)
                .setServiceName("%sService")
                .setOutputDir(projectPath);


        // 2. 数据源配置

        // 3. 策略配置

        // 4. 包名策略

        // 5. 整合配置

        // 6. 执行

    }
}
