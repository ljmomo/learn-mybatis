### 一, 功能架构设计
![image](https://note.youdao.com/yws/public/resource/0e5f295122e74fecddbb799cdd00dcf3/xmlnote/7E79F99B96544A4984DC8FC60D0FEDE7/14143)
功能架构讲解：  

1. 接口层   
 提供给外部使用的接口API，开发人员通过这些本地API来操纵数据库。接口层一接收到调用请求就会调用数据处理层来完成具体的数据处理。
  
   MyBatis和数据库的交互有两种方式：   
  a.使用传统的MyBatis提供的API.   
  b.使用Mapper接口.
2. 数据处理层  
    负责具体的SQL查找、SQL解析、SQL执行和执行结果映射处理等。它主要的目的是根据调用的请求完成一次数据库操作。


3. 基础支撑层       
 负责最基础的功能支撑，包括连接管理、事务管理、配置加载和缓存处理，这些都是共用的东西，将他们抽取出来作为最基础的组件。为上层的数据处理层提供最基础的支撑。



### 二, 框架架构设计

![image](https://note.youdao.com/yws/public/resource/0e5f295122e74fecddbb799cdd00dcf3/xmlnote/D1893CD6E8C94E949AC9248DC19D2952/14175)



1. 加载配置：配置来源于两个地方，一处是配置文件，一处是Java代码的注解，将SQL的配置信息加载成为一个个MappedStatement对象（包括了传入参数映射配置、执行的SQL语句、结果映射配置），存储在内存中。 Mybatis和数据库交互的两种方式：(1)传统的MyBatis提供的API(2)使用Mapper接口

2. SQL解析：当API接口层接收到调用请求时，会接收到传入SQL的ID和传入对象（可以是Map、JavaBean或者基本数据类型），Mybatis会根据SQL的ID找到对应的MappedStatement，然后根据传入参数对象对MappedStatement进行解析，解析后可以得到最终要执行的SQL语句和参数。

3. SQL执行：将最终得到的SQL和参数拿到数据库进行执行，得到操作数据库的结果。

4. 结果映射：将操作数据库的结果按照映射的配置进行转换，可以转换成HashMap、JavaBean或者基本数据类型，并将最终结果返回。

### 三, Mybatis 一次查询过程
一次查询流程大致如下图：

![image](https://note.youdao.com/yws/public/resource/0e5f295122e74fecddbb799cdd00dcf3/xmlnote/B8B1168D74C44330A83BD9A96692A0DC/14190)

流程中Mybatis核心组件说明：   
- Configuration  MyBatis所有的配置信息都维持在Configuration对象之中。
- SqlSession            作为MyBatis工作的主要顶层API，表示和数据库交互的会话，完成必要数据库增删改查功能。
- MapperProxy  Mapper接口的代理实现类，主要用来生成Mapper接口实例对象。
- Executor              MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护。
- StatementHandler   封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数、将Statement结果集转换成List集合。
- ParameterHandler   负责对用户传递的参数转换成JDBC Statement 所需要的参数。
- ResultSetHandler    负责将JDBC返回的ResultSet结果集对象转换成List类型的集合。

以上只是流程图中核心组件说明其实还有几个我认为比较核心的组件：
- TypeHandler        负责java数据类型和jdbc数据类型之间的映射和转换。
- MappedStatement   MappedStatement维护了一条<select|update|delete|insert>节点的封装。
- SqlSource            负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回。
- BoundSql  表示动态生成的SQL语句以及相应的参数信息.

由上面的流程图可以得知一次查询的流程是：SqlSession 调用getMapper通过Configuration获取Mapper接口，并且通过 通过代理MapperProxy 生成Mapper接口的实例对象。然后代理调用SqlSession 的selectOne方法，SqlSession 又委托Executor来执行查询。Executor内部又调用StatementHandler来执行查询。StatementHandler在执行查询又调用了  ParameterHandler来转换参数。查询结果的处理又交给了 ResultSetHandler 。