package com.cgvsu.ATransform;

public interface DataList<T> {
    void add(ATransform at);
    void remove(int index);
    void remove(ATransform at);
    void set(int index, ATransform at);
    T get(int index);
}
