# 架构说明

```text
Vue 3 管理端 / 响应式 H5
          │ HTTP / JSON
Spring Security → Controller → Service → Spring Data JPA → MySQL 8
                                  │
                          运营风险规则引擎
```

当前版本以单体分层架构保证易运行与易理解。`DomainCatalog` 管理领域样例和建议，`OperationsService` 负责统计与风险计算，`WorkItem` 是可继续扩展的协同事项聚合。生产化时建议增加企业 SSO、细粒度 RBAC、操作审计、异步事件、缓存、脱敏和可观测性。
