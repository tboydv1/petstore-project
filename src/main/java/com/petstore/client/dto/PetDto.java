package com.petstore.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petstore.model.PetSex;
import com.petstore.model.PetTypes;
import com.petstore.model.Store;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

public class PetDto {

    private Integer Id;
    private String name;
    private String breed;
    private PetTypes types;
    private PetSex sex;
    private Integer age;
    private Date birthDate;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public PetTypes getTypes() {
        return types;
    }

    public void setTypes(PetTypes types) {
        this.types = types;
    }

    public PetSex getSex() {
        return sex;
    }

    public void setSex(PetSex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PetDto{");
        sb.append("Id=").append(Id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", breed='").append(breed).append('\'');
        sb.append(", types=").append(types);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}
