package pl.sda.jp.miniblogw16.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private Collection<Integer> numbers;
    @JsonIgnore
    private String privateInfo;
}
