package pl.sda.jp.miniblogw16.exampletests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.jp.miniblogw16.api.Address;
import pl.sda.jp.miniblogw16.api.FamilyMember;
import pl.sda.jp.miniblogw16.api.FamilyMemberType;
import pl.sda.jp.miniblogw16.config.JacksonConfiguration;
import pl.sda.jp.miniblogw16.user.EditUserForm;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class MyJsonTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JacksonTester<EditUserForm> editTester;

    @Autowired
    private JacksonTester<Map> mapTester;

    @Test
    public void shouldSerializeJson() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("example", "key");

        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);
    }

    @Test
    public void shouldGenerateJsonForEditUserForm() throws IOException {
        Map<String, Object> map = new TreeMap<>();
        map.put("name", "Jan");
        map.put("age", 25);
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");
        map.put("roles", roles);

        List<FamilyMember> familyMembers
                = Arrays.asList(
                new FamilyMember(FamilyMemberType.BROTHER, "Janusz"),
                new FamilyMember(FamilyMemberType.MOTHER, "Halina")
        );
        map.put("familyMembers", familyMembers);

        Address address = Address.builder()
                .street("Sezamkowa")
                .numbers(Arrays.asList(1, 5))
                .privateInfo("Very private info")
                .build();
        map.put("address", address);


        JsonContent<Map> content = mapTester.write(map);
        System.out.println(content.getJson());
        assertThat(content)
                .hasJsonPathValue("$.address.street");
//        assertThat(content)
//                .hasJsonPathValue("$.address.houseNumber");



    }
}
