package pl.sda.jp.miniblogw16.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hobby extends BaseEntity {

    @Column
    private String hobbyName;

}
