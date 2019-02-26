package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-collection.xml")
public class CollectionBeanTest {
	private Logger logger = LoggerFactory.getLogger(CollectionBeanTest.class);
	@Resource(name="collectionBean")
	private CollectionBean CollectionBeanTest;
	
	/**
	* Method : testCollectionBean
	* 작성자 : PC03
	* 변경이력 :
	* Method 설명 :스프링 설정 파일을 통해 컬렉션 객체를 생성하고, 주입받는다.
	* 			list,set,map,propertise
	*/
	@Test
	public void testCollectionBean() {
		/***Given***/
		
		/***When***/
		List<String> list =CollectionBeanTest.getList();
		for(String str : list)
		logger.debug("list :"+str );
		Set<String> set = CollectionBeanTest.getSet();
		for(String setstr : set)
		logger.debug("set :"+setstr );
		Map<String,String> map = CollectionBeanTest.getMap();
		for(String mapstr : set)
			logger.debug("map :"+mapstr);
		Properties propertiesstr = CollectionBeanTest.getProperties();
			logger.debug("properties  :"+propertiesstr);

		//set,map,properties 출력
		/***Then***/
		assertNotNull(list);
		assertEquals(3, list.size());
		//list
		assertNotNull(set);
		assertEquals(3, set.size());
		//set
		assertNotNull(map);
		assertEquals(3, map.size());
		//map
		assertNotNull(propertiesstr);
		assertEquals(3, propertiesstr.size());
		//propertiesstr



		//assert 구문을  이용하여 속성이 정상적으로 주입되었는지 테스트 코드 작성
	}

}
