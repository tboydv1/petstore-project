package com.petstore.service.pet;

import com.petstore.client.dto.PetDto;
import com.petstore.model.Pet;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PetMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePetFromDto(PetDto petdto, @MappingTarget Pet petEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void upateDtoFromPet(Pet petEntity, @MappingTarget PetDto petDto);
}
