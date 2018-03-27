# SpringBoot集成规则引擎Drools项目Demo

## 使用版本
**SpringBoot-version:1.5.9.RELEASE**

**Drools-version:7.6.0.Final**

## 目录结构
```bash
.
├── java
│   └── me
│       └── sdevil507
│           └── drools
│               ├── SpringBootDroolsApplication.java
│               ├── config
│               │   └── DroolsAutoConfiguration.java    #drools配置
│               ├── controller
│               │   ├── IntegralController.java #经典的订单返还积分demo
│               │   └── TestActivationGroupController.java  #条件互斥demo
│               └── model
│                   └── OrderIntegralDTO.java   #(FACT)对象
└── resources
    ├── application.yml
    └── rules
        ├── activation_group.drl    #互斥规则文件
        ├── order_integral.drl  #订单返还积分规则文件
        └── test.drl    #测试规则文件
```