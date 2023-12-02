package Productes;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateProd {
    public String id ;

    @Test
    public void createPrd()
    {

        JSONObject data =  new JSONObject();
         data.put("title","tv");
         data.put("price","1300");
         data.put("image","https://i.pravatar.cc");
         data.put("category","electronic");


        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("prd","products")
                .header("Content-Type","application/json")
                .body(data.toString())

                .when().post("/{prd}");

        id = res.body().jsonPath().get("id").toString();
         res.prettyPrint();

    }



    @Test(dependsOnMethods = "createPrd")
    public void getProd()
    {
        Response res2 = given().baseUri("https://fakestoreapi.com")
            .pathParam("prd","products")
            .pathParam("id",id)
                .when().get("/{prd}/{id}") ;
        Assert.assertEquals(res2.statusCode(),200);

        res2.prettyPrint();
    }
}
