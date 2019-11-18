package com.joy.dto;

import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContentAssert;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class UserTest {

    private static final String FILE_PATH = "src/test/resources/json/user.xml";

    @Autowired
    private JacksonTester<User> json;

    private static User expectedUser;

    @BeforeAll
    public static void setUp() {
        expectedUser = User.builder().userName("sunnyg").email("sunny.g@email.com")
                .firstName("Sunny")
                .lastName("G").build();
    }

    @Test
    public void serializeJson() throws Exception {
        final JsonContentAssert jsonContentAssert = assertThat(this.json.write(expectedUser));
        jsonContentAssert.isEqualToJson(readAllBytes(get(FILE_PATH)));
    }

    @Test
    public void deserializeJson() throws Exception {
        final String actual = new String(readAllBytes(get(FILE_PATH)));
        final User user = this.json.parseObject(actual);
        assertThat(user).isEqualTo(expectedUser);
    }
}
