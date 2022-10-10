
package com.wizeline.maven.learningjava.service;

import java.net.URI;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wizeline.maven.learningjava.model.Agify;
import com.wizeline.maven.learningjava.model.ErrorDTO;
import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.repository.UserRepositoryImpl;
import com.wizeline.maven.learningjava.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	URI targetUrl = UriComponentsBuilder.fromUriString("api.agify.io").queryParam("name", "Luis").build().encode()
			.toUri();
	Agify agify = restTemplate.getForObject(targetUrl, Agify.class);

	@Override
	public ResponseDTO createUser(String user, String password) {
		
		System.out.println(agify.getName());
		
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "fail";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserRepositoryImpl userDao = new UserRepositoryImpl();
			result = userDao.createUser(user, password);
			response.setCode("OK000");
			response.setStatus(result);

		} else {
			response.setCode("OK000");
			response.setStatus(result);
			response.setErrors(new ErrorDTO("ER001", "Error al crear usuario"));
		}
		return response;
	}

	@Override
	public ResponseDTO login(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserRepositoryImpl userDao = new UserRepositoryImpl();
			result = userDao.login(user, password);
		}
		if ("success".equals(result)) {
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("ER001");
			response.setErrors(new ErrorDTO("ER001", result));
			response.setStatus("fail");
		}
		return response;
	}

}
