/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-06-22 13:01 创建
 */
package org.antframework.manager.dal;

import org.antframework.boot.jpa.boot.AntJpaRepositoriesAutoConfiguration;
import org.antframework.boot.jpa.boot.AntJpaRepositoriesConfigureRegistrar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.util.Streamable;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * dal层配置
 */
@Configuration
@AutoConfigureAfter(AntJpaRepositoriesAutoConfiguration.class)
@Import({DalConfiguration.JpaScanConfiguration.class,
        DalConfiguration.JpaRepositoriesRegistrar.class})
public class DalConfiguration {
    /**
     * jpa repository扫描配置
     */
    public static class JpaRepositoriesRegistrar extends AntJpaRepositoriesConfigureRegistrar {
        @Override
        protected Streamable<String> getBasePackages() {
            return Streamable.of(ClassUtils.getPackageName(DalConfiguration.class));
        }
    }

    /**
     * jpa扫描配置
     */
    public static class JpaScanConfiguration implements BeanFactoryAware, BeanDefinitionRegistryPostProcessor {
        // Spring的bean工厂
        private BeanFactory beanFactory;

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }

        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
            List<String> packageNames = new ArrayList<>();
            // spring自动配置包
            packageNames.addAll(AutoConfigurationPackages.get(beanFactory));
            // 本工具被jpa扫描的包
            packageNames.add(ClassUtils.getPackageName(DalConfiguration.class));
            // 注册需要被jpa扫描的包
            EntityScanPackages.register(registry, packageNames);
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        }
    }
}
