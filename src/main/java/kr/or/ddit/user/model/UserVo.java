package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserVo implements HttpSessionBindingListener{
	private Logger logger = LoggerFactory.getLogger(UserVo.class);
	private String userId; // 사용자 아이디
	private String userNm; // 사용자 이름
	private String alias; // 별명
	private String addr1; // 주소
	private String addr2; // 상세주소
	private String zipcode; // 상세주소
	private Date reg_dt; // 사용자 비밀번호
	private String pass;// 사용자 비밀번호
	private String filename;
	private String realFilename;
	public UserVo(){
		
	}

	public UserVo(String userId, String userNm, String alias, String addr1,
			String addr2, String zipcode, String pass) {
		super();
		this.userId = userId;
		this.userNm = userNm;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.pass = pass;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRealFilename() {
		return realFilename;
	}

	public void setRealFilename(String realFilename) {
		this.realFilename = realFilename;
	}

	public String getReg_dt_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(reg_dt);
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userNm=" + userNm + ", alias="
				+ alias + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", zipcode=" + zipcode + ", reg_dt=" + reg_dt + ", pass="
				+ pass + ", filename=" + filename + ", realFilename="
				+ realFilename + "]";
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session =event.getSession();
		logger.debug("userVo valueBound : {}", session.getId());
		//session에 저장
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session =event.getSession();
		logger.debug("userVo valueUnBound : {}", session.getId());
		
	}

	

}
