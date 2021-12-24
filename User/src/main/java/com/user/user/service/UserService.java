package com.user.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.user.entity.User;
import com.user.user.repository.UserRepository;
import com.user.user.vo.Department;
import com.user.user.vo.ResponseTemplateVO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		ResponseTemplateVO responseTemplateVO=new ResponseTemplateVO();
		User user=userRepository.findByUserId(userId);
		Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getUserId(), Department.class);
		responseTemplateVO.setDepartment(department);
		responseTemplateVO.setUser(user);
		return responseTemplateVO;
	}

}
