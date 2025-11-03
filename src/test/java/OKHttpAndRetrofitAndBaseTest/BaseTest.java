package OKHttpAndRetrofitAndBaseTest;

import io.restassured.RestAssured;
import okhttp3.OkHttpClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pojoClass.Board;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.ApiHttps;
import utility.LoadBoardData;

import java.io.IOException;

public class BaseTest {



    protected final static String BASE_URL = "https://api.trello.com/";

    /******
     * TYPE HERE YOUR TRELLO API KEY
     * AND TRELLO API TOKEN
     */
    protected static String key = ""; // <--- HERE KEY
    protected static String token = "";// <--- HERE TOKEN

    protected String id;
    protected String boardName;

    Board board = new Board();

    /*******************************************************
     From down there its comands for Retrofit
     ******************************************************/

    protected static Retrofit retrofit;
    protected static ApiHttps api;

    /*******************************************************
     From down there its comands for OkHttp
     ******************************************************/

    protected OkHttpClient client;

    /*******************************************************
     * Retrofit
     ******************************************************/

    @BeforeSuite
    public void beforeAllTestsRetrofit(){

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitTrelloTest.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiHttps.class);

    }

    @BeforeMethod
    public void beforeTestLoadDataFromBoardRetrofit() throws IOException {
        board = LoadBoardData.load();
        id = board.getId();
        boardName = board.getName();
    }

    /**************************
     * OkHttp
     *************************/

    @BeforeSuite
    public void beforeAllTestsOkHttp(){
        client = new OkHttpClient();
    }

    @BeforeMethod
    public void beforeTestLoadDataFromBoardOkHttp(){
        // It doesn't have to exist because beforeTestLoadDataFromBoardRetrofit will do the same thing
    }


    /**************************
     * RESTASURED
     *************************/
    @BeforeMethod
    public void beforeTestLoadDataFromBoardRest(){
        // It doesn't have to exist because beforeTestLoadDataFromBoardRetrofit will do the same thing
    }

    @BeforeMethod
    public void beforeTest(){
        RestAssured.baseURI = "https://api.trello.com/";
    }

}
