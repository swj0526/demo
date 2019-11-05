package core.model.bean;

public class PagerBean {
    private  int totalCount;//总计数
    private  int pageSise;// 当前页面显示的条数
    private  int currentPage;//当前页
    private  int  totalPage;//总页数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSise() {
        return pageSise;
    }

    public void setPageSise(int pageSise) {
        this.pageSise = pageSise;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        int totalPage = (int) (Math.ceil(totalCount / pageSise)*1.0);
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
