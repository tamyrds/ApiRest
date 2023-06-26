package restApi;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class VerbosTest {

    @Test
    public void deveSalvarUsuario(){
        RestAssured.given()
                .log().all()
                .contentType("application/json")
                    .body("{\"name\": \"Tamires\",\"age\": 29}")
                .when()
                    .post("https://restapi.wcaquino.me/users")
                .then()
                    .log().all()
                    .statusCode(201);
    }
    @Test
    public void deveInserirID(){
        RestAssured.given()
                    .log().all()
                    .contentType("application/json")
                .body("{\"id\": \"123asg\",\"name\": \"Tamires\",\"age\": 29}")
                .when()
                    .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.is(Matchers.notNullValue()))
                .body("name", Matchers.is("Tamires"))
                ;
    }
}
