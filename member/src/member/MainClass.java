package member;

import java.util.ArrayList;



import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.MemberDto;


public class MainClass {

	public static void main(String[] args) {

		// 멤버 정보 DB 활용
		
		// 파일에 있는 데이터를 읽어오기
		String filePath = "C:/dev/전공정보.txt";
		Member member = new Member();
		ArrayList<MemberDto> list = member.getMemberList(filePath);
		MemberDaoImpl mdao = new MemberDaoImpl();
		//		for (MemberDto d : list) {
//			System.out.println(d.getName());
//		}
		//DB연결
		MemberDao dao = new MemberDaoImpl();
//		System.out.println("쉼표로 구분되는 데이터 갯수: " + spData.length);
		// 읽어온 데이터를 DB에 넣기
		for(MemberDto d:list) {
			mdao.insert(d);
		}
		
		// 데이터 보기
		MemberDto dto = dao.selectOne(0);
		System.out.println(dto.getName());
		System.out.println(dto.getMajor());
		System.out.println(dto.getEmail());
		
		ArrayList<MemberDto> dList = dao.selectAll();
		for(MemberDto d : list) {
			System.out.println(d.getName());
			System.out.println(d.getMajor());
			System.out.println(d.getEmail());
		}
		// 데이터 수정
		dao.update(dto);
	}

}
