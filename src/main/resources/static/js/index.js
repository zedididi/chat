//初始化好友列表
function initFriend() {
    var userId = document.getElementById("userIf").alt;
    var friendIds = "";
    xmlHttp.open("POST", "/friend/getAll", true);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                var data = xmlHttp.responseText;
                var obj = JSON.parse(data);
                var listFriend = '';
                var chatWindowDivs = "";
                for (var i in obj) {
                    var friendId = obj[i].friendId;
                    friendIds += friendId +" ";
                    var userId = obj[i].userId;
                    var image = obj[i].image;
                    var friendName = obj[i].friendName;
                    var li_id = friendId+"li";
                    var tip_id = friendId +"tip";
                    var status_id = friendId +"status";

                    chatWindowDivs = '<div class="chat" id=' +friendId +' data-chat="' +friendId +'"></div>';
                    listFriend += '<li class="person" data-chat="' + friendId + '">' +
                        '<img id="hook"  src="' + image + '" alt="' + friendId + '" />' +
                        '<span class="name">' + friendName + '</span>' +
                        '</li>';
                    chatWindowDivs = '<div style="" class="chat" id="' +friendId +'" data-chat="' +friendId +'"></div>';
                    $("#write").before(chatWindowDivs);
                }
                document.getElementById("people").innerHTML += listFriend;
                boolOnline(friendIds +" " + userId);
                init();
                initChatRecord()
            }
        }

        $("#hook,#msg-box").bind("mouseover",showMsgBox);


    };
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttp.send("userId=" + userId);

}

//好友列表好友
function friend_initFriend() {
    var userId = document.getElementById("friend_userIf").alt;
    // var userId=$("img#userIf").attr("alt");
    xmlHttp.open("POST", "/friend/getAll", true);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                var data = xmlHttp.responseText;
                var obj = JSON.parse(data);
                var listFriend = '';
                var chatWindowDivs = '';
                for (var i in obj) {
                    var friendId = obj[i].friendId;
                    var image = obj[i].image;
                    var friendName = obj[i].friendName;
                    listFriend += '<li class="person" data-chat="' + friendId + '">' +
                        '<img id="hook"  src="' + image + '" alt="' + friendId + '" />' +
                        '<span class="name">' + friendName + '</span>' +
                        '</li>';
                    chatWindowDivs = '<div style="" class="chat" id="' +friendId +'" data-chat="' +friendId +'"></div>';
                    $("#write").before(chatWindowDivs);
                }
                document.getElementById("friend_people").innerHTML += listFriend;
                init();
                initChatRecord()
            }
        }
        $("#container_friend_left_top_head").bind("mousedown",showFriendMsgBox);
    };
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttp.send("userId=" + userId);

}

//进行相关信息的初始化,并连接服务器
function init() {
    document.querySelector('.person').classList.add('active');//使person处于选中状态
    document.querySelector('.chat').classList.add('active-chat');//选择person处于正在聊天状态
    $(".right .top .name").text($(".active .name").text());
    $(".right .top .status").text($(".active .status").text().substring(1,3))

    var friends = {
            list: document.querySelector('ul.people'),//获取好友列表
            all: document.querySelectorAll('.left .person'),//获取所有好友
            name: '' },

        chat = {
            container: document.querySelector('.container .right'),//获取聊天栏
            current: null,
            person: null,
            name: document.querySelector('.container .right .top .name') };//获取聊天对象名称


    var sendId = $("#userIf").attr("alt"); //获取当前登录用户的userID
    var receiveId = $(".active").attr("data-chat"); //获取接收者的ID
    initChatRecord(receiveId);

    // 监听鼠标点击事件，使点击对象处于active状态
    friends.all.forEach(function (f) {
        f.addEventListener('mousedown', function () {
            f.classList.contains('active') || setActiveChat(f);
        });
    });


    $(".chat").click(function () {
        $("#expression").css("visibility","hidden");
        $("#expressionPackage").css("visibility","hidden");
    })

    function setActiveChat(f) {
        friends.list.querySelector('.active').classList.remove('active');//选择处于active状态的person，并消除刚刚点击的person的active状态
        f.classList.add('active');
        chat.current = chat.container.querySelector('.active-chat');//使显示的chat处于active-chat状态
        chat.person = f.getAttribute('data-chat');//使person为选中的data-chat
        receiveId = chat.person; //接收者的ID
        initChatRecord(receiveId);
        chat.current.classList.remove('active-chat');//消除chat的active-chat状态
        chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');//使聊天框中显示选中的person的active-chat
        friends.name = f.querySelector('.name').innerText;//获得聊天对象的name
        chat.name.innerHTML = friends.name;
        $(".right .top .status").text($(".active .status").text().substring(1,3))
        f.querySelector(".tip").style.visibility = "hidden";
        f.querySelector(".tip").innerText ="";
    }

    //返回webSocket对象
    function getWebSocket() {
        var webSocket = new WebSocket("ws://localhost:8080/chatRoom/"+sendId);
        
        webSocket.onopen = function (ev) {
            console.log("用户打开了连接");
        }

        webSocket.onclose = function (ev) {
            console.log("用户关闭了连接")
        }

        webSocket.onerror = function (ev) {
            console.info("发生了错误")
        };

        webSocket.onmessage = function (ev) {  //当有好友发送消息时进行消息的显示
            if (ev.data.trim().length <= 15){
                var status_id = ev.data.split(" ")[0] +"status";
                if (ev.data.split(" ")[1] == "loginIn"){
                    document.getElementById(status_id).innerText = "[在线]";
                    if ($(".active").attr("data-chat") == ev.data.split(" ")[0]) $(".right .top .status").text("在线");
                } else {
                    document.getElementById(status_id).innerText ="[离线]";
                    if ($(".active").attr("data-chat") == ev.data.split(" ")[0]) $(".right .top .status").text("离线");
                }
            } else {
                var obj = JSON.parse(ev.data);
                var sendId = obj.sendId;
                var receiveId = obj.receiveId;
                var createTime = obj.createTime.substring(0,19);
                var content = obj.content;
                document.getElementById("sound").play();
                if ($(".active").attr("data-chat") == sendId){  //如果此时，正好打开了与发送消息的好友的窗口,直接显示
                    var time = document.getElementById($(".active").attr("data-chat")).getElementsByClassName("conversation-start");
                    var lastTime;
                    if (time.length == 0) lastTime ="2010-12-22 11:08:29";
                    else  lastTime = time[time.length-1].innerText;
                    if (judgeShowTime(createTime,lastTime)){
                        if (boolToday(createTime))
                            $(".active-chat").append('<div class=\"conversation-start\"><span>'+createTime.split(" ")[1]+'</span></div>');
                        else
                            $(".active-chat").append('<div class=\"conversation-start\"><span>'+createTime+'</span></div>');
                    }
                    $(".active-chat").append("<div class='bubble you'>"+content+"</div>")
                } else { //如果此时没有打开与发送消息好友的窗口,则给出提醒
                    var li_id = sendId +"li";
                    var person = document.getElementById(li_id);
                    var tip_id = sendId +"tip";
                    var tip = document.getElementById(tip_id);
                    var status_id = sendId +"status";
                    var status = document.getElementById(status_id).innerText;
                    var unread;
                    if (tip.innerText == "") unread = 1;
                    else unread = parseInt(tip.innerText)+1;
                    var img_url = person.getElementsByTagName("img")[0].getAttribute("src");
                    var friendName = person.getElementsByTagName("span")[0].innerText;
                    $("#people").prepend('<li class="person" data-chat="'+sendId +'" id="'+li_id+'">' +
                        '<img src="'+img_url+'" alt ="'+receiveId+'"/><span class="name">' +friendName +'</span><span class="status" id="'+status_id+'">'+status+'</span><span class="tip" ' +
                        'id="'+tip_id+'" style="visibility: visible">'+unread+'</span></li> ')
                    person.parentNode.removeChild(person);
                    friends = {
                        list: document.querySelector('ul.people'),//获取好友列表
                        all: document.querySelectorAll('.left .person'),//获取所有好友
                        name: '' };
                    chat = {
                        container: document.querySelector('.container .right'),//获取聊天栏
                        current: null,
                        person: null,
                        name: document.querySelector('.container .right .top .name') };//获取聊天对象名称
                    friends.all.forEach(function (f) {
                        f.addEventListener('mousedown', function () {
                            f.classList.contains('active') || setActiveChat(f);
                        });
                    });
                }
            }
        }

        return webSocket;
    }

    //用户与服务器进行连接
    var webSocket = getWebSocket();

    //用户发送自己的图片
    $("#inputImage").change(function () {
        var file = document.getElementById("inputImage").files[0];
        if (file == null) return;
        var formData = new FormData();
        formData.append("file",file)
        formData.append("sendId",sendId);
        formData.append("receiveId",receiveId);
        $.ajax({
            url: "/chatRoom/upload/picture",
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (ev) {
                $(".div-textarea").html("<img class='userImg chatPicture' src=" + ev + ">");
                $("#chat-fasong").click();
            }
        })
    })


    //发送功能
    $("#chat-fasong").click(function () {
        var textContent = $(".div-textarea").html().replace(/[\n\r]/g, '<br>')
        if (textContent != "") {
            var msg = $(".div-textarea").html();
            var content = {
                "sendId": $("#userIf").attr("alt"),
                "receiveId":$(".active").attr("data-chat"),
                "content":$(".div-textarea").html()
            }
            webSocket.send(JSON.stringify(content));
            $.ajax({
                url:"/chatRoom/send",
                type:"post",
                data: JSON.stringify(content),
                contentType : "application/json;charset=utf-8",
                success:function (ev) {
                    var time = document.getElementById($(".active").attr("data-chat")).getElementsByClassName("conversation-start");
                    var lastTime;
                    if (time.length == 0) lastTime ="2010-12-22 11:08:29";
                    else  lastTime = time[time.length-1].innerText;
                    var currentTime = ev.trim().substring(0,19);
                    if (judgeShowTime(currentTime,lastTime))
                        if (boolToday(currentTime)){
                            $(".active-chat").append('<div class=\"conversation-start\"><span>'+currentTime.split(" ")[1]+'</span></div>');
                        }
                        else $(".active-chat").append('<div class=\"conversation-start\"><span>'+currentTime+'</span></div>');
                    $(".active-chat").append("<div class='bubble me'>"+msg+"</div>")
                    //发送后清空输入框
                    $(".div-textarea").html("");
                }
            })
        }else {
            alert("发送内容不能为空");
        }
    });

    //语音发送功能
    if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia){
        navigator.mediaDevices.getUserMedia({
            "audio":true
        })
            .then(function (value) {
                var mediaRecorder = new MediaRecorder(value);
                var start,end;
                var chunks = [];

                $("#chat-audio").mousedown(function () {
                    start = new Date();
                    mediaRecorder.start();
                    $(this).css({
                        "width":"44px",
                        "height":"40px",
                        "top":"2px",
                        "left":"374px"
                    })
                })

                mediaRecorder.ondataavailable = function(ev){
                    chunks.push(ev.data)
                }

                $("#chat-audio").mouseup(function () {
                    end = new Date();
                    mediaRecorder.stop();
                    $(this).css({
                        "width":"22",
                        "height":"20px",
                        "left": "384px",
                        "top": "12px"
                    })
                })

                mediaRecorder.onstop = function (ev) {
                    if (end.getTime() - start.getTime() <1000){
                        alert("录音时间过短");
                    }else {
                        var blob = new Blob(chunks,{"type":'audio/mp3;codecs=opus'})
                        var formData = new FormData();
                        formData.append("audio",blob);
                        formData.append("sendId",sendId);
                        $.ajax({
                            url: "/chatRoom/upload/audio",
                            type: "POST",
                            data: formData,
                            contentType: false,
                            processData: false,
                            success: function (ev) {
                                var second = Math.round((end.getTime() -start.getTime())/1000);
                                $(".div-textarea").html('<img class="soundImg" onclick="sound(this)" src="img/soundMsg.png" alt="'+ev+'">'+'<span style="margin-left: 10px">'+second+"</span>");
                                $("#chat-fasong").click();
                            }
                        })
                        chunks = [];
                    }
                }
            })
            .catch(function (reason) {
                alert("请打开录音权限")
            })
    }
}

//播放语音消息
function sound(img) {
    var soundURL = img.getAttribute("alt");
    $("#msgAudio").attr("src",soundURL);
    document.getElementById("msgAudio").play();
}


//点击哪位好友,生成其聊天记录
function initChatRecord(contactId) {
    var userId = $("#userIf").attr("alt");
    $.get("chatRoom/" +userId+"/"+contactId,function (data,status) {
        var obj = jQuery.parseJSON(data);
        var show = true;
        var openedChatWindow = document.getElementById(contactId);
        openedChatWindow.innerHTML = "";
        for (var i in obj){
            var content = obj[i].content;
            var sendTime = obj[i].createTime;
            var sendId = obj[i].sendId;
            var receiveId = obj[i].receiveId;
            if (show){
                if (boolToday(sendTime))
                    openedChatWindow.innerHTML += '<div class=\"conversation-start\"><span>'+sendTime.split(" ")[1]+'</span></div>';
                else
                    openedChatWindow.innerHTML += '<div class=\"conversation-start\"><span>'+sendTime+'</span></div>';
                show = false;
            }
            var time = document.getElementById(contactId).getElementsByClassName("conversation-start");
            var lastTime = time[time.length-1].innerText;
            if (judgeShowTime(sendTime,lastTime))
                if (boolToday(sendTime))
                    openedChatWindow.innerHTML += '<div class=\"conversation-start\"><span>'+sendTime.split(" ")[1]+'</span></div>';
                else
                    openedChatWindow.innerHTML += '<div class=\"conversation-start\"><span>'+sendTime+'</span></div>';

            if (userId == sendId)
                openedChatWindow.innerHTML += '<div class=\"bubble me\">' + content +'</div>';
            else
                openedChatWindow.innerHTML += '<div class=\"bubble you\">' + content +'</div>';
        }
    })
}

//判断时间是否需要显示,此次发送时间与上一次显示的时间间隔3分钟以上就返回true,并进行时间的显示
function judgeShowTime(currentTime,pastTime) {
    var reg = new RegExp("[^0-9]");
    var current = currentTime.trim().split(reg);
    var past = pastTime.trim().split(reg)
    var current_year = parseInt(current[0]);var current_month = parseInt(current[1]);
    var current_day = parseInt(current[2]); var current_hour = parseInt(current[3]);
    var current_minute = parseInt(current[4]);var current_second = parseInt(current[5]);

    if (pastTime.length == 19){
       var past_year = parseInt(past[0]);
       var past_month = parseInt(past[1]);
       var past_day = parseInt(past[2]);
       var past_hour = parseInt(past[3]);
       var past_minute = parseInt(past[4]);
       var past_second = parseInt(past[5]);
       if (current_year>past_year || current_month>past_month || current_day > past_day ||current_hour>past_hour) return true;
       else if ((current_minute*60+current_second) >= (past_minute*60+past_second + 180)) return true;
       else return false;
    }else {
        var past_hour = parseInt(past[0]);
        var past_minute = parseInt(past[1]);
        var past_second = parseInt(past[2]);
        if (current_hour>past_hour)  return true;
        else if ((current_minute*60+current_second) >= (past_minute*60+past_second + 180)) return true;
        else return false;
    }
}

//判断是否为当天,如果是当天的话,返回false,不显示年月日
function boolToday(time) {
    var reg = new RegExp("[^0-9]");
    var time_array = time.split(reg);
    var date = new Date();
    return (time_array[0] == date.getFullYear() && time_array[1] == date.getMonth()+1 && time_array[2] == date.getDate());
}

//返回好友的在线情况
function boolOnline(friend_id) {
    $.ajax({
        url: "/chatRoom/status",
        type:"POST",
        data: "friend_id="+friend_id,
        data_type:"json",
        async: false,
        success:function(ev){
            var friendIds = friend_id.trim().split(" ");
            var status = ev.split(" ");
            for (var i = 0;i<friendIds.length-2;i++){
                var status_id = friendIds[i].trim() +"status";
                if (status[i] == "true"){
                    document.getElementById(status_id).innerText = "[在线]";
                }
                 else{
                    document.getElementById(status_id).innerText = "[离线]";
                }
            }
        }
    })
}

