package com.annyw.pojo;
import java.util.List;
public class Page<T>{
    private int pageSize;
    private int totalSize; //Number of data entries
    private int totalCount;//Number of pages
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
    
    
    //Constructor for Page
    public Page(int pageSize, int totalSize, int totalCount, int currentPage, List<T> users) {
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.totalCount = totalSize==0?1:(int)Math.ceil(totalSize/(pageSize*1.0));
        
        this.currentPage = currentPage;
        this.preNum=currentPage-1<1?1:currentPage-1;
        this.nextNum=currentPage+1>totalCount?totalCount:currentPage+1;
        
        
        this.users = users;
    }
}

