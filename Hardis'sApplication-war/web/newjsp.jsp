<script language="javascript">
function tbladdrow()
{
        var i=lines.rows.length;
        var row = Table1.insertRow(Table1.rows.length);
        var col = row.insertCell(0);
        col.innerHTML = "<INPUT id=line["+i+"][name1] name=line"+i+"name1 SIZE=10>";
        col = row.insertCell(1);
        col.innerHTML = "<INPUT id=line["+i+"][name2]  name=line"+i+"name2 SIZE=10>";
        col = row.insertCell(2);
        col.innerHTML = "<INPUT id=line["+i+"][name3] name=line"+i+"name3 SIZE=10>";
        col = row.insertCell(3);
        col.innerHTML = "<INPUT id=line["+i+"][name4] name=line"+i+"name4 SIZE=10>";
}
function tbladdrows(items)
{
        for( i = 1 ; i <= items ; i++)
        {
                tbladdrow();
        }
}
function delrow()
{
        if(lines.rows.length==0)
        {
                return false  ;
        }
        lines.deleteRow();
}

</script>

****************html页其它代码:
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=Big5">
    <title>輸入</title>
  </head>
  <body>
 <form action="getInput.jsp">
  <DIV align=center><FONT size=2>輸入測試</FONT></DIV></TD>
    <TD width=412 bgColor=#e4e4e4>
      <TABLE id=Table1 cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width=350 align=center borderColorLight=#000000 border=1>
        <TBODY>
        <TR bgColor=#999999>
          <TH width=94><FONT size=2>參數一</FONT></TH>
          <TH width=84><FONT size=2>參數二</FONT></TH>
          <TH width=85><FONT size=2>參數三</FONT></TH>
          <TH width=77><FONT size=2>參數四</FONT></TH></TR>
        <TBODY id=lines name="lines"><!--
         <TR>
             <TD><INPUT id="things_name" TYPE="text" NAME="things_name" SIZE="15"></TD>
             <TD><INPUT id="things_model" TYPE="text" NAME="things_model" SIZE="10"></TD>
             <TD><INPUT id="things_number" TYPE="text" NAME="things_number" SIZE="5"></TD>
             <TD><input id="things_unit" type="text" name="things_unit" size="5"></TD>
         </TR>
                         --></TBODY></TABLE>
      <DIV align=center><BR></DIV>
      <DIV align=center>
      <INPUT id=items type=hidden value=1 name=items> <INPUT οnclick=tbladdrow(items.value) type=button value=新增一行 name=insert/>
      <font color="#e4e4e4"> </font>&nbsp;&nbsp;
      <INPUT language=javascript οnclick="return delrow()" type=button value=刪除一行 name=del/>
      </DIV></TD></TR></TBODY></TABLE>
<input type="submit" value="commit"/>
</form>
  </body>
</html>

***jsp页收集输入
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>

<%
request.setCharacterEncoding("UTF-8");

Enumeration paramNames=request.getParameterNames();
String paramName=null;
while(paramNames.hasMoreElements()){
paramName=(String)paramNames.nextElement();

String param=request.getParameter(paramName);

//out.println(paramName+"   "+param+"<br>");

 String paramValues[]=request.getParameterValues(paramName);
 for(int i=0; i<=paramValues.length-1; i++)
{
out.println(paramName+"  ");
out.println(paramValues[i]+"<br>");}

}



//out.println("<br>"+name);

%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>接受輸入</title>
  </head>
  <body>
  </body>
</html>