package Cart;

import com.sun.net.httpserver.Request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllCarts {




    @Test
    public void allCarts()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .when().get("/{cart}");

        res.prettyPrint() ;

        Assert.assertEquals(res.statusCode() ,200);
        System.out.println(res.body().jsonPath().getString("id"));
        Assert.assertEquals(res.body().jsonPath().getString("products[0].quantity[0]") ,"4" );

    }





    @DataProvider(name = "singleCartIDS")
    public Object[]cartIDS()
    {
       Object ISD[] = {1,2,3}   ;
       return ISD;
    }




    @Test(dataProvider ="singleCartIDS")
    public void singleCart(int id)
    {

        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .pathParam("cartID",id)
                .when().get("/{cart}/{cartID}") ;

        res.prettyPrint();
        Assert.assertEquals(res.statusCode() ,200);
        Assert.assertEquals(res.body().jsonPath().getInt("id") ,id);
        System.out.println(res.body().jsonPath().getInt("id"));

    }




    @Test
    public void cartLimits()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .queryParam("limit","3")
                .when().get("/{cart}") ;

        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,200);

        for(int i=0; i<3 ;i++)
        {
            Assert.assertEquals(res.body().jsonPath().getInt("id["+i+"]") ,i+1);

        }


    }


    @Test
    public void sortCart()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .queryParam("sort","desc")
                .when().get("/{cart}") ;

        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,200);
        Assert.assertEquals(res.body().jsonPath().getInt("id[0]") ,7);
        Assert.assertEquals(res.body().jsonPath().getInt("id[6]") ,1);


    }



    @Test
    public void cartsInDateRange()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .queryParam("startdate","2019-12-10")
                .queryParam("enddate","2020-10-10")
                .with().get("/{cart}");

        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,200);
    }


    @Test
    public void userCarts()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("cart","carts")
                .pathParam("usr","user")
                .pathParam("usrID","3")

                .when().get("/{cart}/{usr}/{usrID}") ;

        res.prettyPrint();
        Assert.assertEquals(res.statusCode() ,200);
        Assert.assertEquals(res.body().jsonPath().getString("userId[0]") ,"3");
    }



}
