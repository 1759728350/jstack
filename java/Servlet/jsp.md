#### 生命周期四个阶段

##### 1.加载和实例化

客户端首次向服务器发出请求

##### 2.初始化

init方法,初始化后servlet一直在内存中存在,直到被销毁

#### 3.处理请求

service方法

##### 4.销毁

destory方法



#### 声明servlet

```xml
<servlet>
    <servlet-name></servlet-name>
    <servlet-class></servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name></servlet-name>
    <url-pattern></url-pattern>
</servlet-mapping>
```

声明fitler
把servlet换成filter即可

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

##### 重定向

```java
response.sendRedirect("/chapter/index.jsp")
```



##### 和请求转发

```java
request.getRequestDispatcher("/index.jsp").forward(request,response)
```

区别
重定向是一个新的请求,所以无法获取之前存的属性值

##### form表单请求

调用相应接口servlet的dopost和doget方法

##### 超链接请求

调用相应接口servlet的doget方法
参数只能在url ?后面拼接

区别
post比get安全
post比get传输的容量大,get有请求长度限制
get常用于读取数据,post常用于修改数据
get会被缓存,post不会

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

#### servlet四个核心接口

##### servletConfig接口

初始化一个servlet时会创建servletConfig对象并将其作为参数放入init方法中传递给servlet
servletConfig可获取:
1.初始化参数
2.ServletContext对象

##### servletContext接口

servlet上下文,记录整个应用的上下文信息,整个应用共用唯一一个servletContext对象,大于session域,多个用户多个对话,当多个用户想要共享信息,只能将其存在context域中

##### HttpServletRequest接口

request.getParamter("属性名")

##### HttpServletResponse接口

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

会话跟踪技术
cookie:数据存在客户端上
session:数据存在服务端上

```java
request.getAttribute("属性名")
request.setAttribute("属性名","属性值")//不同于getParamter取form表单属性,这个是去取几个域中的属性
```



<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

#### jsp三大元素

##### 脚本元素

```jsp
语法:<% jj %>
声明:<%!int i = 0;%>
     <%!public String f(int i){
         if(i<3)
             return "";
     }
     %>

表达式:<% =表达式 %>
例子:
<%
 for(int i = 1;i<10;i++){
%>
<% = i*i %>
<% } %>
```

脚本元素里定义变量和声明里定义变量区别是什么?
前者所写的都在jsp所对应的servlet类里的service方法里
声明将会变成这个servlet类的成员属性

能在脚本里定义方法吗?
**不能**,因为所写的代码都**生成在service方法中**,方法中无法再定义方法,声明能定义是因为那个方法和service方法平级
在jsp脚本中定义的变量也是在service方法中的**局部变量**,局部变量比声明的成员变量优先级高

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

##### 指令元素

page指令

```jsp
<%@page 属性 import="java.utils.* , com.utils.* " %>
```

page指令可以出现多次,但在其中的属性只能出现一次,后面的会覆盖前面的
import属性
如果引用多个包中间用逗号分割



include指令元素与include动作元素有什么区别?

```jsp
指令元素:<%@ include file="header.jsp" %>
#将两个文件合并,然后编译运行,称为静态包含
动态元素:<jsp:include page="header.jsp" flush="false">
#不会将两个文件合并成新页面,在jsp运行时直接显示    
```



taglib指令

```jsp
<%@ taglib url="标签库" prefix="标签前缀" %>   当需要使用标签库时写   然后标签<前缀:标签名>
```



##### 动作元素

使用javabean时的动作元素是什么?

```jsp
<jsp:useBean id="name1" class="className" scope="page|request|session|application">  #默认page
#获取属性值时,填id
#动作元素获取类属性值
<jsp:getProperty name="name1" property="age">
```



<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

#### jsp内置对象

request   详见p200,明确对象名称和作用
response
out
session
application
pageContext
config
page
exception

##### jsp四种作用域

page页面域 request请求域 session会话域 application应用域

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

##### javaBean规范

1.类是public,可供访问
2.有无参构造
3.有set,get方法

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" width="80%" color=#987cb9 SIZE=10>

##### el表达式

99乘法表

```html
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>九九乘法表</title>
</head>
<body>
	九九乘法表
	<table border=1>
		<c:forEach var="i" begin="1" end="9">
				<tr>
				<c:forEach var="j" begin="1" end="${i}">
					<td>${i}×${j}=${i*j}</td>
				</c:forEach>
				</tr>
		</c:forEach>
	</table>
</body>
</html>
```

el表达式取值

```shell
${sessionScope.对象名/useBean的id.age}  不写范围,根据id从小往大找
```

${empty sessionScope.age}



标签库

```jsp
<c:foreach items="${array}" var="item">begin= end= step=
    ${item}
</c:foreach>   


<c:if test="${empty sessionScope.age}">
    ${sessionScope.age}
</c:if>    

<c:choose>
    <c:when test="${i < 10}">
        jjj
    </c:when>
    <c:otherwise>
        sss
    </c:otherwise>
</c:choose>    
```

