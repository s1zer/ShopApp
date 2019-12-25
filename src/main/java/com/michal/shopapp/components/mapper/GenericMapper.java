package com.michal.shopapp.components.mapper;

public interface GenericMapper<E, D> {

    public E convertToEntity(D d);

    public D convertToDto(E e);
}
