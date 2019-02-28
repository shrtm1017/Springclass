package kr.or.ddit.util.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testCookieStringParsing() {
		/***Given***/
		String cookieString = "rememberne=w; w=w; test=value";
		
		CookieUtil cookieUtil = new CookieUtil(cookieString);
		
		
		
		/***When***/
		String cookieValue = cookieUtil.getCookieValue("rememberne");
		String cookieValue2 = cookieUtil.getCookieValue("w");
		String cookieValue3 = cookieUtil.getCookieValue("test");
		/***Then***/
		
		assertEquals("w",cookieValue );
		assertEquals("w", cookieValue2);
		assertEquals("value",cookieValue3);
		

	}
			/**
			* Method : testGetFileNameFromPart
			* 작성자 : PC03
			* 변경이력 :
			* Method 설명 : part의Content-Disposition 헤더로 부터
			* 				fileName을 갖고온다.
			*/
			@Test
			public void testGetFileNameFromPart(){
		/***Given***/
		String contentDisposition = "form-data; name=\"uploadFile\"; filename=\"asd.png\"";
		
		/***When***/
		String fileName = PartUtil.getFileNameFromPart(contentDisposition);
		/***Then***/
		assertEquals("asd.png",fileName );

	}
		
}
