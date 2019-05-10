package me.sdevil507.drools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;


/**
 * Drools配置
 *
 * @author sdevil507
 */
@Configuration
public class DroolsAutoConfiguration {

    private static final String RULES_PATH = "rules/";

    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }

    /**
     * 构建KieServices
     * <p>
     * 该接口提供了很多方法，可以通过这些方法访问KIE关于构建和运行的相关对象。<br/>
     * 如：可以通过KieServices获取KieContainer，利用KieContainer来访问KBase和KSession等信息；<br/>
     * 可以获取KieRepository对象，利用KieRepository来管理KieModule等。<br/>
     * KieServices就是一个中心，通过它来获取的各种对象来完成规则构建、管理和执行等操作。
     *
     * @return KieServices
     */
    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    /**
     * 构建KieContainer
     * <p>
     * KieContainer就是一个KieBase的容器。<br/>
     * 提供了获取KieBase的方法和创建KieSession的方法。<br/>
     * 其中获取KieSession的方法内部依旧通过KieBase来创建KieSession。
     *
     * @return KieContainer
     * @throws IOException IO异常
     */
    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer getKieContainer() throws IOException {
        // KieRepository是一个单例对象，它是存放KieModule的仓库
        final KieRepository kieRepository = getKieServices().getRepository();

        //noinspection Convert2Lambda,Anonymous2MethodRef
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });

        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        KieContainer kieContainer = getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());

        return kieContainer;
    }

    @Bean
    @ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
    public KModuleBeanFactoryPostProcessor getKModuleBeanFactoryPostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }
}
