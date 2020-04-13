package com.deepbluebi.basic.filter;

import com.deepbluebi.basic.common.constant.SysConst;
import com.deepbluebi.basic.common.utils.RegexUtil;
import com.deepbluebi.basic.service.context.PageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器，过滤出分页的参数，然后执行完方法之后，移除数据，防止不安全
 *
 * @author cj
 */
@Slf4j
@Order(1)
@WebFilter(urlPatterns = "/*", filterName = "pageFilter")
public class PageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        PageContext.setPageNum(getPageNum(httpRequest));
        PageContext.setPageSize(getPageSize(httpRequest));

        try {
            chain.doFilter(request, response);
        } finally {
            /**
             *  使用完Threadlocal，将其删除。使用finally确保一定将其删除
             */
            PageContext.removePageNum();
            PageContext.removePageSize();
        }
    }

    /**
     * 获得pager.offset参数的值
     *
     * @param request
     * @return
     */
    protected int getPageNum(HttpServletRequest request) {
        int pageNum = 1;
        /**
         * 参数可以传pageNum、page 表示分页
         */
        Integer pageNumi = convert(request.getParameter("pageNum"));
        Integer pagei = convert(request.getParameter("page"));
        if (pageNumi != 0) {
            pageNum = pageNumi;
        }
        if (pagei != 0) {
            pageNum = pagei;
        }

        return pageNum;
    }

    /**
     * 设置默认每页大小
     *
     * @return
     */
    protected Integer getPageSize(HttpServletRequest request) {
        int pageSize = SysConst.PAGE_SIZE;
        /**
         * 参数可以传pageSize、limit 标识每页大小
         */
        Integer pageSizei = convert(request.getParameter("pageSize"));
        Integer limiti = convert(request.getParameter("limit"));
        if (pageSizei != 0) {
            pageSize = pageSizei;
        }
        if (limiti != 0) {
            pageSize = limiti;
        }

        return pageSize;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.info("pageFilter注册成功-------------------------------------------------------");
    }

    private Integer convert(String s) {
        if (s != null && RegexUtil.isNonnegativeInt(s)) {
            return Integer.parseInt(s);
        }
        return 0;
    }
}
