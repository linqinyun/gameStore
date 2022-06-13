# gameStore
游戏商城，J2EE开发、后端系统
## 开发目标
1. 产品模块开发
2. 用户管理
3. 登录模块
4. 后台模块开发
### 规范
- JavaBean标准
- 使用REST接口
- 使用Maven构建

## 目录结构
- src -- 主目录
  - main -- 主程序
    - java -- 源码
      - dao -- 持久层
      - dto -- 封装返回
      - entity -- 实体类
      - enums -- 枚举变量
      - exceptions -- 运行异常封装、用于事务
      - interceptor -- 拦截器
      - service -- 服务层、
      - util -- 工具封装
      - web -- controller层
    - resources -- spring、mapper等配置
    - webapp -- html模板文件及静态资源、web.xml配置
  - test -- 单元测试
- sql -- sql文件
- pom -- Maven配置
- README -- 自述文件

### 开发流程
1. 设计
2. 数据库
3. 目录结构
4. maven项目配置pom，加载jar包
5. 实体类
6. 自下而上配置
   1. 数据库jdbc配置
   2. mybatis-config配置
   3. spring配置
      1. spring-dao配置
      2. spring-service配置
      3. spring-web配置
   4. web.xml整合配置、spring配置
7. 测试
   1. 测试配置
   2. 测试结构
8. 日志文件-logback
   1. logback.xml配置
   2. 配置在controller、service层中使用

#### 更新
##### 0.1
1. 配置文件/目录结构
2. 编写数据库
3. 编写实体类
4. 各层级需求配置dao、service、controller、webXML
5. 管理员AuthUser及登录后台主要流程完成，无页面状态。
6. 产品管理
7. 分类管理
##### 0.2
1. 删除原有数据库，重新设计数据库
2. 修改entity
3. 修改dao、service、controller、webXML
