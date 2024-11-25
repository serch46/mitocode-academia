package com.mitocode.academia.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

// La anotación @Component convierte esta clase en un bean manejado por el contenedor de Spring.
// Esto permite que la clase sea inyectada automáticamente en otras clases donde sea necesaria.
@Component

// @RequiredArgsConstructor es una anotación de Lombok que genera un constructor para todas las
// propiedades finales (final). En este caso, generará un constructor que inicializa el
// atributo `applicationContext`.
@RequiredArgsConstructor
public class MapperUtil {

    // Inyecta automáticamente el ApplicationContext de Spring, que es el contenedor que
    // gestiona todos los beans definidos en la aplicación. Esto se utiliza aquí para
    // acceder dinámicamente a instancias de `ModelMapper`.
    private final ApplicationContext applicationContext;

    // Método para mapear una lista de objetos de un tipo (`sourceList`) a otro tipo (`targetClass`).
    // Permite especificar opcionalmente un calificador (`mapperQualifier`) para elegir un bean
    // específico de `ModelMapper`.
    public <S, T> List<T> mapList(List<S> sourceList, Class<T> targetClass, String... mapperQualifier) {

        // Obtiene una instancia de ModelMapper (según el calificador opcional).
        ModelMapper modelMapper = getModelMapper(mapperQualifier);

        // Realiza el mapeo de cada elemento de la lista fuente al tipo de destino
        // y devuelve la lista mapeada.
        return sourceList
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

    // Método para mapear un objeto individual (`source`) a otro tipo (`targetClass`).
    // También permite especificar opcionalmente un calificador para elegir un bean específico
    // de ModelMapper.
    public <S, T> T map(S source, Class<T> targetClass, String... mapperQualifier) {

        // Obtiene una instancia de ModelMapper.
        ModelMapper modelMapper = getModelMapper(mapperQualifier);

        // Realiza el mapeo del objeto fuente al tipo de destino y devuelve el resultado.
        return modelMapper.map(source, targetClass);
    }

    // Método privado para obtener una instancia de `ModelMapper` desde el contenedor
    // de Spring. Puede utilizar un calificador opcional (`mapperQualifier`) para seleccionar
    // un bean específico.
    private ModelMapper getModelMapper(String... mapperQualifier) {

        // Si no se proporciona un calificador, utiliza un bean llamado "defaultMapper".
        if (mapperQualifier.length == 0 || mapperQualifier[0] == null || mapperQualifier[0].isEmpty()) {
            return applicationContext.getBean("defaultMapper", ModelMapper.class);
        } else {
            // Si se proporciona un calificador, obtiene el bean correspondiente.
            return applicationContext.getBean(mapperQualifier[0], ModelMapper.class);
        }
    }
}
