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
<h1>
    <#list authors as author>
        <h3>${author.name} ${author.surname} ${author.patronymic}</h3>
    </#list>
</h1>
<h3>Список книг:</h3>
<div class="block">
    <#list books as book>
        <h1>${book.name}</h1>
    </#list>
</div>
</body>
</html>