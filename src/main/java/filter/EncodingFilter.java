package filter;

import java.io.IOException;

 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
/**
 * Класс служит для проверки кодировки данных переданных через запрос.
 * @version 1.0
 * @autor Trusov Anton
 */
//@WebFilter(filterName = "encodingFilter", urlPatterns = { "/*" })
public class EncodingFilter implements Filter {
  /**
   * Конструктор класса EncodingFilter.
   */
  public EncodingFilter() {
  }

  /**
   * Перегрузка функции init для классов Filter
   * @param fConfig - конфигурация
   * @throws ServletException
   */
  @Override
  public void init(FilterConfig fConfig) throws ServletException {
 
  }
  /**
   * Перегрузка функции init для классов Filter
   */
  @Override
  public void destroy() {
 
  }
  /**
   * Функция для проверки кодировки 
   * @param request - запрос поступивший к сервлету
   * @param response - ответ отправляющий сервлетом
   * @param chain - цепь фильтров
   * @throws ServletException
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
      request.setCharacterEncoding("UTF-8");

      chain.doFilter(request, response);
  }
 
}