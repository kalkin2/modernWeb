package com.kalkin.modernWeb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * MockMvc에서 scan 시 오류를 피하기 위해 application.java 에서 분리함
 * MockMvc는 일반적인 @Confiration을 스캔하지 않는다 !
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
