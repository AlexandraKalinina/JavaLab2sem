<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>Registration</h1>
<div>
    <form method="post" action="/registration">
        <input name="name" placeholder="Name">
        <input name="email" placeholder="Email">
        <input type="password" name="password" placeholder="Password">
        <div>
            <h3>Введите CONSUMER или PRODUCER</h3>
            <input name="role" placeholder="Role">
        </div>
        <input type="submit" value="REGISTRATION">
    </form>
</div>
</body>
</html>