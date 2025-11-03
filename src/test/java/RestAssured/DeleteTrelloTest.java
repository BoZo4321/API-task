package RestAssured;

import OKHttpAndRetrofitAndBaseTest.BaseTest;
import org.testng.annotations.Test;
import utility.SaveBoardData;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DeleteTrelloTest extends BaseTest {

    @Test
    public void deleteBoard() throws IOException {

        given()
                .log().all().queryParam("key",key)
                .queryParam("token",token)
                .when()
                .delete("1/boards/" + id )
                .then()
                .log().all().assertThat().statusCode(200);

        SaveBoardData.save(null, null);

    }

}