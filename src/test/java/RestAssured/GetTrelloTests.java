package RestAssured;

import OKHttpAndRetrofitAndBaseTest.BaseTest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetTrelloTests extends BaseTest {

    @Test
    public void testGET(){
        RestAssured.baseURI = "https://api.trello.com/";
        given().log().all().queryParam("key", key).queryParam("token",token)
                .when().get("1/boards/" + id).then().log().all().assertThat().statusCode(200);
    }

}
