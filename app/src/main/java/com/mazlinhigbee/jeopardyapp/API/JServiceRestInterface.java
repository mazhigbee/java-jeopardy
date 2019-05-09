package com.mazlinhigbee.jeopardyapp.API;

import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.Models.Clue;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * com.mazlinhigbee.jeopardyapp.API
 * Created by: mhigbee
 * Date: 4/21/19 Time: 9:54 PM
 */
public interface JServiceRestInterface {

    @GET("categories?count=100")
    Call<List<Category>> getJepCategories();

    @GET("clues")
    Call<List<Clue>> getCluesForCategory(@Query("category") Integer categoryId);


}
