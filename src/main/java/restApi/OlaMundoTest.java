package restApi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OlaMundoTest {

    @Test
    public void testeOlaMundo(){
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
       Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
       Assert.assertTrue("O status code deveria ser 200", response.statusCode() ==200);

        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void devoConhecerNovosMetodosRest(){
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
        ValidatableResponse validacao = response.then();
        Assert.assertEquals(200, response.statusCode());

        RestAssured.get("https://restapi.wcaquino.me/ola").then().statusCode(200);

        given() // Pré-Condições
        .when() // Ação
            .get("https://restapi.wcaquino.me/ola")
        .then() // Assertivas
            .statusCode(200);
    }

    @Test
    public void devoConheceroHamcrest(){
        Assert.assertThat("Maria", Matchers.is("Maria"));
        Assert.assertThat(123, Matchers.isA(Integer.class));
        Assert.assertThat(123, Matchers.greaterThan(120));

        List<Integer> impares = Arrays.asList(1,3,5,7,9);

        Assert.assertThat(impares, Matchers.hasSize(5));
    }

    @Test
    public void devovalidarBody(){
        given() // Pré-Condições
                .when() // Ação
                .get("https://restapi.wcaquino.me/ola")
                .then() // Assertivas
                .statusCode(200)
                .body(Matchers.is("Ola Mundo!"));

    }
}
