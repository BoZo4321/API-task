package OKHttpAndRetrofitAndBaseTest;

import io.restassured.path.json.JsonPath;
import okhttp3.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;
import utility.SaveBoardData;

import java.io.IOException;

public class OkHttpTest extends BaseTest {

//    private final static String BASE_URL = "https://api.trello.com/";
//    private final static String key = System.getenv("TRELLO_API_KEY");
//    private final static String token = System.getenv("TRELLO_API_TOKEN");

    @Test()
    public void postOkHttp() throws IOException {

        String url = BASE_URL + "1/boards/" + "?key=" + key + "&token=" + token;
        String curentName = "Tabla task123";

        RequestBody body = new FormBody.Builder()
                .add("name", curentName)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        JsonPath js = new JsonPath(responseBody);
        String curentId = js.getString("id");

        Assert.assertEquals(response.code(), 200);
        Assert.assertEquals(js.getString("name"),curentName);

        SaveBoardData.save(curentId,curentName);
    }

    @Test(dependsOnMethods = "postOkHttp")
    public void getOkHttp() throws IOException {

        String url = BASE_URL + "1/boards/" + id + "?key=" + key + "&token=" + token;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertEquals(response.code(), 200);
        System.out.println(response.body().string());
    }

    @Test(dependsOnMethods = "getOkHttp")
    public void putOkHttp() throws IOException {

        String newName = "New name 123";

        String url = BASE_URL + "1/boards/" + id + "?key=" + key + "&token=" + token;

        RequestBody body = new FormBody.Builder()
                .add("name", newName)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();

        JsonPath js = new JsonPath(response.body().string());

        Assert.assertEquals(response.code(), 200);
        Assertions.assertThat(js.getString("name")).isEqualTo(newName);

        SaveBoardData.save(id,newName);
    }

    @Test(dependsOnMethods = "putOkHttp")
    public void deleteOkHttp() throws IOException {

        String url = BASE_URL + "1/boards/" + id + "?key=" + key + "&token=" + token;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 200);

        SaveBoardData.save(null,null);
    }
}


