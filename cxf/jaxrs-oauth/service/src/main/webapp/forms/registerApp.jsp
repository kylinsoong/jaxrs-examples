<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Client Application Registration Form</title>
</head>
<body>
<h1>Client Application Registration Form</h1>
<em></em>
<p>
 <table>
     <form action="/oauth/registerProvider" enctype="multipart/form-data" method="POST">
        <tr>
            <td>Application Name:</td>
            <td>
              <input type="text" name="appName" size='50' value="Restaurant Reservations"/>
            </td>
        </tr>
        <tr>
            <td>Application Description:</td>
            <td>
              <input type="text" name="appDescription" size='50' value="The online service for booking a table at the favourite restaurant"/>
            </td>
        </tr>
        <tr>
            <td>Application URI:</td>
            <td>
              <input type="text" name="appURI" size='50' value="http://localhost:8080/reservations"/>
            </td>
        </tr>
        <tr>
            <td>Application Logo:</td>
            <td>
               <input id="appLogo" name="appLogo" type="file" accept="image/gif,image/jpeg,image/png"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Register Your Application"/>
            </td>
        </tr>
  </form>
 </table> 
</body>
</html>
