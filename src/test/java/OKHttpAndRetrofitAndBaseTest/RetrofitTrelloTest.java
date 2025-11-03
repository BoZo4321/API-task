package OKHttpAndRetrofitAndBaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojoClass.Board;
import retrofit2.Call;
import retrofit2.Response;
import utility.SaveBoardData;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrofitTrelloTest extends BaseTest {

//    public final static String BASE_URL = "https://api.trello.com/";
//    private final static String key = System.getenv("TRELLO_API_KEY");//System.getenv("TRELLO_API_KEY");
//    private final static String token = System.getenv("TRELLO_API_TOKEN");

    @Test
    public void postTrelloRetrofit() throws IOException {
        Board nameTable = new Board();
        nameTable.setName("Tabla task123");

        Call<Board> postCall = BaseTest.api.postBoardRetrofit(key, token,nameTable.getName());
        Response<Board> response = postCall.execute();

        Assert.assertEquals(response.code(), 200);

        assert response.body() != null;
        String curentId = response.body().getId();
        SaveBoardData.save(curentId, nameTable.getName());

        System.out.println(nameTable.getName());
        System.out.println("Status code: " + response.code());
    }

    @Test(dependsOnMethods = "postTrelloRetrofit")
    public void getTrelloRetrofit() throws IOException {

        Call<Board> getCall = BaseTest.api.getBoardByIdRetrofit(id, key, token);
        Response<Board> response = getCall.execute();

        Assert.assertEquals(response.code(), 200);
        assert response.body() != null;
        assertThat(response.body().getName()).isEqualTo("Tabla task123");

        System.out.println(response.body().getName());
    }


    @Test(dependsOnMethods = "getTrelloRetrofit")
    public void putTrelloRetrofit() throws IOException {

        Call<Board> putCall = BaseTest.api.putBoardRetrofit(id, key, token, "New name Retrofit");
        Response<Board> response = putCall.execute();

        Assert.assertEquals(response.code(), 200);
        assertThat(response.body().getName()).isEqualTo("New name Retrofit");

        SaveBoardData.save(id, "New name Retrofit");
        System.out.println(response.body().getName());
    }

    @Test(dependsOnMethods = "putTrelloRetrofit")
    public void deleteTrelloRetrofit() throws IOException {

        Call<Board> deleteCall = BaseTest.api.deleteBoardRetrofit(id, key, token);
        Response<Board> response = deleteCall.execute();

        Assert.assertEquals(response.code(), 200);

        SaveBoardData.save(null, null);
    }
}
