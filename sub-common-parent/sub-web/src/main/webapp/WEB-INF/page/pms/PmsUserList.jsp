<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);"
		action="pms_listPmsUser.action" method="post">
		<!-- 分页表单参数 -->
		<%@include file="../inc/pageForm.jsp"%>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>用户登录名：<input type="text" name="userNo" value="${userNo}"
						size="30" alt="精确查询" />
					</td>
					<td>用户姓名：<input type="text" name="userName"
						value="${userName}" size="30" alt="模糊查询" />
					</td>
					<td>状态：</td>
					<td><select name="status" class="combox">
							<option value="">-全部-</option>
					</select></td>
					<td>
						<div class="subBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">查询</button>
										</div>
									</div></li>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<rc:permission value="pms:user:add">
				<li><a class="add" href="pms_addPmsUserUI.action"
					target="dialog" rel="input" title="添加用户"><span>添加用户</span></a></li>
			</rc:permission>
		</ul>
	</div>

	<table class="table" targetType="navTab" asc="asc" desc="desc"
		width="100%" width="100%" layoutH="130">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th width="200">用户登录名</th>
				<th>用户姓名</th>
				<th width="100">类型</th>
				<th width="300">操作</th>
				<!-- 图标列不能居中 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.recordList }" var="e">
				<tr  target="sid_user" rel="${e.id}">
				  <td>${e.id }</td>
				  <td>${e.userNo }</td>
				  <td>${e.userName }</td>
				  <td>${e.userName }</td>
				  <td>${e.userName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页条 -->
	<%@include file="../inc/pageBar.jsp"%>
</div>