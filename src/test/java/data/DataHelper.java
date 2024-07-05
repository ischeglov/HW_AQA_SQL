package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getRandomVerificationCode() {
        return new VerificationCode(FAKER.numerify("######"));
    }

    private static String getLogin() {
        return FAKER.name().username();
    }

    private static String getPassword() {
        return FAKER.internet().password();
    }

    public static AuthInfo getRandomUser() {
        return new AuthInfo(getLogin(), getPassword());
    }
}
