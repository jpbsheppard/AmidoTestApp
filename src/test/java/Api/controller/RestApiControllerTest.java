package Api.controller;


import Api.model.User;
import Api.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService userService;

    @Before
    public void setUp(){
        given(this.userService.findById(1)).willReturn(new User(1, "Francesco", "fraPass"));
    }

    @Test
    public void getUserTest(){
        ResponseEntity<User> user = this.restTemplate.getForEntity("/api/user/{id}", User.class, 1);
        assertThat("Http status success", user.getStatusCode().is2xxSuccessful());
        assertThat(user.getBody().getName(), equalTo("Francesco"));
    }
}