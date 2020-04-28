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
<div>
    <input id="query" placeholder="Поиск"/>
    <button onclick="search($('#query').val())">Поиск</button>
</div>


<div id="res">
    <#list theseBooks as b>
        <h3>${b.name}</h3>
    </#list>
</div>
</body>
</html>