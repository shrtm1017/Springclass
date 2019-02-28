package kr.or.ddit.util.model;

public class CookieUtil {
//필드선언 해놔야함
	private String cookieString;
//	private	String[] cookie =cookieString.split("; ");
	public CookieUtil(String cookieString) {
		this.cookieString = cookieString;
		
	}

	/**
	* Method : getCookieValue
	* 작성자 : PC03
	* 변경이력 :
	* @param string
	* @return
	* Method 설명 :쿠키 이름에 해당하는 쿠키값을 조회
	*/
	public String getCookieValue(String string) {
		
//		String cookieValue ="";
//		for(String cookie : cookieArray){
//			if(cookieName.equals(cookie.split("="[0])){
//				cookieValue = cookie.split("=")[1];
//				break;
//			}
//		}
//		retrun cookieValue;
		
		String[] cookie =cookieString.split("; ");
		String[] cookiename;
		
		for (int i = 0; i < cookie.length; i++) {
			cookiename=cookie[i].split("=");
				if(cookiename[0].equals(string)){
					return cookiename[1];
				}
		}
		return null;
	}
	

}

