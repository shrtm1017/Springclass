package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context.xml")
public class IocMainTest {
	//rangerDao, rangerSerivce
	//DI(Dependency Injection)
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao;
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao2;
	
	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype1;

	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype2;


	@Test
	public void testRangerDao() {
		//기존방법
		//ApplicationContext context = new ...
		//DL을 통해 스프링 컨테이너에 스프링 빈을 요청
		//IRangerDao rangerDao = context.getBean("rangerDao");
		//변경 방법
		
		//스프링 컨테이너 생성을 junit runner에게 위임
		//우리가 사용하고자 하는 객체를 DI(Dependency Injection)을 통해 주입 받는다.
		//@Autowired(스프링),@Resource(java 표준)
		//테스트 대상
		//1.스프링빈이 정상적으로 생성되고, 주입이 문제가 없는지
		
		assertNotNull(rangerDao);
	}
	/**
	* Method : testSpringSingletonBean
	* 작성자 : PC03
	* 변경이력 :
	* Method 설명 : 같은 이름의 스프링 빈을 두번 주입 받앗을 때
	* 				해당 객체가 동일한 객체 인지
	*/
	/**
	* Method : testSpringSingletonBean
	* 작성자 : PC03
	* 변경이력 :
	* Method 설명 : 같은 클래스로 선언된 서로 다른 스프링 빈(singleton)이 디자인 패턴의 정의대로 
	*/
	@Test
	public void testSpringSingletonBean() {
		/***Given***/
		
		/***When***/
		/***Then***/
		//디자인 패터에 의하면 같은 클래스로부터 하나의 인스턴스만 존재하게 하는 패턴이 ㅅ이글톤
		//싱글톤
		//rangerDaoSpringBean과 rangerDao 스프링 빈은 서로 같은 RangerDaoImPl
		//클래스로 부터 생성ㄱ된 객체
		//디자인 패턴의 정의에 의해서 두개의 객체는 서로 같아야 된다.
		//서로 다른 이유는 메모리주소값이 틀리다.
		assertNotEquals(rangerDao, rangerDao2);
		//스프링 bean scope에서 이야기하는 singleton 은 스프링 이름 단위로 생성된다.
		//rangerDaoSpringBean과 rangerDao는 같은 클래스로 부터 생성되었지만
		//sptring bean 이름을 서로 다르게 선언 하였기 때문에 스프링 컨테이너 내에서는
		//서로 다른 객체로 인식한다.
		
	}
	@Test
	public void testProtoType() {
		/***Given***/
		
		/***When***/
		/***Then***/
		assertNotEquals(rangerDaoPrototype1, rangerDaoPrototype2);
		
	}

}
