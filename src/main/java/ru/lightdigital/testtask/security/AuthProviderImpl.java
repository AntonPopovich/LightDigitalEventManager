//package ru.lightdigital.testtask.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import ru.lightdigital.testtask.services.PersonDetailsService;
//
//import java.util.Collections;
//
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    private final PersonDetailsService personDetailsService;
//
//    @Autowired
//    public AuthProviderImpl(PersonDetailsService personDetailsService) {
//        this.personDetailsService = personDetailsService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String login = authentication.getName();
//
//        UserDetails personDetails = personDetailsService.loadUserByUsername(login);
//
//        String password = authentication.getCredentials().toString();
//
//        if(!password.equals(personDetails.getPassword()))
//            throw new BadCredentialsException("Incorrect password");
//
//        return new UsernamePasswordAuthenticationToken(personDetails, password,
//                Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
//      http://localhost:8080/auth/signin
//{
//        "login": "test1",
//        "password": "0123123456"
//        }

//{
//        "login": "admin1",
//        "password": "012312345"
//        }
// 3-rd method POST (register to event)
//{
//        "fio": "Ivan Semenov",
//        "pcr": "pcr12",
//        "age": 34,
//        "person": "test1",
//        "event": "SomeParty"
//}
// 4-rd method POST(NEW Event) http://localhost:8080/new-event/
//{
//        "name": "Joyyyy",
//        "ticketPrice": 23.54,
//        "contractNumber": 1
//}

// 6-rd method POST (new contract) http://localhost:8080/new-event/contracts/new
//{
//        "principalName": "OOO \"GAS\"",
//        "cost": 3560000
//}