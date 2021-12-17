package org.kpu.myweb.service;

import java.util.List;

import org.kpu.myweb.domain.StudentVO;
import org.kpu.myweb.persistence.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public StudentVO readMember(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.read(id);
	}

	@Override
	public List<StudentVO> readMemberList() throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.readList();
	}

	@Override
	public void addMember(StudentVO student) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.add(student);
	}

	@Override
	public void deleteMember(String id) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.delete(id);
	}

	@Override
	public void updateMember(StudentVO student) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.update(student);
	}

}
