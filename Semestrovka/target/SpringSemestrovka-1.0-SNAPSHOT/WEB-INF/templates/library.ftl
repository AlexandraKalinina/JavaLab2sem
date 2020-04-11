<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <h1>Welcome!</h1>
        <form method="get" action="/library/signUp">
            <input type="submit" value="SignUp">
        </form>
        <form method="get" action="/library/signIn">
            <input type="submit" value="SignIn">
        </form>
    </div>
    <div class="row">
        <h1>Books</h1>
        <form method="get" action="/library/book">
            <input type="submit" value="book">
        </form>
    </div>
</div>
</body>
</html>