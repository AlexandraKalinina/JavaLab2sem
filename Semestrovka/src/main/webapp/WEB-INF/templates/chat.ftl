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
<body onload="sendMessage('${userId}', 'Login', '${bookId}', ${name})">
<h1>Ваш идентификатор - ${userId}</h1>
<div>
    <input id="message" placeholder="Ваше сообщение">
    <button onclick="sendMessage('${userId}',
            $('#message').val(), '${bookId}', '${name}')">Отправить</button>
</div>
<div>
    <div id="messages">

    </div>
</div>
<div>
    <#list messages as message>
        <h4>${message.text} ${message.user.name}</h4>
    </#list>
</div>


</body>
</html>