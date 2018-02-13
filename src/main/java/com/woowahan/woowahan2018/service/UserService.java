package com.woowahan.woowahan2018.service;

import com.woowahan.woowahan2018.domain.User;
import com.woowahan.woowahan2018.domain.UserRepository;
import com.woowahan.woowahan2018.dto.UserDto;
import com.woowahan.woowahan2018.exception.DuplicatedEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public void createUser(UserDto userDto) throws DuplicatedEmailException {
		Optional<User> maybeUser = userRepository.findByEmail(userDto.getEmail());
		log.debug("userDto: {}", userDto);

		if (maybeUser.isPresent())
			throw new DuplicatedEmailException("이미 가입된 사용자입니다.");

		userRepository.save(userDto.toUser());
	}

	private Optional<User> findUserById(long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

}