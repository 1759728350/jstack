##### @JsonProperty

当bean中字段与@RequestBody接收到前端的字段不同时
我们需要建立关系进行匹配
```java
public Result addCommit(@RequestBody Commit commit){
```
比如说前端传一个评论对象
![](img/Pasted%20image%2020221019220519.png)
其中汽车id字段叫carId

但你接收的bean对象的字段是coCid
![](img/Pasted%20image%2020221019220640.png)
此时直接用commit对象接收@RequestBody传来的参数
那么coCid将会为空字符

<font color=#FFCCCC style=" font-weight:bold;">那么怎么办?</font>
使用<font color=#F09B59 style=" font-weight:bold;">@JsonProperty(value = "carId")</font>
<font color=#66CC99 style=" font-weight:bold;">告诉bean当json的字段是carId时,调用set方法更改被该注解修饰的值</font>


