package com.deepbluebi.basic.bean.base;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 * @ClassName: ResponsePageListBean
 * @Description:TODO(分页返回数据对象)
 * @author: wangshuai
 * @date: 2017年7月14日 上午11:11:31
 * @Copyright: 2017 www.deepbluebi.com Inc. All rights reserved.
 */
public class ResponsePageListBean<T> implements Serializable {

    private static final long serialVersionUID = -4241866631851550265L;
    /**
     * 返回数据
     */

    private List<T> data;
    /**
     * 请求的次数
     */
    private Long draw;
    /**
     * 总记录数
     */
    private Long recordsTotal;
    /**
     * 过滤后总记录数
     */
    private Long recordsFiltered;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
