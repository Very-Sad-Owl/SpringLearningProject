package com.epam.practice.teashop_spring.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tea {
    //TODO: add localization support for messages
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String teaTitle;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String brand;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String country;


    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String fragranceBaseNotes;

    private String description;

    private String filename;

    @NotNull(message = "Пожалуйста заполните поле")
    private Integer price;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String volume;

    @NotBlank(message = "Пожалуйста заполните поле")
    @Length(max = 255)
    private String type;
}