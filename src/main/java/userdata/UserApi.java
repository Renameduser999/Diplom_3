package userdata;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static userdata.UserData.*;

public class UserApi {

    public static String accessToken;
    public static UserObject user;

    @Step("Создание пользователя")
    public static Response createUser(String email, String password, String name) {
        user = new UserObject(email, password, name);
        Response response =  given()
                .header("Content-type", "application/json")
                .body(user)
                .post(USER_REGISTER);

        accessToken = response.then()
                .extract().path("accessToken");
        System.out.println("Created user with accessToken: " + accessToken); // Логирование созданного юзера

        return response;
    }

    @Step("Логин пользователя")
    public static Response loginUser(String email, String password) {
        user = new UserObject(email, password);
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .post(USER_LOGIN);

        accessToken = response.then()
                .extract().path("accessToken");
        System.out.println("Logged in user with accessToken: " + accessToken); // Логирование авторизованного юзера

        return response;
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        if (accessToken != null) {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", accessToken)
                    .delete(USER_UPDATE_DELETE);
                    /*.then()
                    .log().all();*/
            System.out.println("Deleted user with accessToken: " + accessToken); // Логирование удаленного юзера
        }
    }
}
