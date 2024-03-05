package com.auction.cataloguemicroservice.repository;
import javax.sql.DataSource;

import com.auction.cataloguemicroservice.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository itemRepository) {

        return args -> {
            log.info("Preloading " + itemRepository.save(new Item(1, "chair", "description1", 10.99)));
            log.info("Preloading " + itemRepository.save(new Item(2, "chairs", "description2", 11.99)));
        };
    }
//    @Autowired
//    Environment env;
//    @Bean public DataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.sqlite-jdbc"));
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        return dataSource;
//    }
}
