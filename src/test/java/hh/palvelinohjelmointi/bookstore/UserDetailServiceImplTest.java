package hh.palvelinohjelmointi.bookstore;

import hh.palvelinohjelmointi.bookstore.web.UserDetailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing that context is creating userDetailService
 */


@RunWith(SpringRunner.class)
@SpringBootTest

public class UserDetailServiceImplTest {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userDetailService).isNotNull();
    }
}
