package RestAssured;

import OKHttpAndRetrofitAndBaseTest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pojoClass.Board;
import utility.SaveBoardData;
import java.io.IOException;

public class PostTrelloTest extends BaseTest {

    @Test
    public static void postBoard() throws IOException {

        Board nameTable = new Board();
        nameTable.setName("Tabla task123");

        String response = RestAssured
                .given()
                .queryParam("key", key)
                .queryParam("token", token)
                .header("Content-Type", "application/json")
                .body(nameTable)
                .when()
                .post("1/boards/")
                .then()
                .log().all()
                .assertThat().statusCode(200).extract().response().asString();

        JsonPath js = new JsonPath(response);
        String curentId = js.getString("id");

        SaveBoardData.save(curentId, nameTable.getName());
        System.out.println(curentId);
    }
}
