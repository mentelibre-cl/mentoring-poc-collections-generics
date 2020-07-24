package com.globant.mentoring.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "code_account")
public class CodeAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;
    @Column(name = "description", columnDefinition = "varchar(150)")
    private String description;
    @Column(name = "status", columnDefinition = "int")
    @ColumnDefault(value = "1")
    private int status;
}