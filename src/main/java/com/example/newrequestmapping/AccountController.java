
package com.example.newrequestmapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import com.example.newrequestmapping.Account;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.example.newrequestmapping.AccountService;

/**
 * @author Baraneetharan R
 *
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<List<Account>> all() {
        return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody Account ac, UriComponentsBuilder ucBuilder) {
       /* if (accountService.alreadyExists(ac.getAccountId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }*/

        accountService.save(ac);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/get/{id}").buildAndExpand(ac.getAccountId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @GetMapping("/get/{accountId}")
    public @ResponseBody ResponseEntity<?> getById(@PathVariable Long accountId) {

        Account account = accountService.find(accountId);
       /*if (account == null) {
            return new ResponseEntity<>("No Customer found for ID " + accountId, HttpStatus.NOT_FOUND);
        }*/

        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    @PutMapping("/put/{accountId}")
    public ResponseEntity<?> put(@PathVariable Long accountId, @RequestBody Account account) {

        Account currentaccount = accountService.find(accountId);

       /* if (currentaccount == null) {
            return new ResponseEntity<>("No Customer found for ID " + accountId, HttpStatus.NOT_FOUND);
        }*/

        currentaccount.setName(account.getName());
        currentaccount.setCity(account.getCity());
        currentaccount.setBalance(account.getBalance());

        accountService.save(currentaccount);
        return new ResponseEntity<>(currentaccount, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> delete(@PathVariable Long accountId) {
        Account currentaccount = accountService.find(accountId);
       /*if (currentaccount == null) {
            return new ResponseEntity<>("No Customer found for ID " + accountId, HttpStatus.NOT_FOUND);
        }*/
        accountService.delete(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}