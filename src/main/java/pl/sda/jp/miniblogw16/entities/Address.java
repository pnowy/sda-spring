package pl.sda.jp.miniblogw16.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Address extends BaseEntity {

    public Address(String street) {
        this.street = street;
    }

    @Column
    private String street;

}
