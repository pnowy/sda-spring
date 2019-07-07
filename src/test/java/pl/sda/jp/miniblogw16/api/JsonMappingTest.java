package pl.sda.jp.miniblogw16.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import pl.sda.jp.miniblogw16.user.EditUserForm;

import java.io.IOException;
import java.util.*;

@Slf4j
public class JsonMappingTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void shouldGenerateSimpleJson() throws JsonProcessingException {
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

        String generatedJson = objectMapper.writeValueAsString(map);
//        System.out.println(generatedJson);

        String addressJson = objectMapper.writeValueAsString(address);
        System.out.println(addressJson);
    }

    @Test
    public void shouldSerializeUserEditForm() throws IOException {
        EditUserForm editUserForm = new EditUserForm();
        editUserForm.setId(1L);
        editUserForm.setFirstName("Jan");
        editUserForm.setLastName("Kowalski");
        editUserForm.setEmail("kowalski@gmail.com");
        editUserForm.setRoles(Arrays.asList("ROLE_USER"));

        String userJson = objectMapper.writeValueAsString(editUserForm);
        System.out.println(userJson);


        EditUserForm formFromJson =
                objectMapper.readValue(userJson, EditUserForm.class);
        System.out.println(formFromJson);
    }
}
