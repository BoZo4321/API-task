package utility;

import pojoClass.Board;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiHttps {

    @POST("1/boards/")
    Call<Board> postBoardRetrofit(
            @Query("key") String key,
            @Query("token") String token,
            @Query("name") String name
    );

    @GET("1/boards/{id}")
    Call<Board> getBoardByIdRetrofit(
            @Path("id") String boardId,
            @Query("key") String key,
            @Query("token") String token
    );

    @PUT("1/boards/{id}")
    Call<Board> putBoardRetrofit(
            @Path("id") String boardId,
            @Query("key") String key,
            @Query("token") String token,
            @Query("name") String name
    );

    @DELETE("1/boards/{id}")
    Call<Board> deleteBoardRetrofit(
            @Path("id") String boardId,
            @Query("key") String key,
            @Query("token") String token
    );

}
