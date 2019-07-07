package pl.sda.jp.miniblogw16.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

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

        String generatedJson = objectMapper.writeValueAsString(map);
        System.out.println(generatedJson);
    }

}
