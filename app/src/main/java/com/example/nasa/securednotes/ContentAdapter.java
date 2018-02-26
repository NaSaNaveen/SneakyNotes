package com.example.nasa.securednotes;


public class ContentAdapter
{
    public ContentAdapter()
    {

    }

    public ContentAdapter(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String content;
}
