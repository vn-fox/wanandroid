package com.wng.wanandroid.model;

import java.util.List;

public class CategoryDetailData {

    private List<CategoryDetailData> children;
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private int visible;

    public List<CategoryDetailData> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDetailData> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
