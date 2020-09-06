package com.petstore.service.pet;

import com.petstore.client.dto.PetDto;
import com.petstore.exception.PetNotFoundException;
import com.petstore.model.Pet;
import com.petstore.model.PetSex;
import com.petstore.model.PetTypes;
import com.petstore.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class PetServiceTest {

    @Mock
    PetRepository petMockRepository;

    @InjectMocks
    PetService petService;

    @Autowired
    PetRepository petRepository;

    Logger log = Logger.getLogger(getClass().getName());


    PetDto dto;
    Pet testPet;

    @BeforeEach
    void setUp() {
        petService = new PetServiceImpl();

        testPet = new Pet();
        dto = new PetDto();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {

        when(petService.save(dto)).thenReturn(new Pet());
        petService.save(dto);

        verify(petMockRepository, times(1)).save(testPet);
    }

    @Test
    void findById() {

        when(petService.findById(11)).thenReturn(Optional.of(testPet));
        petService.findById(11);

        verify(petMockRepository, times(1)).findById(11);
    }

    @Test
    void update() throws PetNotFoundException {

        when(petService.update(dto)).thenReturn(testPet);
        petService.update(dto);

        verify(petMockRepository, times(1)).save(testPet);
    }

    @Test
    void delete() {

        doNothing().when(petMockRepository).deleteById(11);
        petMockRepository.deleteById(11);

        verify(petMockRepository, times(1)).deleteById(11);
    }

    @Test
    void findAll() {
        List<Pet> petList = new ArrayList<>();
        when(petService.findAll()).thenReturn(petList);
        petService.findAll();

        verify(petMockRepository, times(1)).findAll();
    }

    @Test
    void whenPetUpdateMethodIsCalled_thenUpdateOnlyProvidedFields(){

        Pet pet = new Pet();
        pet.setName("Bloomy");
        pet.setAge(60);
        pet.setBreed("German");
        pet.setSex(PetSex.MALE);
        pet.setTypes(PetTypes.DOG);

        pet = petRepository.save(pet);
        log.info("Pet fields after save ---> {}"+ pet);
        assertThat(pet).isNotNull();
        assertThat(pet.getName()).isEqualTo("Bloomy");

        //update Name field
        pet.setName("Grupal");

        Optional<Pet> savedPet = petRepository.findById(pet.getId());

//        petRepository.save(pet, "   ");
    }

}