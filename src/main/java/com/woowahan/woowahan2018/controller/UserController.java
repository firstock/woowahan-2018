package com.woowahan.woowahan2018.controller;

import com.woowahan.woowahan2018.dto.CommonResponse;
import com.woowahan.woowahan2018.dto.UserDto;
import com.woowahan.woowahan2018.dto.group.EmailPriorityGroup;
import com.woowahan.woowahan2018.dto.group.NamePriorityGroup;
import com.woowahan.woowahan2018.dto.group.PasswordPriorityGroup;
import com.woowahan.woowahan2018.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("")
    public CommonResponse createUser(@Validated(value = {EmailPriorityGroup.class, PasswordPriorityGroup.class, NamePriorityGroup.class})
                                     @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        log.debug("it's email : {}", userDto.getEmail());

        return CommonResponse.success("USER.CREATE");
    }
}
