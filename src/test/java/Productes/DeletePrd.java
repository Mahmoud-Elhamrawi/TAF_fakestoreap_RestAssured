package Productes;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePrd
{

//https://fakestoreapi.com/products/6
    @Test
    public void delPrd()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("category","products")
                .pathParam("prdID","6")


                .when().delete("/{category}/{prdID}");

        res.prettyPrint();
    }
}
