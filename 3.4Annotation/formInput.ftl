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
<h1>SignIn</h1>
<div>
    <form method="${method}" action="${action}">
        <#list inputs as i>
            <input type="${i.getType()}" name="${i.getName()}" placeholder="${i.getPlaceholder()}">
        </#list>
    </form>
</div>
</body>
</html>