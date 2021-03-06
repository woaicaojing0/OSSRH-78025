package com.deepbluebi.basic.service.context;

/**
 * 保存分页的参数（pageNum、pageSize）
 *
 * @author cj
 */
public class PageContext {
    /**
     * 定义两个threadLocal变量：pageNum和pageSize
     * 保存第几页
     */
    private static ThreadLocal<Integer> pageNum = new ThreadLocal<Integer>();
    /**
     * 保存每页记录条数
     */
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

    /**
     * pageNum ：get、set、remove
     */
    public static Integer getPageNum() {
        Integer pn = pageNum.get();
        if (pn == null) {
            return 0;
        }
        return pn;
    }

    public static void setPageNum(int pageNumValue) {
        pageNum.set(pageNumValue);
    }

    public static void removePageNum() {
        pageNum.remove();
    }

    /**
     * pageSize ：get、set、remove
     */
    public static Integer getPageSize() {
        Integer ps = pageSize.get();
        if (ps == null) {
            return 0;
        }
        return ps;
    }

    public static void setPageSize(int pageSizeValue) {
        pageSize.set(pageSizeValue);
    }

    public static void removePageSize() {
        pageSize.remove();
    }
}
