package com.grzegorznowakowski.sortingdemo.translation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Translation implements Serializable {

    @Id
    private Long id;

    @NaturalId
    private String name;

    private String body;
}
