package Users;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AddUser {


    @Test
    public void addUser() throws FileNotFoundException {


        File file = new File("./src\\test\\resources\\data.json");
        FileReader fileReader = new FileReader(file);
        JSONObject jsonObject = new JSONObject(fileReader) ;


        Response res = given().baseUri("https://fakestoreapi.com")
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .pathParam("user","users")
                .body(jsonObject)

                .when().post("/{user}") ;

        res.prettyPrint();
        Assert.assertEquals(res.statusCode() , 200);

    }



}
