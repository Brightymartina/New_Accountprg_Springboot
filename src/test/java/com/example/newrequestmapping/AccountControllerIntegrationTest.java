/*package com.example.newrequestmapping;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class AccountControllerIntegrationTest {

    private static final String BASE_URI = "http://localhost:8080";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate template;

    // =========================================== Get All Accounts ==========================================

    @Test
    public void test_get_all_success(){
        ResponseEntity<Account[]> response = template.getForEntity(BASE_URI+"/get", Account[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        //validateCORSHttpHeaders(response.getHeaders());
    }

    // =========================================== Get Account By ID =========================================

    @Test
    public void test_get_by_id_success(){
        ResponseEntity<Account> response = template.getForEntity(BASE_URI + "/1", Account.class);
        Account account = response.getBody();
        assertThat(account.getAccountId(), is(1));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        //validateCORSHttpHeaders(response.getHeaders());
    }

    @Test
    public void test_get_by_id_failure_not_found(){
        try {
            ResponseEntity<Account> response = template.getForEntity(BASE_URI + "/" + UNKNOWN_ID, Account.class);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
            //validateCORSHttpHeaders(e.getResponseHeaders());
        }
    }

    // =========================================== Create New Account ========================================

    @Test
    public void test_create_new_account_success(){
        Account newAccount = new Account(1L,"John","CBE",150000.00);
        URI location = template.postForLocation(BASE_URI+"/post", newAccount, Account.class);
        assertThat(location, notNullValue());
    }

    @Test
    public void test_create_new_account_fail_exists(){
        Account existingAccount = new Account(1L,"John","CBE",150000.00);
        try {
            URI location = template.postForLocation(BASE_URI, existingAccount, Account.class);
            fail("should return 409 conflict");
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.CONFLICT));
            //validateCORSHttpHeaders(e.getResponseHeaders());
        }
    }

    // =========================================== Update Existing Account ===================================

    @Test
    public void test_update_account_success(){
        Account existingAccount = new Account(1L,"John","CBE",150000.00);
        template.put(BASE_URI + "/" + existingAccount.getAccountId(), existingAccount);
    }

    @Test
    public void test_update_account_fail(){
        Account existingAccount = new Account(1L,"John","CBE",150000.00);
        try {
            template.put(BASE_URI + "/" + existingAccount.getAccountId(), existingAccount);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
            //validateCORSHttpHeaders(e.getResponseHeaders());
        }
    }

    // =========================================== Delete Account ============================================

    @Test
    public void test_delete_account_success(){
        template.delete(BASE_URI + "/" + getLastAccount().getAccountId());
    }

    @Test
    public void test_delete_account_fail(){
        try {
            template.delete(BASE_URI + "/" + UNKNOWN_ID);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
            //validateCORSHttpHeaders(e.getResponseHeaders());
        }
    }

    private Account getLastAccount(){
        ResponseEntity<Account[]> response = template.getForEntity(BASE_URI, Account[].class);
        Account[] accounts = response.getBody();
        return accounts[accounts.length - 1];
    }

    // =========================================== CORS Headers ===========================================

    public void validateCORSHttpHeaders(HttpHeaders headers){
        assertThat(headers.getAccessControlAllowOrigin(), is("*"));
        assertThat(headers.getAccessControlAllowHeaders(), hasItem("*"));
        assertThat(headers.getAccessControlMaxAge(), is(3600L));
        assertThat(headers.getAccessControlAllowMethods(), hasItems(
                HttpMethod.GET,
                HttpMethod.POST,
                HttpMethod.PUT,
                
                HttpMethod.OPTIONS,
                HttpMethod.DELETE));
    }
}
*/