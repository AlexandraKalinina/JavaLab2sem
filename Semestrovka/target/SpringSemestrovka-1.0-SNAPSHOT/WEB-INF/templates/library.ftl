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

<p><input id="query" oninput="f()"/></p>

<div id="res"></div>

<script type="application/javascript">
    function search() {
        if ($("#query").val().length >= 1) {
            $.ajax({
                url: "/search",
                method: "GET",
                data: {"query": $("#query").val()},
                dataType: "json",
                success: function (msg) {
                    if (msg.objects.length > 0) {
                        $("#res").html("");
                        for (var i = 0; i < msg.objects.length; i++) {
                            $("#res").append("<li>" + msg.objects[i].name + "</li>");
                        }
                    } else {
                        $("#res").html("No results..");
                    }
                }
            })
        }
        else {
            $("#res").html("");
        }
    }
</script>
</body>
</html>