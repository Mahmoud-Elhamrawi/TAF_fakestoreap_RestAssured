package Cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import seialization.addToCart;
import seialization.productsDeatils;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AddToCart {

//using JSON OBJECT
    @Test
    public void addCart()
    {
        JSONObject data = new JSONObject();
        data.put("userId","5");
        data.put("date","2020-02-03");
        data.put("products","[{productId:5,quantity:1},{productId:1,quantity:5}]");



        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .contentType(ContentType.JSON)
                .body(data.toString())

                .when().post("/{cart}");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode() ,200);

    }

/*
    //USING Serialization
    @Test
    public void cartAdd() throws JsonProcessingException {

        productsDeatils pd = new productsDeatils();

        addToCart ac = new addToCart();
        ac.setUserId(5);
        ac.setDate("2020-02-03");



        ObjectMapper mapper = new ObjectMapper();
          String data =mapper.writeValueAsString(ac);


        Response resCart =given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .contentType(ContentType.JSON)
                .body(data)

                .when().post("/{cart}");

        resCart.prettyPrint();
        Assert.assertEquals(resCart.statusCode() ,200);


    }

*/



}
