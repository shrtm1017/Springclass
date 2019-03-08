package kr.or.ddit.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.ranger.controller.RangerControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
//로직만 쓸거면 serlvet-context가 필요없다
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml",
	"classpath:kr/or/ddit/config/spring/context-datasource.xml",
	"classpath:kr/or/ddit/config/spring/context-transaction.xml"})
public class LogicTestConfig {

}
