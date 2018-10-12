# spring-boot-mybatis-annotation-mulidatasource

## 备注
该工程主要通过多线程方式，解析EXCEL并将结果存入实体表  
######1. relationInfo表  
  该主要存储实体和EXCEL文件中sheet的对应关系，为反射时提供信息
######2. sourceInfo表
  该表主要用于存储读取EXCEL文件的目录和名称等信息，可以配置多个
######3. 客户信息表  
  该表因为业务需要使用了中文名称定义，各位看官看看就好，不推荐，哎，开发整不过搞设计的

##新增EXCEL SHEET步骤
######1. 数据库中新建对应表A(举例)
######2. 在com.neo.entity.po下新建实体A，并覆写cast方法
######3. 在com.neo.mapper.test1下新建实体AMapper
######4. 在relationInfo表插入对应数据
######5. 在sourceInfo表插入对应数据


## 特点
1. 使用SpringBoot多数据源，虽没用到，但可供学习  
2. 使用mybatis注解方式，增删改查，不再使用xml
3. 多线程处理数据，ThreadPoolExecutor
4. 非分布式的定时任务调度，EnableScheduling
