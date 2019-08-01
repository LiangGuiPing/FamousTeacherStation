package com.util.config;
import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Configuration
// @ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties(PageHelperProperties.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class PageHelperAutoConfig {

    Logger logger = LoggerFactory.getLogger(PageHelperAutoConfig.class);

    private final List<SqlSessionFactory> sqlSessionFactories;

    private final PageHelperProperties properties;

    @Autowired
    public PageHelperAutoConfig(List<SqlSessionFactory> sqlSessionFactories, PageHelperProperties properties) {
        this.sqlSessionFactories = sqlSessionFactories;
        this.properties = properties;
    }

    @Bean
    @ConfigurationProperties(prefix = PageHelperProperties.PAGEHELPER_PREFIX)
    public Properties pageHelperProperties() {
        logger.info("解决pageHelper不分页的问题......");
        return new Properties();
    }

    @PostConstruct
    public void addPageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.putAll(pageHelperProperties());
        properties.putAll(this.properties.getProperties());
        interceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactories) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }

}
