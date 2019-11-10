package core.model.bean;

import java.util.List;


public class PageBean<T> {

    private int totalCount;//记录的总数
    private int totalPage;//总页码；
    private List<T> list;//每页的数据
    private int Page;//当前页码
    private int limit;//每页显示的记录数;
//计算总页
    private int totalPage() {
        if (totalCount % limit == 0) {
            return totalCount / limit;

        } else {
            return (totalCount / limit + 1);

        }

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
