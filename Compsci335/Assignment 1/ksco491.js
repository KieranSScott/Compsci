function getBooks() {
    var uri = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/booklist"
    var xhr = new XMLHttpRequest();
    xhr.open("GET", uri, true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.onload = function() {
        var resp = JSON.parse(xhr.responseText);
        showBooks(resp);
    }
    xhr.send(null);
}

function showBooks(books) {
    var tableContent = "<tr><td>Book</td><td>Title</td></tr>\n ";
    for (var i = 0; i < books.length; ++i) {
        var book = books[i];
        console.log(book.Title);
        var image = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/bookimg?id=" + book.Id;
        tableContent += "<td>" + "<img src='" + image + "'><img/>  </td><td>" + book.Title + "</td></tr>\n ";
    }
    document.getElementById("showBook").innerHTML = tableContent;
}

function getBrs() {
    var uri = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/brlist"
    var xhr = new XMLHttpRequest();
    xhr.open("GET", uri, true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.onload = function() {
        var resp = JSON.parse(xhr.responseText);
        showBrs(resp);
    }
    xhr.send(null);
}

function showBrs(brs) {
    var tableContent = "<tr><td>BluRay</td><td>Title</td></tr>\n ";
    for (var i = 0; i < brs.length; ++i) {
        var br = brs[i];
        var image = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/brimg?id=" + br.Id;
        tableContent += "<td>" + "<img src='" + image + "'><img/>  </td><td>" + br.Title + "</td></tr>\n ";
    }
    document.getElementById("showBr").innerHTML = tableContent;
}

function openTab(evt, tabName) {    // Declare all variables
       
    var i, tabcontent, tablinks;

        // Get all elements with class="tabcontent" and hide them
       
    tabcontent = document.getElementsByClassName("tabcontent");   
    for (i = 0; i < tabcontent.length; i++) {       
        tabcontent[i].style.display = "none";   
    }

        // Get all elements with class="tablinks" and remove the class "active"
       
    tablinks = document.getElementsByClassName("tablinks");   
    for (i = 0; i < tablinks.length; i++) {       
        tablinks[i].className = tablinks[i].className.replace(" active", "");   
    }

        // Show the current tab, and add an "active" class to the button that opened the tab
       
    document.getElementById(tabName).style.display = "block";   
    evt.currentTarget.className += " active";
}

document.getElementById("defaultOpen").click();

function postComment() {
    var name = document.getElementById('name').value;
    var comment = document.getElementById('comment').value;
    var uri = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/comment?name=" + name;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", uri, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(comment));
    var iframe = document.getElementById('comments');
    iframe.src = iframe.src;
}

function bookSearch() {
    var input = document.getElementById('bookSearchBar').value;
    var uri = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/booksearch?term=" + input;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", uri, true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.onload = function() {
        var resp = JSON.parse(xhr.responseText);
        showBooks(resp);
    }
    xhr.send(null);
}

function brSearch() {
    var input = document.getElementById('brSearchBar').value;
    var uri = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/brsearch?term=" + input;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", uri, true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.onload = function() {
        var resp = JSON.parse(xhr.responseText);
        showBrs(resp);
    }
    xhr.send(null);
}
