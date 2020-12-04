/*
 * 파일과 관련된 유용한 기능을 모아놓는 클래스
 * */
package common;

public class FileManager {
	//확장자만 추출하기
	public static String getExtend(String path) {
		int start = path.lastIndexOf(".");
		int end = path.length();
		String ext = path.substring(start+1, end);
		
		return ext;
	}
	
	/*
	//단위테스트 해보기 위함
	public static void main(String[] args) {
		String filename="c:\\photo\\summer\\2010\\지난여름.사진.jpg";
		System.out.println(getExtend(filename));
	}
	*/
}
