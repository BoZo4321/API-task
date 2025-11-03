package RestAssured;

import OKHttpAndRetrofitAndBaseTest.BaseTest;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;
import utility.SaveBoardData;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PutTrelloTest extends BaseTest {

    @Test
    public void putTrello() throws IOException {

        String putResponse =
                given()
                        .log().all()
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                        "  \"name\": \"Kako god\",\n" +
                        "  \"desc\": \"Ovo pokusavam da promenim\"\n" +
                        "}")
                .when()
                        .put("1/boards/" + id)
                .then().
                        log().all().assertThat().statusCode(200)
                        .extract().response().asString();

        JsonPath js = new JsonPath(putResponse);
        String newName = js.getString("name");

        Assert.assertEquals(newName,"Kako god");
        assertThat(newName).isEqualTo("Kako god");

        SaveBoardData.save(id,"Kako god");
    }

}
