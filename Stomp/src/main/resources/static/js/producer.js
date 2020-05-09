let stompClient = null;

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

function sendData()
{
    let queueData=formInline.elements.queueName.value;
    let emailData=formInline.elements.email.value;
    let nameData=formInline.elements.name.value;
    let json = emailData + "+++" + nameData;
    let forms = {
        "nameQueue": queueData,
        "json": json
    };
    stompClient.send("/app/send", {}, JSON.stringify(forms));

}
function connect() {
    let socket = new SockJS('/messages');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat', function (greeting) {
            showGreeting(greeting);
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



function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});
