function sendMessage(userId, text, bookId, name) {
    let body = {
        userId: userId,
        text: text,
        bookId: bookId,
        name: name
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            receiveMessage(userId, name)
        }
    });
}
function search(query) {
    if (query.length >= 1) {
        $.ajax({
            url: "/search?query=" + query,
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (books) {
                if (books.length > 0) {
                    $('#res').html("");
                    for (let i = 0; i < books.length; i++) {
                        $('#res').append('<li>' + books[i] + '</li>');
                    }
                }  else {
                    $('#res').html("No results..");
                }

            }
        })
    }  else {
        $("#res").html("");
    }
}

// LONG POLLING
function receiveMessage(userId, name) {
    $.ajax({
        url: "/messages?userId=" + userId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<h3>' + response[0]['text'] + " "+ name + '</h3>');
            receiveMessage(userId, name);
        }
    })
}