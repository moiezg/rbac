package com.moiez.model;

public class Resource {

    private String resourceName;

    private String content;

    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String newContent, boolean overWrite) {
        this.content = overWrite ? newContent : content + "\n" + newContent;
    }
}
