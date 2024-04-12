### session来源
session会话是服务器的web容器本身创建的,
<font color=#99CCFF style=" font-weight:bold;">不是我们在服务端new出来的</font>,
当客户端请求服务端时,服务端<font color=#99CCFF style=" font-weight:bold;">web容器会自动为对应客户端创建对应的session会话</font>
然后这个对应的会话就会给你个sessionid来区分你
你的浏览器会自动带这个sessionid访问过去,
所以<font color=#99CCFF style=" font-weight:bold;">session是一种服务端的一个用户相关数据表</font>
用户那边只需要存sessionid就行了,

当然,服务端那边过一个月把表上某些sessionid及对应值删了
那此时客户端请求服务端时,拿这sessionid<font color=#FFCCCC style=" font-weight:bold;">服务器就不认了</font>,
此时服务器就需要重新建立sessionid给他

### session的使用场景
1. 用户认证和授权：当用户进行登录操作时，服务端会验证用户的身份，并将与该用户关联的会话ID存储在服务器端。在后续的请求中，服务端可以通过检查客户端带来的会话ID，来确定用户的身份和权限。这样可以实现用户认证和授权功能，确保只有经过验证的用户才能访问受限资源或执行敏感操作。
    
2. 会话数据存储：服务端可以根据会话ID将与客户端相关的数据存储在服务器端的会话存储中。这使得服务端能够跟踪和共享客户端在不同请求之间的数据。例如，在购物网站中，服务端可以使用会话ID来存储用户的购物车信息，以确保用户在不同页面之间保持相同的购物车状态。
    
3. 分布式环境下的会话共享：在分布式系统中，多个服务器可能同时处理来自同一个客户端的请求。为了实现会话的共享和一致性，这些服务器可以使用客户端带来的会话ID来识别和访问共享的会话数据。这可以通过将会话数据存储在共享的缓存或数据库中，并使用会话ID作为键来实现。

### session用户登录代码
一个使用Session的具体场景是用户登录认证。当用户在网站上进行登录操作时，服务器可以使用Session来跟踪用户的登录状态，并在后续请求中验证用户的身份。
```java

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 进行用户验证逻辑，假设用户名为"user"，密码为"password"
        if (username.equals("user") && password.equals("password")) {
            // 用户验证成功，创建Session并将用户信息存储在Session中
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 重定向到登录成功页面
            response.sendRedirect("success.jsp");
        } else {
            // 用户验证失败，重定向到登录失败页面
            response.sendRedirect("failure.jsp");
        }
    }
}
/*
上述示例中，当用户提交登录表单时，Servlet会获取用户名和密码。然后，通过比对用户名和密码是否匹配，决定是创建Session并将用户名存储在Session中，还是重定向到登录失败页面。如果验证成功，用户将被重定向到登录成功页面，在后续的请求中，可以通过Session对象来获取用户的登录信息。
*/
```
请注意，这只是一个简单的示例，实际的用户认证可能涉及更多的操作，如密码加密、用户权限管理等。此外，还需要在Web应用程序中配置Session管理器（例如在web.xml文件中），以确保正确地管理和使用Session。

#### session过期时间
Session的过期时间可以通过服务器的配置进行设置，具体的过期时间可以因系统而异。通常情况下，Session的过期时间可以有以下几种方式进行定义：

1. 默认过期时间：大多数Web框架和服务器会设置一个默认的Session过期时间，通常为几十分钟到几小时不等。这个默认值可以通过服务器的配置进行修改。

2. 固定过期时间：可以在服务器的配置中指定Session的固定过期时间。无论用户是否活跃，都将在一定时间后过期。

3. 活动过期时间：也被称为闲置超时（Idle Timeout），即在一段时间内没有用户活动或请求时，Session将过期。这个时间段可以是几分钟到数小时不等，也可以由开发人员自行配置。

4. 手动过期时间：开发人员可以在代码中手动设置Session的过期时间。例如，在用户退出登录时，可以立即使Session过期，或者在特定操作完成后使Session过期。

总之，Session的过期时间可以根据服务器的配置、开发人员的需求或应用程序的要求进行设置。恰当地设置Session的过期时间可以平衡用户体验和服务器资源的利用。过期时间过短可能导致频繁的重新登录，过期时间过长可能会增加安全风险和服务器资源开销。