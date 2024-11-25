package com.mitocode.academia.service;

import java.util.List;

public interface IGenericCRUDService <T, ID>{
    T findById(ID id) throws Exception;
    List<T> findAll() throws Exception;
    T save(T t) throws Exception;
    void delete(ID id) throws Exception;
    T update(ID id, T t) throws Exception;
}
