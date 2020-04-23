<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чат</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        var sockJS;
        function connect() {
            sockJS =  new SockJS('http://localhost:8080/chat');

            document.cookie = 'X-Authorization=' + '12345' + ';path=/';

            sockJS.onmessage = function receiveMessage(response) {
                let data = response['data'];
                let json = JSON.parse(data);
                $('#messagesList').first().after("<li>" + json['from'] + ' ' + json['text'] + "</li>")
            }
        }

        function sendMessage(text, userId) {
            let message = {
                "text": text,
                "from": userId
            };

            sockJS.send(JSON.stringify(message));
        }
    </script>
</head>
<body onload="connect()">
<div>
    <label for="message">Текст сообщения</label>
    <input name="message" id="message" placeholder="Сообщение">
    <button onclick="sendMessage($('#message').val(), '${userId}')">Отправить</button>
    <h3>Сообщения</h3>
    <ul id="messagesList">

    </ul>
</div>
</body>

</html>

