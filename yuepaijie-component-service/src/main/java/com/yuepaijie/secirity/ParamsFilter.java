package com.yuepaijie.secirity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 传参中body里面的参数去掉头尾的空格
 * （感觉不要加这个过滤器也已经去掉头尾的空格了，但是这个加了好像也不碍事），先蛮加着
 */
public class ParamsFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    //只是我们自己写的param参数去除空格并写回的类
    ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
    filterChain.doFilter(requestWrapper, response);
  }
}

class ParameterRequestWrapper extends HttpServletRequestWrapper {

  private Map<String , String[]> params = new HashMap<String, String[]>();

  @SuppressWarnings("unchecked")
  public ParameterRequestWrapper(HttpServletRequest request) {
    // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
    super(request);
    //将参数表，赋予给当前的Map以便于持有request中的参数
    this.params.putAll(request.getParameterMap());
    this.modifyParameterValues();
  }
  /**
   * 重载一个构造方法
   */
  public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
    this(request);
    //这里将扩展参数写入参数表
    addAllParameters(extendParams);
  }
  /**
   * 将parameter的值去除空格后重写回去
   */
  public void modifyParameterValues(){
      Set<String> set =params.keySet();
    Iterator<String> it=set.iterator();
    while(it.hasNext()){
      String key= (String) it.next();
      String[] values = params.get(key);
      values[0] = values[0].trim();
      params.put(key, values);
    }
  }

  /**
   * 重写getParameter，代表参数从当前类中的map获取
   */
  @Override
  public String getParameter(String name) {
    String[]values = params.get(name);
    if(values == null || values.length == 0) {
      return null;
    }
    return values[0];
  }

  @Override
  public String[] getParameterValues(String name) {//同上
    return params.get(name);
  }

  public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数
    for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
      addParameter(entry.getKey() , entry.getValue());
    }
  }

  public void addParameter(String name , Object value) {//增加参数
    if(value != null) {
      if(value instanceof String[]) {
        params.put(name , (String[])value);
      }else if(value instanceof String) {
        params.put(name , new String[] {(String)value});
      }else {
        params.put(name , new String[] {String.valueOf(value)});
      }
    }
  }
}

