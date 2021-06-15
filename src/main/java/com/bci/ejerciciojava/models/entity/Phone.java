package com.bci.ejerciciojava.models.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phones")
@Getter
@Setter
@ToString
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPhoneSeq")
    @SequenceGenerator(name = "idPhoneSeq")
    @Column(name="id")
    private Long id;
    @Column(name="number_phone")
    private String numberPhone;
    @Column(name="city_code")
    private String cityCode;
    @Column(name="country_code")
    private String countryCode;

}
