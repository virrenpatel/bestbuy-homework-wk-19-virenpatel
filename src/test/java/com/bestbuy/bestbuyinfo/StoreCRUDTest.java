package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends TestBase {
    static String name = "VP";
    static String type = "SmallBox";
    static String address = "123 Om";
    static String address2 = "";
    static String city = "Del";
    static String state = "Guj";
    static String zip = "55305";
    static int lat = 123;
    static int lng = 56;
    static String hours = "2";
    static HashMap<String, String> services;
    static int storeId;

    @Steps
    StoreSteps storeSteps;

    @Title("This will create new  store")
    @Test
    public void test001() {

        ValidatableResponse response = storeSteps.addStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeId=response.extract().path("id");
        System.out.println(storeId);
    }

    @Title("Getting the store information with Name:")
    @Test
    public void test002() {
ValidatableResponse response=storeSteps.getStoreById(storeId).statusCode(200);


    }

    @Title("Updating store information and verify the updated information")
    @Test
    public void test003() {
        name = name + ("_added");
        storeSteps.updateStoreByName(storeId, name, type, address, address2, city, state, zip, lat, lng, hours).log().all().statusCode(200);


    }

    @Title("Deleting store with id and verify that user is deleted")
    @Test
    public void test004() {
        storeSteps.deleteStore(storeId).statusCode(200);
        storeSteps.getStoreById(storeId).statusCode(404);

    }
}
