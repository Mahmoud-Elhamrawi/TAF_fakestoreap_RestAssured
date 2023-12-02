package Cart;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteCart {


    @Test
    public void deleCart(){

        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .pathParam("cartID" ,"6")
                .when().delete("/{cart}/{cartID}");

        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,200);
        Assert.assertEquals(res.body().jsonPath().getInt("id") ,6);

    }
}
