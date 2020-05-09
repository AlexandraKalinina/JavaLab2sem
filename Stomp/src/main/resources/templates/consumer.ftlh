<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/consumer.js"></script>
    <title>Document</title>

</head>
<body>

<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" type="submit">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline" id="formInline"name="sendForms">
                <h3>Введите имя очереди</h3>
                <input type="text" id="queueName"name="queueName" placeholder="Queue name here...">
                <button onclick="subscribe()">Подписаться</button>
            </form>
        </div>

    </div>

    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>Greetings</th>
                </tr>
                </thead>
                <tbody id="greetings">
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <button id="accepted" onclick="accepted()" disabled = "disabled">Взять задачу</button>
        <div>
            <input type="file" id="file" name="file" placeholder="Имя файла..."/>
            <button onclick="sendFile()" id="thisFile" disabled = "disabled">
                Загрузить файл
            </button>
            <input type="hidden" id="file_hidden">
            <div class="filename"></div>
        </div>
        <div class="row">
            <button onclick="completed()" id="completed" disabled = "disabled">
                Завершить задачу
            </button>
        </div>
        <div class="row">
            <button onclick="next()" id="next" disabled = "disabled">
                Подписаться на следующую задачу
            </button>
        </div>
    </div>

</div>
</body>
</html>