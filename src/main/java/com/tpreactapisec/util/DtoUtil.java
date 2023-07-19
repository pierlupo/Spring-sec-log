package com.tpreactapisec.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoUtil {

    public <T, D> D convertToDto(T entity, D dto, Class<D> dtoClass) {
        return new ModelMapper().map(entity, dtoClass);
    }

    public <D, T> T convertToEntity(D dto, T entity, Class<T> entityClass) {
        return new ModelMapper().map(dto, entityClass);
    }
}
