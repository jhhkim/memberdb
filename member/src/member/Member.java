package member;

import java.util.ArrayList;

import comn.Util;
import dto.MemberDto;

public class Member {

	public ArrayList<MemberDto> getMemberList(String filePath) {
		
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		ArrayList<String> spList = Util.readLineFileList(filePath);
		for(String s : spList) {
			MemberDto dto = new MemberDto();
			String[] sp = s.split(",");
			dto.setNum(Integer.parseInt(sp[0]));//문자열을 숫자로
			dto.setName(sp[1]);
			dto.setMajor(sp[2]);
			dto.setEmail(sp[3]);
			list.add(dto);
			
			}
		return list;
	}

}
	
