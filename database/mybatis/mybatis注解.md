##### @TableField
当bean与数据库字段不一致时使用该注解能进行映射建立两个字段的联系
```java
@TableField("co_cid")  
private String coCid;
```
数据库字段为co_cid
![](img/Pasted%20image%2020221019222432.png)
SQL: SELECT  co_id AS coId,co_cid AS coCid,co_uid AS coUid,content,create_time AS createTime,level  FROM commit     WHERE (coCid = ?)

从上面这个sql语句可以看出来
mybatisplus是<font color=#66CC99 style=" font-weight:bold;">将注解中的字段当成查询字段</font>
<font color=#66CC99 style=" font-weight:bold;">bean的字段当成as别名</font>


##### @TableId
<font color=#F09B59 style=" font-weight:bold;">主键自动生成</font>
不用每次insert语句来创建uuid了
很方便,优先用
```java
@TableId(value = "userId",type = IdType.ASSIGN_UUID)
```