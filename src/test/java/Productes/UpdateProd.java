package Productes;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateProd {

    @Test
    public void getPrd7()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("category","products")
                .pathParam("prdID","7")

                .when().get("/{category}/{prdID}") ;
        res.prettyPrint();
    }



   @Test(dependsOnMethods = "getPrd7")
   public void modify()
   {
       JSONObject jo = new JSONObject();
       jo.put("title","phone");
       jo.put("price","5000");
       jo.put("description","cheap");
       jo.put("image","https://i.pravatar.cc");
       jo.put("category","smart");

       Response res = given().baseUri("https://fakestoreapi.com")
               .header("Content-Type","application/json")
               .pathParam("category","products")
               .pathParam("prdID","7")
               .body(jo.toString())

               .when().put("/{category}/{prdID}") ;
       Assert.assertEquals(res.statusCode() , 200);
       Assert.assertEquals("phone" ,res.body().jsonPath().get("title") ,"phone id changed");
       System.out.println((String) res.body().jsonPath().get("title"));

       res.prettyPrint();
   }


}
