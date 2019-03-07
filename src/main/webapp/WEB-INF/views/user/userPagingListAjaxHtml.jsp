<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<c:forEach items="${userList }" var ="user" varStatus="i">
	<tr class='userTr' data-userid='${user.userId }'>
		<td> ${i.index } </td>
        <td> ${user.userId }</td>
        <td> ${user.userNm }</td>
        <td> ${user.alias }</td>
        <td> ${user.reg_dt_fmt }</td>
	</tr>
</c:forEach>
==============seperator===================
<c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0))}"/>
  					<c:choose>
                        <c:when test="${page == 1 }">
                           <li class="disabled"><a aria-label="Previous"> <span
                                 aria-hidden="true">&laquo;</span>
                           </a></li>
                        </c:when>
                        <c:otherwise>
                           <li><a
                              href="javascript:getUserPageListHtml(1)"
                              aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                           </a></li>
                        </c:otherwise>
                     </c:choose>

                     <c:forEach begin="1" end="${lastPage }" var="i">
                        <c:set var="active" value="" />
                        <c:if test="${i == page }">
                           <c:set var="active" value="active" />
                        </c:if>

                        <li class="${active }"><a
                           href="javascript:getUserPageListHtml(${i})">${i}</a>
                        </li>
                     </c:forEach>

                     <c:choose>
                        <c:when test="${page ==lastPage}">
                           <li class="disabled">
                           		<a aria-label="Next"> 
                           <span aria-hidden="true">&raquo;</span>
                         		 </a>
                           </li>
                        </c:when>
                        <c:otherwise>
                           <li><a
                              href="javascript:getUserPageListHtml(${lastPage});"
                              aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                           </a></li>
                        </c:otherwise>
                     </c:choose>



