package org.kpu.mweb.controller;

import java.nio.charset.Charset;

import org.kpu.myweb.domain.StudentVO;
import org.kpu.myweb.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/member/rest")
public class MemberRestController {	
	private static final Logger logger = LoggerFactory.getLogger(MemberRestController.class);
	
	@Autowired(required=true)
	private MemberService memberService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StudentVO> readMember(@PathVariable String id) throws Exception {
		StudentVO student = memberService.readMember(id);
		logger.info("/member/rest/{id} REST-API GET method called. then method executed.");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header",  "MyHeaderValue");
		return new ResponseEntity<StudentVO>(student, headers, HttpStatus.OK);
	}
}
