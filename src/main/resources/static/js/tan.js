

function tan() {
    var btn = document.getElementById('topen_btn');
    var div = document.getElementById('tan_background');
    var close = document.getElementById('tclose-button');

    btn.onclick = function show() {
        div.style.display = "block";
    }

    close.onclick = function close() {
        div.style.display = "none";
    }

    window.onclick = function close(e) {
        if (e.target == div) {
            div.style.display = "none";
        }
    }
}

