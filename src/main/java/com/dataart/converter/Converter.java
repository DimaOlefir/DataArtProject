package com.dataart.converter;

import com.dataart.dto.UserDTO;
import com.dataart.model.User;

/**
 * Created by Роман on 09.12.2017.
 */
public class Converter {

    public static UserDTO convert(User user){
        if(user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setSex(user.getSex());
        userDTO.setAbout(user.getAbout());
        return  userDTO;
    }
}
