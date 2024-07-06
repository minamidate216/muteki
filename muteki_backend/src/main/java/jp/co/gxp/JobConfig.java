package jp.co.gxp;

import jp.co.gxp.infrastructure.mapper.JobMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {JobConfig.class})
@MapperScan(basePackageClasses = JobMapper.class)
public class JobConfig {
}
