package org.lirio.test.springboot.app.mappers;

public interface EntityDtoMapper <E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
