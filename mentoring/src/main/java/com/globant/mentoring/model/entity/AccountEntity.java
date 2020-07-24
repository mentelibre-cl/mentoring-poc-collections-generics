package com.globant.mentoring.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "codeaccount", referencedColumnName = "id")
    private CodeAccountEntity codeAccount;
    @Column(name = "number", columnDefinition = "varchar(25)")
    private String number;
    @Temporal(TemporalType.DATE)
    @Column(name = "openingdate", columnDefinition = "date")
    private Date openingDate;
    @Column(name = "status", columnDefinition = "int")
    @ColumnDefault(value = "1")
    private int status;
}