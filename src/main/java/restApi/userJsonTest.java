package restApi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class userJsonTest {
    @Test
    public void deveValidaroPrimeiroNivel() {
        RestAssured.given()
                .when()
                .get("https://restapi.wcaquino.me/users/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.is(1))
                .body("name", Matchers.containsString("Silva"))
                .body("age", Matchers.greaterThan(18))
        ;
    }

    @Test
    public void devevalidarprimeiroNiveldeOutrasFormas() {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");

        //path
        Assert.assertEquals(new Integer(1), response.path("id"));

        //jsonpath
        JsonPath jpath = new JsonPath(response.asString());

        Assert.assertEquals(1, jpath.getInt("id"));

        //from
        int id = JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals(1, id);
    }

    @Test
    public void devevalidarSegundoNivel() {
        RestAssured.given()
                .when()
                .get("https://restapi.wcaquino.me/users/2")
                .then()
                .statusCode(200)
                .body("name", Matchers.containsString("Joaquina"))
                .body("endereco.rua", Matchers.is("Rua dos bobos"))

        ;
    }
        @Test
        public void devevalidarLista() {
            RestAssured.given()
                    .when()
                    .get("https://restapi.wcaquino.me/users/3")
                    .then()
                    .statusCode(200)
                    .body("filhos", Matchers.hasSize(2))
                    .body("filhos.name[0]", Matchers.is("Zezinho"))
                    .body("filhos.name[1]", Matchers.is("Luizinho"))
                    .body("filhos.name", Matchers.hasItems("Zezinho","Luizinho"))
;
        }

        @Test
        public void usuarioInexistente(){
            RestAssured.given()
                    .when()
                    .get("https://restapi.wcaquino.me/users/4")
                    .then()
                    .statusCode(404)
                    .body("error", Matchers.is("Usuário inexistente"))

        ;
        }
        @Test
    public void deveVerificarLista(){
            RestAssured.given()
                    .when()
                    .get("https://restapi.wcaquino.me/users")
                    .then()
                    .statusCode(200)
                    .body("", Matchers.hasSize(3))
                    .body("name", Matchers.hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
                    .body("age[1]", Matchers.is(25))
                    .body("filhos.name", Matchers.hasItem(Arrays.asList("Zezinho", "Luizinho")))
                    .body("salary", Matchers.contains(1234.5677f, 2500, null))
                    ;
        }
    }

