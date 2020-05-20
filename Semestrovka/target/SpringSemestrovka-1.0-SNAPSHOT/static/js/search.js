let listBooks = [];
let arrSplit = [];

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
                        arrSplit = books[i].split('+++');
                        listBooks.push(arrSplit[1]);
                        /*$('#res').append('<li>' + books[i] + '</li>');*/
                        $('#res').append('<a href=' + "/sequence?id=" + arrSplit[1] + '>' + arrSplit[0] + '</a>');
                    }
                }  else {
                    $('#res').html("No results..");
                }

            }
        });
    }  else {
        $("#res").html("");
    }
}
let listAuthors = [];
let arrSplitA = [];

function authors(query) {
    if (query.length >= 1) {
        $.ajax({
            url: "/searchAuthors?query=" + query,
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (authors) {
                if (authors.length > 0) {
                    $('#res').html("");
                    for (let i = 0; i < authors.length; i++) {
                        arrSplitA = authors[i].split('+++');
                        listAuthors.push(arrSplitA[1]);
                        $('#res').append('<a href=' + "/sequenceAuthor?id=" + arrSplitA[3] + '>'
                            + arrSplitA[0] + " " + arrSplitA[1]
                            + " " + arrSplitA[2] + '</a>');
                    }
                }  else {
                    $('#res').html("No results..");
                }

            }
        });
    }  else {
        $("#res").html("");
    }
}