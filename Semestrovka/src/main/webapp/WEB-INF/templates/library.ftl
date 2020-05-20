<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="/static/js/chat.js"></script>
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
        <form method="get" action="/searchBooks">
            <input type="submit" value="Поиск по книгам">
        </form>
    </div>
    <div class="row">
        <form method="get" action="/searchAuthor">
            <input type="submit" value="Поиск по авторам">
        </form>
    </div>
    <div class="row">
        <form method="get" action="/searchGenre">
            <input type="submit" value="Поиск по жанрам">
        </form>
    </div>


</div>

</body>
</html>