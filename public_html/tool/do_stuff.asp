<%@Language="JScript"%><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC
  "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>Form Iframe Demo</title>
  </head>
  <body>
  <% if (Request.Form.Count) { %>
  You typed: <%=Request.Form("someText").Item%>
  <% } else { %>
  (not submitted)
  <% } %>
  </body>
</html>
