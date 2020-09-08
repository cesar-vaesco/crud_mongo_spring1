package com.example.usuario.repository;

import com.example.usuario.repository.UserRepository;
import com.example.usuario.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;



@Repository
public class UserRepositoryImpl implements UserRepository {

	private final MongoOperations mongoOperations;


	@Autowired
	public UserRepositoryImpl(MongoOperations mongoOperations) {
		Assert.notNull(mongoOperations);
		this.mongoOperations = mongoOperations;
	}

	@Override
	  public Optional<List<User>> findAll() {
    	List<User> users = this.mongoOperations.find(new Query(), User.class);
        Optional<List<User>> optionalUsers = Optional.ofNullable(users);
        return optionalUsers;
	}    
	

    public Optional<User> findOne(String userId) {
        User d = this.mongoOperations.findOne(new Query(Criteria.where("userId").is(userId)), User.class);
        Optional<User> user = Optional.ofNullable(d);
        return user;
    }
	
	@Override
	public User saveUser(User user) {
		this.mongoOperations.save(user);
        return findOne(user.getUserId()).get();
	}

	

	@Override
	public void updateUser(User user) {
		this.mongoOperations.save(user);

	}

	@Override
	public void deleteUser(String userId) {
		this.mongoOperations.findAllAndRemove(new Query(Criteria.where("userId").is(userId)), User.class);

	}

}
