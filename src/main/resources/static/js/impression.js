var n = false;

function randomColor() {
    var arrHex = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"];
    var strHex = "#";
    var index;
    for (var i = 0; i < 6; i++) {
        index = Math.round(Math.random() * 15);
        strHex += arrHex[index];
    }
    return strHex;
}
function changeColor() {
    var links = document.getElementsByTagName("a");
    for (var i = 0; i < links.length; i++) {
        var bgColor = randomColor();
        links[i].style.backgroundColor = links[i].style.borderColor = bgColor;
    }
}
function sayHi() {
    var scolor, links = document.getElementsByTagName("a");
    for (var i = 0; i < links.length; i++) {
        links[i].onmouseover = function () {
            scolor = this.style.backgroundColor;
            this.style.color = scolor;
            this.style.borderColor = scolor;
            this.style.backgroundColor = "white";
        };
        links[i].onmouseout = function () {
            this.style.color = "white";
            this.style.backgroundColor = this.style.borderColor = scolor;
        }
    }
}
function addEvaluation() {
    var txt = document.getElementById("input-txt");
    var btn = document.getElementById("input-btn");
    var divs = document.getElementsByClassName("impression")[0];
    if (!txt) return false;
    if (!btn) return false;
    var texts, links, spans;
    txt.onfocus = function () {
        btn.className = "cur";
    };
    txt.onblur = function () {
        btn.className = "";
    };

    btn.onclick = function () {
        window.console.info("btn:"+btn.alt);

        if (txt.value == "") {
            alert("请输入一个印象词");
            return false;
        }

        texts = document.createTextNode(txt.value);
        var friendId=btn.alt;
        var eva=txt.value;
        initAJAX();
        xmlHttp.open("POST", "/eva/add", true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlHttp.send("id=" + friendId+"&eva="+eva);

        links = document.createElement("a");
        spans = document.createElement("span");
        links.appendChild(texts);
        links.style.backgroundColor = links.style.borderColor = randomColor();
        spans.appendChild(links);
        divs.appendChild(spans);
        sayHi();
        n = true;
        txt.value="";
    }
}
changeColor();
sayHi();
addEvaluation();