package com.example.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.Department;
import com.example.userservice.vo.ResponseTemplateVO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(Long id) {
		return userRepository.findByUserId(id);
	}

	public ResponseTemplateVO getUserWithDepartment(Long id) {
		// TODO Auto-generated method stub
		ResponseTemplateVO vo=new ResponseTemplateVO();
		User user=userRepository.findByUserId(id);
		
		Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+user.getDepartmentId(), Department.class);
		vo.setUser(user);
		vo.setDepartment(department);
		return vo;
	}
}
