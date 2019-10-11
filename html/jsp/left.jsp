<%@page import="test.sec.bean.WebAdminBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'left.jsp' starting page</title>


		<style type="text/css">

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE3 {
	font-size: 12px;
	color: #FFD700;
}

</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}

.style1 {
	font-size: 12px;
}

a {
	text-decoration: none;
	color: #FFA500;
}
}
</style>


	</head>

	<body bgcolor="#000000">
			<%
						WebAdminBean adminInfo = (WebAdminBean)request.getSession().getAttribute("adminInfo");
			             String admin = adminInfo.getUserAdmin();
			 %>
		<table width="165" height="100%" border="0" cellpadding="0"
			cellspacing="0">

			
			
			<tr>
				<td valign="top">
					<table width="151" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr height="23"> 				
						</tr>
						
						
						<%if(!admin.contains("customer_")){ %>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="23"
											background=""
											id="imgmenu1" class="menu_title"
											onMouseOver="this.className='menu_title2';"
											onClick="showsubmenu(1)"
											onMouseOut="this.className='menu_title';"
											style="cursor: hand">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="18%">
														&nbsp;
													</td>
													<td width="82%" class="STYLE1">
														运维视图
													</td>
												</tr>
											</table>
										</td>
									</tr>
									
									<tr>
										<td background=""
											id="submenu1">
											<div class="sec_menu">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>
															<table width="90%" border="0" align="center"
																cellpadding="0" cellspacing="0">

																<tr>
																	<td width="16%" height="25">
																		<div align="center">
																			<img src=""
																				width="10" height="10" />
																		</div>
																	</td>
																	<td width="84%" height="23">
																		<table width="95%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td height="20" style="cursor: hand"
																					onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																					onmouseout="this.style.borderStyle='none'">
																					<span class="STYLE3"><a 
																						href="<%=basePath%>servlet/WorkViewServlet?isExecute=Y&userId=<%=Integer.toString(adminInfo.getOperatorId())%>"
																						target="mainFrame">&nbsp;&nbsp;CASE执行</a> </span>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>	
	
															</table>
														</td>
													</tr>
													<tr>
														<td height="5">
															<img src=""
																width="151" height="5" />
														</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>

								</table>
							</td>
						</tr>





						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="23"
											background=""
											id="imgmenu3" class="menu_title"
											onmouseover="this.className='menu_title2';"
											onclick="showsubmenu(3)"
											onmouseout="this.className='menu_title';"
											style="cursor: hand">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="18%">
														&nbsp;
													</td>
													<td width="82%" class="STYLE1">
														管理视图
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td background=""
											id="submenu3">
											<div class="sec_menu">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>
															<table width="90%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																																																												
																<tr>
																	<td width="16%" height="25">
																		<div align="center">
																			<img src=""
																				width="10" height="10" />
																		</div>
																	</td>
																	<td width="84%" height="23">
																		<table width="95%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td height="20" style="cursor: hand"
																					onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																					onmouseout="this.style.borderStyle='none'">
																					<span class="STYLE3"><a 
																						href="<%=basePath%>servlet/CaseManageServlet?self=yes&userId=<%=Integer.toString(adminInfo.getOperatorId())%>"
																						target="mainFrame">&nbsp;&nbsp;CASE管理</a> </span>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																
																<tr>
																	<td width="16%" height="25">
																		<div align="center">
																			<img src=""
																				width="10" height="10" />
																		</div>
																	</td>
																	<td width="84%" height="23">
																		<table width="95%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td height="20" style="cursor: hand"
																					onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																					onmouseout="this.style.borderStyle='none'">
																					<span class="STYLE3"><a 
																						href="<%=basePath%>servlet/ComQueryServlet?self=yes&userId=<%=Integer.toString(adminInfo.getOperatorId())%>"
																						target="mainFrame">&nbsp;&nbsp;组件开发</a> </span>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>		

												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>


                          <%}%>

						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="23"
											background=""
											id="imgmenu2" class="menu_title"
											onMouseOver="this.className='menu_title2';"
											onClick="showsubmenu(2)"
											onMouseOut="this.className='menu_title';"
											style="cursor: hand">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="18%">
														&nbsp;
													</td>
													<td width="82%" class="STYLE1">
														配置视图
													</td>
												</tr>
											</table>
										</td>
									</tr>
									
									
									<%if(!admin.contains("customer_")){ %>
									<tr>
										<td background=""
											id="submenu2">
											<div class="sec_menu">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>
															<table width="90%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td width="16%" height="25">
																		<div align="center">
																			<img src=""
																				width="10" height="10" />
																		</div>
																	</td>
																	<td width="84%" height="23">
																		<table width="95%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td height="20" style="cursor: hand"
																					onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																					onmouseout="this.style.borderStyle='none'">
																					<span class="STYLE3"><a
																						href="<%=basePath%>servlet/ConfigQueryServlet?paraType=&paraCode=&remark=" target="mainFrame">&nbsp;&nbsp;配置管理</a>
																					</span>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>

															</table>
														</td>
													</tr>
													<tr>
														<td height="5">
															<img src=""
																width="151" height="5" />
														</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
									
									<%}%>
									
										<tr>
										<td background=""
											id="submenu2">
											<div class="sec_menu">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>
															<table width="90%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td width="16%" height="25">
																		<div align="center">
																			<img src=""
																				width="10" height="10" />
																		</div>
																	</td>
																	<td width="84%" height="23">
																		<table width="95%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td height="20" style="cursor: hand"
																					onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																					onmouseout="this.style.borderStyle='none'">
																					<span class="STYLE3"><a
																						href="<%=basePath%>servlet/AccessConfigQueryServlet?paraType=&paraCode=&remark=" target="mainFrame">&nbsp;&nbsp;数据查询</a>
																					</span>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>

															</table>
														</td>
													</tr>
													<tr>
														<td height="5">
															<img src=""
																width="151" height="5" />
														</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>								
									

								</table>
							</td>
						</tr>


	                   <%if(!admin.contains("customer_")){ %>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="23"
											background=""
											id="imgmenu4" class="menu_title"
											onmouseover="this.className='menu_title2';"
											onclick="showsubmenu(4)"
											onmouseout="this.className='menu_title';"
											style="cursor: hand">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="18%">
														&nbsp;
													</td>
													<td width="82%" class="STYLE1">
														管理员
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td background=""
											id="submenu4" >
											<div class="sec_menu">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>
															<table width="90%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																
																<tr>
																	<td width="16%" height="25">
																		<div align="center">
																			<img src=""
																				width="10" height="10" />
																		</div>
																	</td>
																	<td width="84%" height="23">
																		<table width="95%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td height="20" style="cursor: hand"
																					onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																					onmouseout="this.style.borderStyle='none'">
																					<span class="STYLE3"><a 
																						href="<%=basePath%>servlet/OperatorManageServlet?userId=<%=Integer.toString(adminInfo.getOperatorId())%>"
																						target="mainFrame">&nbsp;&nbsp;操作员管理</a> </span>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>												
																
																
																
																<tr>
																	<td width="16%" height="25">
																		<div align="center">
																			<img src=""
																				width="10" height="10" />
																		</div>
																	</td>
																	<td width="84%" height="23">
																		<table width="95%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td height="20" style="cursor: hand"
																					onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																					onmouseout="this.style.borderStyle='none'">
																					<span class="STYLE3"><a
																						href="<%=basePath%>servlet/OperatorAddServlet" target="mainFrame">&nbsp;&nbsp;添加员工</a>
																					</span>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																
															</table>
														</td>
													</tr>
													<tr>
														<td height="5">
															<img src=""
																width="151" height="5" />
														</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
									
								</table>
							</td>
						</tr>
						<%} %>
						
					</table>
				</td>
				
			</tr>
			
		</table>

	</body>
	<script>

function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
imgmenu = eval("imgmenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
imgmenu.background="<%=basePath%>../../resource/images/main_47.gif";
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
imgmenu.background="<%=basePath%>../../resource/images/main_48.gif";
}
}

</script>
</html>
