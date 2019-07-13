package pl.sda.jp.miniblogw16.exampletests;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sda.jp.miniblogw16.GreetingController;
import pl.sda.jp.miniblogw16.GreetingService;
import pl.sda.jp.miniblogw16.config.BasicSecurityConfig;
import pl.sda.jp.miniblogw16.user.EditUserForm;

import javax.sql.DataSource;
import java.util.Arrays;

@WebMvcTest(value = GreetingController.class,
excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = BasicSecurityConfig.class
))
@RunWith(SpringRunner.class)
public class MyWebMvcTest {

    @MockBean
    private GreetingService greetingService; // odpowiednik Mockito.mock(GreetingService.class)

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private DataSource dataSource;

    @Test
    @WithMockUser("admin")
    public void shouldReturnGreeting() throws Exception {
        EditUserForm form = new EditUserForm();
        form.setRoles(Arrays.asList("A", "B"));
        form.setEmail("kowal@gmail.com");
        Mockito.when(greetingService.greet()).thenReturn(form);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().string("HelloTest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("kowal@gmail.com")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.is", Matchers.notNullValue()))
        ;

    }
}
