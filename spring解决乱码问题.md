# 1、tomcat配置编码UTF-8

* 位置：tomcat>conf>server.xml

  ~~~xml
  <Connector port="8080" protocol="HTTP/1.1"
                 connectionTimeout="20000"
                 redirectPort="8443"
  			   URIEncoding="UTF-8"/>
  ~~~

# 2、使用spring自带过滤器

* 位置：web>WEB-INF>web.xml

  ~~~xml
  <!--    spring自带解决乱码-->
      <filter>
          <filter-name>encoding</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <init-param>
              <param-name>encoding</param-name>
              <param-value>utf-8</param-value>
          </init-param>
      </filter>
      <filter-mapping>
          <filter-name>encoding</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  
  ~~~

## 第二步完成还不行的话，替换成其他人的过滤器

* 创建过滤器文件

  ~~~java
  package com.tuzhi.filter;
  
  /**
   * @program: SpringMVC-study
   * @description:过滤乱码
   * @author: 兔子
   * @create: 2021-12-03 11:28
   **/
  
  import javax.servlet.*;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletRequestWrapper;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  import java.io.UnsupportedEncodingException;
  import java.util.Map;
  
  /**
   * 解决get和post请求 全部乱码的过滤器
   */
  public class GenericEncodingFilter implements Filter {
  
      @Override
      public void destroy() {
      }
  
      @Override
      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          System.out.println("======乱码过滤器======");
          //处理response的字符编码
          HttpServletResponse myResponse=(HttpServletResponse) response;
          myResponse.setContentType("text/html;charset=UTF-8");
  
          // 转型为与协议相关对象
          HttpServletRequest httpServletRequest = (HttpServletRequest) request;
          // 对request包装增强
          HttpServletRequest myrequest = new MyRequest(httpServletRequest);
          chain.doFilter(myrequest, response);
      }
  
      @Override
      public void init(FilterConfig filterConfig) throws ServletException {
      }
  
  }
  
  //自定义request对象，HttpServletRequest的包装类
  class MyRequest extends HttpServletRequestWrapper {
  
      private HttpServletRequest request;
      //是否编码的标记
      private boolean hasEncode;
      //定义一个可以传入HttpServletRequest对象的构造函数，以便对其进行装饰
      public MyRequest(HttpServletRequest request) {
          super(request);// super必须写
          this.request = request;
      }
  
      // 对需要增强方法 进行覆盖
      @Override
      public Map getParameterMap() {
          // 先获得请求方式
          String method = request.getMethod();
          if (method.equalsIgnoreCase("post")) {
              // post请求
              try {
                  // 处理post乱码
                  request.setCharacterEncoding("utf-8");
                  return request.getParameterMap();
              } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
              }
          } else if (method.equalsIgnoreCase("get")) {
              // get请求
              Map<String, String[]> parameterMap = request.getParameterMap();
              if (!hasEncode) { // 确保get手动编码逻辑只运行一次
                  for (String parameterName : parameterMap.keySet()) {
                      String[] values = parameterMap.get(parameterName);
                      if (values != null) {
                          for (int i = 0; i < values.length; i++) {
                              try {
                                  // 处理get乱码
                                  values[i] = new String(values[i]
                                          .getBytes("ISO-8859-1"), "utf-8");
                              } catch (UnsupportedEncodingException e) {
                                  e.printStackTrace();
                              }
                          }
                      }
                  }
                  hasEncode = true;
              }
              return parameterMap;
          }
          return super.getParameterMap();
      }
  
      //取一个值
      @Override
      public String getParameter(String name) {
          Map<String, String[]> parameterMap = getParameterMap();
          String[] values = parameterMap.get(name);
          if (values == null) {
              return null;
          }
          return values[0]; // 取回参数的第一个值
      }
  
      //取所有值
      @Override
      public String[] getParameterValues(String name) {
          Map<String, String[]> parameterMap = getParameterMap();
          String[] values = parameterMap.get(name);
          return values;
      }
  }
  
  ~~~

* web.xml中配置过滤器

  ~~~xml
  <!--    第三方自定义过滤器解决乱码-->
      <filter>
          <filter-name>GenericEncodigFilter</filter-name>
          <filter-class>com.tuzhi.filter.GenericEncodingFilter</filter-class>
      </filter>
      <filter-mapping>
          <filter-name>GenericEncodigFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  ~~~

  