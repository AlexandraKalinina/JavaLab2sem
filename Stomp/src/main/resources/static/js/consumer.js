    let stompClient = null;

    let thisMessage = null;

    let queue = null;
    function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        }
        else {
            $("#conversation").hide();
        }
        $("#greetings").html("");
    }

    function sendFile() {
        // данные для отправки
        let formData = new FormData();
        // забрал файл из input
        let files = ($('#file'))[0]['files'];
        // добавляю файл в formData
        [].forEach.call(files, function (file, i, files) {
            formData.append("file", file);
        });
        $.ajax({
            type: "POST",
            url: "/files",
            data: formData,
            processData: false,
            contentType: false
        })
            .done(function (response) {
                alert(response)
            })
            .fail(function () {
                alert('Error')
            });
        $("#completed").prop('disabled', false);
        $("#accepted").prop('disabled', true);

    }
    function connect() {
        let socket = new SockJS('/messages');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/chat', function (greeting) {
                showGreeting(greeting)
            });
        });
    }

    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function subscribe() {
        let queueData=formInline.elements.queueName.value;
        queue = queueData;
        let forms = {
            "nameQueue": queueData,
            "json":""
        };
        stompClient.send("/app/subscribe", {}, JSON.stringify(forms));
    }
    function next() {
        let forms = {
            "nameQueue": queue,
            "json": ""
        };
        stompClient.send("/app/subscribe", {}, JSON.stringify(forms));
    }
    function showGreeting(message) {
        $("#greetings").append("<tr><td>" + "task: " + message + "</td></tr>");
        $("#accepted").prop('disabled', false);

        thisMessage = message;
    }
    function accepted() {
        let forms = {
            "nameQueue": "",
            "json": thisMessage
        };
        $("#thisFile").prop('disabled', false);
        stompClient.send("/app/accepted", {}, JSON.stringify(forms));
    }

    function completed() {
        let forms = {
            "nameQueue": "",
            "json": thisMessage
        };
        stompClient.send("/app/completed", {}, JSON.stringify(forms));
        $("#thisFile").prop('disabled', true);
        $("#completed").prop('disabled', true);
        $("#next").prop('disabled', false);

    }

    $(function () {
        $("form").on('submit', function (e) {
            e.preventDefault();
        });
        $( "#connect" ).click(function() { connect(); });
        $( "#disconnect" ).click(function() { disconnect(); });
        $( "#send" ).click(function() { sendName(); });
    });
