<html>
<head>
<title>Curso JSP (Java Server Pages)</title>
</head>
<body bgcolor=white>

<table border="0">
<tr>
<td align=center>
<img src="../images/java_logo.gif">
</td>
<td><h1>JSP: jsp-plugin</h1></td></tr>
</table>


<TABLE BORDER=5 ALIGN="CENTER">
<TR><TH CLASS="TITLE">Using jsp:plugin</TABLE>
<P>
<CENTER>
<jsp:plugin type="applet" 
            code="applet.ShadowedTextApplet.class"
            width="475" height="350">
  <jsp:params>
    <jsp:param name="MESSAGE" value="Your Message Here" />
  </jsp:params>
</jsp:plugin>
</CENTER>

</BODY>
</HTML>
