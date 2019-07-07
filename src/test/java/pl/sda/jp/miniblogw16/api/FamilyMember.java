package pl.sda.jp.miniblogw16.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FamilyMember {
    private FamilyMemberType type;
    private String name;
}
