package com.abraao.ribeiro.account.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    @Column(name = "address_zipCode", nullable = false)
    private String zipCode;

    @Column(name = "address_place", nullable = false)
    private String place;

    @Column(name = "address_number", nullable = false)
    private String number;

    @Column(name = "address_complement", nullable = false)
    private String complement;

    @Column(name = "address_district", nullable = false)
    private String district;

    @Column(name = "address_city", nullable = false)
    private String city;

    @Column(name = "address_state", nullable = false)
    private String state;
}
