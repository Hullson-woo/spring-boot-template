### SpringBoot Template
#### VERSION
- Java version：JDK1.8
- SpringBoot Version：2.6.13
- MySQL Version：5.1.38

#### RPOGRAM
- common
  - exception: 全局异常处理器、自定义业务异常
  - result: 封装统一结果返回工具
  - DataEntity: 通用字段实体类
- config
  - MyBatisConfig: MyBatisPlus配置，解决分页插件异常问题
  - WebCorsConfig: 后端跨域配置
- constant
  - SystemConstant: 系统常量
- utils: 工具类
- controller: 控制层
- service: 业务层
- dao: 持久层
- entity: 实体类
- dto: 用于接收前端传入参数
- vo: 用于后端返回数据处理

#### API
localhost:8081/example/system/health/check - 测试接口，用于测试项目启动后服务调用是否正常
