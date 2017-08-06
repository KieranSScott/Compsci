function getBooks() {
    console.log("hi")
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
    var tableContent = "<tr><td class='image'>Book</td><td class='title'>Title</td><td class='buy'></td></tr>\n ";
    for (var i = 0; i < books.length; ++i) {
        var book = books[i];
        var image = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/bookimg?id=" + book.Id;
        var id = JSON.stringify(book.Id);
        tableContent += "<td class='image'><img src='" + image + "' width='200px' height='300px'><img/>  </td><td class='title'>" + book.Title + "</td><td class='buy'> <input type='image' src='buy.jpg' name='buyItem' id='butItem' width='100px' height='50px' onclick='buyBook("+ id+")'/>  </td></tr>\n ";
    }
    document.getElementById("showBook").innerHTML = tableContent;
    console.log(book.Id);
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
    var tableContent = "<tr><td class='image'>Book</td><td class='title'>Title</td><td class='buy'></td></tr>\n ";
    for (var i = 0; i < brs.length; ++i) {
        var br = brs[i];
        var image = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/brimg?id=" + br.Id;
        var id = JSON.stringify(br.Id);
        tableContent += "<td class='image'><img src='" + image + "'width='200px' height='300px'><img/>  </td><td class='title'>" + br.Title + "</td><td class='buy'><input type='image' src='buy.jpg' name='buyItem' id='butItem' width='100px' height='50px' onclick='buyBr(" + id +")'/></td></tr>\n ";
    }
    document.getElementById("showBr").innerHTML = tableContent;
}

function changeTab(event, tabName) {     
    var tabBtn = document.getElementsByClassName("tabBtn");
    var tabcontent = document.getElementsByClassName("tabcontent");   
    for (var i = 0; i < tabcontent.length; i++) {       
        tabcontent[i].style.display = "none"; 
        tabBtn[i].className = tabBtn[i].className.replace(" active", "");
    } 
    event.currentTarget.className += " active";
    document.getElementById(tabName).style.display = "block";   
    
}

window.onload = function() {
	document.getElementById("default").click();
}

function postComment() {
    var name = document.getElementById('name').value;
    var comment = document.getElementById('comment').value;
    var uri = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/comment?name=" + name;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", uri, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(comment));
    xhr.onload = function () {
        var iframe = document.getElementById('comments');
        iframe.src = iframe.src;
    }
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

function register() {
    var login = document.getElementById('login').value;
    var password = document.getElementById('password').value;
	var address = document.getElementById('address').value;
    var registration = {
        Address: address,
        Name: login,
        Password: password,
    }
    var xhr = new XMLHttpRequest();
    var uri = "http://redsox.uoa.auckland.ac.nz/BC/Open/Service.svc/register"
    xhr.open("POST", uri, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(registration));
    xhr.onload = function () {
        alert("You are now registered " + login);
    }
}

function buyBook(Id) {
	var uri = " http://redsox.uoa.auckland.ac.nz/BC/Closed/Service.svc/bookbuy?id=" + Id;
	window.open(uri, "_blank");
    
}

function buyBr(Id) {
	var uri = "http://redsox.uoa.auckland.ac.nz/BC/Closed/Service.svc/id" + Id;
	window.open(uri, "_blank");
}
