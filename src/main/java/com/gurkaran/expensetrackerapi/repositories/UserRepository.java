package com.gurkaran.expensetrackerapi.repositories;

import com.gurkaran.expensetrackerapi.domain.User;
import com.gurkaran.expensetrackerapi.exceptions.EtAuthException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);

}
