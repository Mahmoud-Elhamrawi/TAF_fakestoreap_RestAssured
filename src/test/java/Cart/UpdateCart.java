package Cart;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateCart {


    @Test
    public void modify()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .pathParam("cartID","7")

                .when().put("/{cart}/{cartID}") ;

        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,200);

    }
}
