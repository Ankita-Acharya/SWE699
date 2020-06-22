package com.swe699.userManagement;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
    public Users findByEmail(String email);
    public Users findByuName(String uName);
    public Users findByPass(String pass);
}
