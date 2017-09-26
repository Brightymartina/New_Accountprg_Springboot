package com.example.newrequestmapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.hamcrest.Matchers.containsString;
import org.springframework.http.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;
import com.example.newrequestmapping.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountService.class, secure = false)
public class AccountServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void getByID1() throws Exception {
        Account acc = new Account();
        acc.setAccountId(1L);
        acc.setBalance(100000.00);
        acc.setCity("CBE");
        acc.setName("NAME1");

        given(accountRepository.findOne(1L)).willReturn(acc);
        mockMvc.perform(get("/get/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                //{  'accountId': 1,  'name': 'NAME1',  'city': 'CBE',  'balance': 100000 }
                .andExpect(content().json("{  'accountId': 1,  'name': 'NAME1',  'city': 'CBE',  'balance': 100000 }"));
    }

    @Test
    public void getAll1() throws Exception {
        List<Account> acc = Arrays.asList(new Account(1L, "John", "Mumbai", 100000.00),
                new Account(2L, "Peter", "Chennai", 200000.00), new Account(3L, "Mike", "Coimbatore", 300000.00));
        given(accountRepository.findAll()).willReturn(acc);
        mockMvc.perform(get("/get/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content()
                .json("[{  'accountId': 1,  'name': 'John',  'city': 'Mumbai',  'balance': 100000 },{  'accountId': 2,  'name': 'Peter',  'city': 'Chennai',  'balance': 200000 },{  'accountId': 3,  'name': 'Mike',  'city': 'Coimbatore',  'balance': 300000 }]"));

    }

    @Test
    public void postmapping1() throws Exception {
        Account acc = new Account();
        acc.setAccountId(1L);
        acc.setBalance(100000.00);
        acc.setCity("Mumbai");
        acc.setName("John");

       when(accountRepository.findOne(acc.getAccountId())).thenReturn(acc);
        mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(acc)))
                .andExpect(status().isCreated());

        //verify(accountService, times(1)).find(acc.getAccountId());
        // verify(accountService, times(1)).save(acc);
        // verifyNoMoreInteractions(accountService);

    }



	@Test
    public void updateByID1() throws Exception {

        Account acc1 = new Account();
        acc1.setAccountId(1L);
        acc1.setBalance(600000.00);
        acc1.setCity("CBE6");
        acc1.setName("NAMEss");

        when(accountRepository.findOne(acc1.getAccountId())).thenReturn(acc1);
      
        mockMvc.perform(
                put("/put/1", acc1.getAccountId()).contentType(MediaType.APPLICATION_JSON).content(asJsonString(acc1)))
                .andExpect(status().isOk());

        verify(accountRepository, times(1)).findOne(acc1.getAccountId());
        verify(accountRepository, times(1)).save(acc1);
        verifyNoMoreInteractions(accountRepository);
    }
    @Test
    public void deleteByID1() throws Exception {
 
        Account acc = new Account();
        acc.setAccountId(1L);
        acc.setBalance(100000.00);
        acc.setCity("CBE");
        acc.setName("NAME1");

        when(accountRepository.findOne(acc.getAccountId())).thenReturn(acc);

        mockMvc.perform(delete("/delete/1", acc.getAccountId())).andExpect(status().isOk());

        verify(accountRepository, times(1)).findOne(acc.getAccountId());
        verify(accountRepository, times(1)).delete(acc.getAccountId());
        verifyNoMoreInteractions(accountRepository);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}