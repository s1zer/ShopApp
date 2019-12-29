package com.michal.shopapp.service;

public interface GenericService<T> {

    public T getDefinition(Long id);

    public T saveDefinition(T t);

    public T putDefinition(T t, Long id);

    public T patchDefinition(T t, Long id);

    public void removeDefinition(Long id);
}
