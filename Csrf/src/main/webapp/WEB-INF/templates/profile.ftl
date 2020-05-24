<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    .error {
        color: #ff0000;
    }
</style>
<body>
<div>
    <h1><@spring.message 'profile.page.welcome'/></h1>
    <h1><@spring.message 'profile.page.good.work'/></h1>
    <h2>${user.email}</h2>
    <h2>${user.name}</h2>
</div>
<div>
    <h3>Profile</h3>
</div>
</body>
</html>
