function initFriend() {
   var userId = document.getElementById("userIf").alt;
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
                document.getElementById("people").innerHTML += listFriend;

                init();
                initChatRecord()
            }
        }

        $("#hook,#msg-box").bind("mouseover",showMsgBox);

    };
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttp.send("userId=" + userId);

}


//与服务端连接
function getWebSocket() {

    var sendId = $("#userIf").attr("alt");
    var webSocket = new WebSocket("ws://localhost:8080/chatRoom/"+sendId);

    webSocket.onopen = function (ev) {
        window.console.info("发出连接请求");
    };

    webSocket.onclose = function (ev) {
        window.console.info("发生了关闭请求");
    };

    webSocket.onerror = function (ev) {
        window.console.info("发生了错误");
    };
    webSocket.onmessage = function (ev) {


        // var obj=ev.data;
        /* for (var i in obj){
             var sendId = obj[i].sendId;
             var receiveId = obj[i].receiveId;
             var createTime = obj[i].createTime;
             var msg = obj[i].content;*/

        var obj = JSON.parse(ev.data);
        console.info("data" + ev.data);

        var sendId = obj.sendId;
        var receiveId = obj.receiveId;
        var createTime = obj.createTime;
        var msg = obj.content;

        dealAddReceived(ev);

        window.console.info("msg发送内容：" + msg);

       /* if (msg == requestTAG) {

            $.ajax({              //获取已发送请求的好友信息
                type: 'GET',
                url: '/user/getId',
                dataType: 'JSON',
                data: {
                    'id': sendId              //目前为接受者，因此sendId是请求者
                },
                success: function (data) {

                    var reValidte = '';
                    var id = data.id;
                    var image = data.image;
                    var userName = data.userName;
                    reValidte += '<li class="person" id="' + id + '">\n' +
                        '<img src="' + image + '" alt="' + id + '" style="width: 45px;height: 45px"/>\n' +
                        '<span class="name">帐号：' + id + ' 用户名：' + userName + '</span>\n' +
                        '<span style="color: green"  onclick="acceptReq('+sendId+')">√</span>\n' +
                        '<span style="color: red" onclick="refuseReq('+sendId+')">×</span>\n'+
                        '</li>';
                    document.getElementById("reValidte").innerHTML += reValidte;
                }
            });
        }
        if (msg == agreeTAG) {
            window.console.info(sendId+"已同意你的请求");
            $("#sendValidate li[id="+sendId+"]").remove();
        }

        if (msg == refuseTAG ){
            window.console.info(sendId+"已拒绝你的请求");
            $("#sendValidate li[id="+sendId+"]").remove();
        }
        alert(sendId + " " + receiveId + " " + createTime + " " + msg)*/

    };

    return webSocket;
}

//处理好友添加请求
function dealAddReceived(ev) {

    var obj = JSON.parse(ev.data);
    console.info("data" + ev.data);

    var sendId = obj.sendId;
    var receiveId = obj.receiveId;
    var createTime = obj.createTime;
    var msg = obj.content;

    if (msg == requestTAG) {

        $.ajax({              //获取已发送请求的好友信息
            type: 'GET',
            url: '/user/getId',
            dataType: 'JSON',
            data: {
                'id': sendId              //目前为接受者，因此sendId是请求者
            },
            success: function (data) {

                var reValidte = '';
                var id = data.id;
                var image = data.image;
                var userName = data.userName;
                reValidte += '<li class="person" id="' + id + '">\n' +
                    '<img src="' + image + '" alt="' + id + '" style="width: 45px;height: 45px"/>\n' +
                    '<span class="name">帐号：' + id + ' 用户名：' + userName + '</span>\n' +
                    '<span style="color: green"  onclick="acceptReq('+id+')">√</span>\n' +
                    '<span style="color: red" onclick="refuseReq('+id+')">×</span>\n'+
                    '</li>';
                document.getElementById("reValidte").innerHTML += reValidte;
            }
        });
    }

    if (msg == agreeTAG) {    //接收到好友请求同意消息

        window.console.info(sendId+"已同意你的请求");
        $("#sendValidate li[id="+sendId+"]").remove();

        $.ajax({              //好友列表添加此好友信息
            type: 'GET',
            url: '/user/getId',
            dataType: 'JSON',
            data: {
                'id': sendId
            },
            success: function(data){

                var info="";
                var id = data.id;
                var image = data.image;
                var userName = data.userName;

                info +='<li class="person" data-chat="' + id + '">' +
                    '<img id="hook"  src="' + image + '" alt="' + id + '" />' +
                    '<span class="name">' + userName + '</span>' +
                    '</li>';

                //$("#people").innerHTML+=info;
                document.getElementById("people").innerHTML +=info;
            }
        });
    }

    if (msg == refuseTAG ){   //接收到好友请求拒绝消息
        window.console.info(sendId+"已拒绝你的请求");
        $("#sendValidate li[id="+sendId+"]").remove();
    }
    alert(sendId + " " + receiveId + " " + createTime + " " + msg)

}

//同意好友添加请求
function acceptReq(receiveId) {

    var sendId = $("#userIf").attr("alt");

    $.ajax({
        type: 'POST',
        url: '/friend/agree',
        dataType: 'text',
        data: {
            'friendId': receiveId,
            'userId': sendId,
        },
        success: function (data) {
            var content = {
                "sendId": sendId,    //发送同意信息到请求者，因此receiveId是真正的发送者
                "receiveId": receiveId,
                "content": agreeTAG
            };
            window.alert(data + JSON.stringify(content));
            webSocket.send(JSON.stringify(content));
            window.console.info("同意发送内容：" + content.content);
            $("#reValidte li[id="+receiveId+"]").remove();

            $.ajax({              //好友列表添加此好友信息
                type: 'GET',
                url: '/user/getId',
                dataType: 'JSON',
                data: {
                    'id': receiveId
                },
                success: function(data){


                    var info="";
                    var id = data.id;
                    var image = data.image;
                    var userName = data.userName;

                    info +='<li class="person" data-chat="' + id + '">' +
                        '<img id="hook"  src="' + image + '" alt="' + id + '" />' +
                        '<span class="name">' + userName + '</span>' +
                        '</li>';

                    //$("#people").innerHTML+=info;
                    document.getElementById("people").innerHTML +=info;
                }
            });

        }
    });

}

//拒绝好友添加请求
function refuseReq(receiveId) {

    var sendId = $("#userIf").attr("alt");

    $.ajax({
        type: 'POST',
        url: '/friend/delete',
        dataType: 'text',
        data: {
            'friendId': receiveId,
            'userId': sendId
        },
        success: function (data) {

            var content = {
                "sendId": sendId,    //发送拒绝信息到请求者，
                "receiveId": receiveId,
                "content": refuseTAG
            };
            window.alert(data + JSON.stringify(content));
            webSocket.send(JSON.stringify(content));
            window.console.info("拒绝发送内容：" + content.content);
            $("#reValidte li[id="+receiveId+"]").remove();

        }
    });
}


function init() {

    document.querySelector('.person').classList.add('active');//使person处于选中状态
    document.querySelector('.chat').classList.add('active-chat');//选择person处于正在聊天状态
    var friends = {
            list: document.querySelector('ul.people'),//获取好友列表
            all: document.querySelectorAll('.left .person'),//获取所有好友
            name: '' },

        chat = {
            container: document.querySelector('.container .right'),//获取聊天栏
            current: null,
            person: null,
            name: document.querySelector('.container .right .top .name') };//获取聊天对象名称

    var sendId = $("#userIf").attr("alt");
    var receiveId = $(".active").attr("data-chat");
    initChatRecord(receiveId);

    // 监听鼠标点击事件，使点击对象处于active状态
    friends.all.forEach(function (f) {
        f.addEventListener('mousedown', function () {
            f.classList.contains('active') || setActiveChat(f);
        });
    });

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

    }

 /*   function getWebSocket() {
        var webSocket = new WebSocket("ws://localhost:8080/chatRoom/"+sendId);

        webSocket.onopen = function (ev) {
            alert("发出连接请求")
        };

        webSocket.onclose = function (ev) {
            alert("发生了关闭请求")
        };

        webSocket.onerror = function (ev) {
            alert("发生了错误")
        };

        webSocket.onmessage = function (ev) {

            alert(ev.data);
            var obj = JSON.parse(ev.data);
            alert(obj);
            for (var i in obj){
               var sendId = obj[i].sendId;
               var receiveId = obj[i].receiveId;
               var createTime = obj[i].createTime;
               var msg = obj[i].content;
               alert(sendId +" " + receiveId +" " + createTime+" " + msg)
           }
        };

        return webSocket;
    }*/


    var webSocket = getWebSocket();

    //发送功能
    $("#chat-fasong").click(function () {
        var textContent = $(".div-textarea").html().replace(/[\n\r]/g, '<br>')
        if (textContent != "") {
            var msg = $(".div-textarea").html();
            var content = {
                "sendId": $("#userIf").attr("alt"),
                "receiveId":$(".active").attr("data-chat"),
                "content":$(".div-textarea").text()
            };
            alert(JSON.stringify(content));
            webSocket.send(JSON.stringify(content));

            $.ajax({
                url:"/chatRoom/send",
                type:"post",
                data: JSON.stringify(content),
                contentType : "application/json;charset=utf-8",
                success:function (ev) {
                    var time = document.getElementById($(".active").attr("data-chat")).getElementsByClassName("conversation-start");
                    var lastTime = time[time.length-1].innerText;
                    var currentTime = ev.trim().substring(0,19);
                    if (judgeShowTime(currentTime,lastTime))
                        if (boolToday(currentTime)){
                            $(".active-chat").append('<div class=\"conversation-start\"><span>'+currentTime.split(" ")[1]+'</span></div>');
                        }
                        else $(".active-chat").append('<div class=\"conversation-start\"><span>'+currentTime+'</span></div>');
                    $(".active-chat").append("<div class='bubble me'>"+msg+"</div>");
                    //发送后清空输入框
                    $(".div-textarea").html("");
                }
            })
        }else {
            alert("发送内容不能为空");
        }
    });
}
//点击哪位好友,生成其聊天记录
function initChatRecord(contactId) {
    var userId = $("#userIf").attr("alt");
    $.get("chatRoom/" +userId+"/"+contactId,function (data,status) {
        var obj = jQuery.parseJSON(data);
        var show = true;
        var openedChatWindow = document.getElementById(contactId);
        openedChatWindow.innerHTML = " ";
        for (var i in obj){
            var content = obj[i].content;
            var sendTime = obj[i].createTime;
            var sendId = obj[i].sendId;
            var receiveId = obj[i].receiveId;
            if (show){
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
    var current_year = parseInt(current[0]);var past_year = parseInt(past[0]);
    var current_month = parseInt(current[1]);var past_month = parseInt(past[1]);
    var current_day = parseInt(current[2]);var past_day = parseInt(past[2]);
    var current_hour = parseInt(current[3]);var past_hour = parseInt(past[3]);
    var current_minute = parseInt(current[4]);var past_minute = parseInt(past[4]);
    var current_second = parseInt(current[5]);var past_second = parseInt(past[5]);
    if (current_year>past_year || current_month>past_month || current_day > past_day ||current_hour>past_hour) return true;
    else if ((current_minute*60+current_second) >= (past_minute*60+past_second + 180)) return true;
    else return false;
}

//判断是否为当天,如果是当天的话,返回false,不显示年月日
function boolToday(time) {
    var reg = new RegExp("[^0-9]");
    var time_array = time.split(reg);
    var date = new Date();
    return (time_array[0] == date.getFullYear() && time_array[1] == date.getMonth()+1 && time_array[2] == date.getDate());
}


