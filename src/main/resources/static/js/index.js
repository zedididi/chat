document.querySelector('.chat[data-chat=person2]').classList.add('active-chat');//选择person2处于正在聊天状态
document.querySelector('.person[data-chat=person2]').classList.add('active');//使person2处于选中状态

var friends = {
        list: document.querySelector('ul.people'),//获取好友列表
        all: document.querySelectorAll('.left .person'),//获取所有好友
        name: '' },

    chat = {
        container: document.querySelector('.container .right'),//获取聊天栏
        current: null,
        person: null,
        name: document.querySelector('.container .right .top .name') };//获取聊天对象名称

//监听鼠标点击事件，使点击对象处于active状态
friends.all.forEach(function (f) {
    f.addEventListener('mousedown', function () {
        f.classList.contains('active') || setAciveChat(f);
    });
});

function setAciveChat(f) {
    friends.list.querySelector('.active').classList.remove('active');//选择处于active状态的person，并消除刚刚点击的person的active状态
    f.classList.add('active');
    chat.current = chat.container.querySelector('.active-chat');//使显示的chat处于active-chat状态
    chat.person = f.getAttribute('data-chat');//使person为选中的data-chat
    chat.current.classList.remove('active-chat');//消除chat的active-chat状态
    chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');//使聊天框中显示选中的person的active-chat
    friends.name = f.querySelector('.name').innerText;//获得聊天对象的name
    chat.name.innerHTML = friends.name;
}


