package member;

import java.util.ArrayList;

import comn.Util;
import dto.MemberDto;

public class Member {

	public void getDtoList(String filePath) {
		
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		ArrayList<String> spList = Util.readLineFileList(filePath);
		for(String s : spList) {
			String[] sp = s.split(",");
			dto.setNum(Integer.parseInt(sp[0]));//문자열을 숫자로
			dto.setName(sp[1]);
			dto.setMajor(sp[2]);
			dto.setEmail(sp[3]);
			
			}
	}

}
	
