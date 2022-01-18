<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <script>
            const TestingControllerDog = async () => {
                const req = await fetch('http://localhost:8080/TestAPIRestServlet/api/dogs', {
                    method: "POST",
                    headers: {
                        "Content-Type": "json/application"
                    },
                    body: JSON.stringify(
                        {
                            "idRaceDog": 4,
                            "idSizeDog": 1,
                            "name": "Un perro esdrújula",
                            "description": "Es un perro mutante.",
                            "owner": "Maycol Soto",
                            "weight": 90
                        }
                    )
                });
                const res = await req.json();
                console.log(res);
            }

       </script>
    </body>
</html>
