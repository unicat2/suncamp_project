let menu = document.querySelector(".float-menu");
let items = document.querySelectorAll(".avatar");


let menu_width = menu.offsetWidth / 2;

let timer,
    menu_entered = false,
    url;

function item_enter(ev) {
    if (timer) timer = clearTimeout(timer); // 只要上次的定时器没清除，就清除掉，防止菜单莫名其妙隐藏

    url = ev.target.dataset.url;

    menu.classList.add("active"); // 让 menu 显示出来，主要是修改了 visibility 属性

    // menu.style.top = ev.target.offsetTop + ev.target.offsetHeight - 25 + "px"; // 纵向坐标
    // menu.style.right =
    //     ev.target.offsetLeft + ev.target.offsetWidth / 2 - menu_width + "px"; // 横向坐标
}

function item_leave() {
    timer = setTimeout(() => {
        // 如果没有进入过菜单，才让菜单隐藏
        if (!menu_entered) {
            menu.classList.remove("active");

            url = undefined;
        }

        // 活干完了，要把定时器取消掉，还原变量
        timer = clearTimeout(timer);
    }, 100);
}

function menu_enter() {
    menu_entered = true;
}

function menu_leave() {
    menu_entered = false; // 还原状态

    timer = clearTimeout(timer); // 清除定时器

    menu.classList.remove("active"); // 隐藏菜单
}

menu.onmouseenter = menu_enter;
menu.onmouseleave = menu_leave;

items.forEach((item) => {
    item.onmouseenter = item_enter;
    item.onmouseleave = item_leave;
    // item.onmouseover = item_leave; 错误的事件
});

// function menu_item_manage(ev) {
//     // 点击按钮跳转对应页面
//     // if (url) window.open(url);
//     let pwdForm = document.getElementById("layui-form")
//     layer.open({
//         type:1,
//         content:pwdForm,
//         title :"修改密码",
//         area: ['500px', '300px']
//     });
// }

