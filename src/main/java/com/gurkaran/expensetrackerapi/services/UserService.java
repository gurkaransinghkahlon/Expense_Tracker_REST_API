package com.gurkaran.expensetrackerapi.services;

import com.gurkaran.expensetrackerapi.domain.User;
import com.gurkaran.expensetrackerapi.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;




}
