package com.example.Assignment2Bun.model;

public interface Subject {
    public void attach(com.example.Assignment2Bun.model.Observer o);
    public void detach(Observer o);
    public void notifyUpdate(String m);
}
