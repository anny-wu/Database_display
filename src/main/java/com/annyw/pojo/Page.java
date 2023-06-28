package com.annyw.pojo;
import java.util.List;
public class Page<T>{
    private int pageSize;
    private int pageCount;
    private int totalSize;
    private int totalCount;
    private int preNum;
    private int nextNum;
    private int currentPage;
    private List<T> users;
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public int getTotalSize() {
        return totalSize;
    }
    
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    
    public int getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    public int getPreNum() {
        return preNum;
    }
    
    public void setPreNum(int preNum) {
        this.preNum = preNum;
    }
    
    public int getNextNum() {
        return nextNum;
    }
    
    public void setNextNum(int nextNum) {
        this.nextNum = nextNum;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currPage) {
        this.currentPage = currentPage;
    }
    
    public List<T> getUsers() {
        return users;
    }
    
    public void setUsers(List<T> users) {
        this.users = users;
    }
    
    
    public Page(int pageSize, int pageCount, int totalSize, int currentPage, List<T> users) {
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalSize = totalSize;
        this.totalCount = (int) Math.ceil(totalSize/(pageSize*1.0));
        
        this.preNum=pageCount-1<1?1:pageCount-1;
        this.nextNum=pageCount+1>totalCount?totalSize:pageCount+1;
        
        this.currentPage = currentPage;
        this.users = users;
    }
}

