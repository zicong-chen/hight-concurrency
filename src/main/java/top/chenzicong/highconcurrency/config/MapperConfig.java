package top.chenzicong.highconcurrency.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@MapperScan(basePackages = "top.chenzicong.highconcurrency.dao")
@Configuration
public class MapperConfig {
}
