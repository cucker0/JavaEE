package test.com.java.spring.hibernate;

import com.java.spring.hibernate.service.BookShopService;
import com.java.spring.hibernate.service.Casher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private ApplicationContext ctx;
    private BookShopService bookShopService;
    private Casher casher;

    {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        bookShopService = ctx.getBean(BookShopService.class);
        casher = ctx.getBean(Casher.class);
    }

    @Test
    public void testDataSource() {
        // 建好实体类 TAccount、TBook及相应的hbm.xml文件，配置了hibernate.cfg.xml、spring.xml后，执行此方法会自动生成相应的表
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource);
        /*
Hibernate:

    create table t_account (
       id integer not null auto_increment,
        username varchar(255),
        password varchar(255),
        balance double precision,
        primary key (id)
    ) engine=InnoDB
Hibernate:

    create table t_book (
       id integer not null auto_increment,
        sn varchar(255),
        bookName varchar(255),
        price double precision,
        stock integer,
        primary key (id)
    ) engine=InnoDB

 */
    }

    @Test
    public void testBookShopService() {
        bookShopService.purchase(1, "b1001", 2);
    }

    // 事务测试
    @Test
    public void testCasher() {
        List<Map<String, Object>> books = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("sn", "b1001");
        map.put("num", 2);
        books.add(map);
        Map<String, Object> map2 = new HashMap<>() {
            {
                put("sn", "b1002");
                put("num", 1);
            }
        };
        books.add(map2);

        casher.checkOut(1, books);
    }

}
