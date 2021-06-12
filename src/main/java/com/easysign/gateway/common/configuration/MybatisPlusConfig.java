package com.easysign.gateway.common.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.easysign.gateway.interceptor.CreateUpdateTimeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: SuperSign
 * @Package: com.cjml.supersign.configuration
 * @ClassName: MybatisPlusConfig
 * @Author: Strawberry
 * @Description: sql拦截器注入
 * @Date: 2020/09/17 16:02
 * @Version: 1.0
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public CreateUpdateTimeInterceptor createUpdateTimeInterceptor() {
        return new CreateUpdateTimeInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean  //让Spring容器进行管理
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}