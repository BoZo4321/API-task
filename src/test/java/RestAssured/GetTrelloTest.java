package RestAssured;

import OKHttpAndRetrofitAndBaseTest.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetTrelloTest extends BaseTest {

    @Test
    public void getBoard() throws IOException {

        System.out.println(id);

        given()
                .log().all()
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .get("1/boards/" + id)
                .then()
                .log().all()
                .assertThat().statusCode(200).body("name", equalTo("Tabla task123"));


    }


}
