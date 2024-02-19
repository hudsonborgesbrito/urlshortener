<%@ page import="com.studyjavaee.servlet.URLShortenerServlet" %><%--
  Created by IntelliJ IDEA.
  User: hudso
  Date: 17/02/2024
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shortened Result</title>
</head>
<body>
    <table>
        <tr>
            <td>Shortened URL</td>
            <td>Original URL</td>
        </tr>
        <tr>
            <td>
                <%= request.getAttribute(URLShortenerServlet.SHORT_URL) %>
                <input type="button" onclick="copyText('<%= request.getAttribute(URLShortenerServlet.SHORT_URL) %>');" value="Copy to clipboard" />
            </td>
            <td>
                <%= request.getAttribute("longURL") %>
            </td>
        </tr>
    </table>
    <br/>
    <form action="./">
        <input type="submit" value="back" />
    </form>
    <script>
        function copyText(textToBeCopied) {
            navigator.clipboard.writeText(textToBeCopied)
                .then(() => {
                    alert("Successfully copied: " + textToBeCopied);
                })
                .catch(() => {
                    alert("Could not copy to clipboard");
                });
        }
    </script>
</body>
</html>
