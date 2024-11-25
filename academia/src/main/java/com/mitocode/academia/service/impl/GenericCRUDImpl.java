package com.mitocode.academia.service.impl;

import com.mitocode.academia.exception.ModelNotFoundException;
import com.mitocode.academia.repo.IGenericRepo;
import com.mitocode.academia.service.IGenericCRUDService;

import java.lang.reflect.Method;
import java.util.List;

public abstract class GenericCRUDImpl<T, ID> implements IGenericCRUDService <T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {

        // Usamos la reflexión para obtener la clase de la entidad
        Class<?> clazz = t.getClass();
        String className = clazz.getSimpleName(); // Obtenemos el nombre de la clase (Product, Client, etc.)

        // Construimos el nombre del método setId dinámicamente
        String methodName = "setId" + className; // Asumimos que el método es setIdProduct, setIdClient, etc.

        // Usamos la reflexión para obtener el método setId correspondiente
        Method setIdMethod = clazz.getMethod(methodName, id.getClass()); // id.getClass() obtiene el tipo de la variable id

        // Invocamos el método setId pasando el id como parámetro
        setIdMethod.invoke(t, id); // Esto establece el id en el objeto t de tipo T

        // Verificamos si el objeto con el id proporcionado existe en la base de datos
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));

        // Guardamos la entidad actualizada en la base de datos
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: "+ id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: "+ id));
        getRepo().deleteById(id);
    }
}
