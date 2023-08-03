function $(id) {
    return document.getElementById(id);
}

function previousRegist() {
    // DOM: Document Object Model 文档对象模型
    // let nameText = document.getElementById("nameText");

    // BOM: Browser Object Model 浏览器对象模型
    // let name = document.forms[0].name;

    // 用户名不能为空, 而且应为 6~16 位数字和字母组成
    let nameRegular = /[0-9a-zA-Z]{6,16}/;

    let nameText = $("nameText");

    let name = nameText.value;

    let nameSpan = $("nameSpan");

    if (!nameRegular.test(name)) {

        nameSpan.style.visibility = "visible";

        return false;
    } else {
        nameSpan.style.visibility = "hidden";
    }

    // 密码必须同时包含字母和数字, 并且长度至少为8位
    let passwordRegular = /^(?=.[A-Za-z])(?=.\d)[A-Za-z\d]{8,}$/;

    let passwordText = $("passwordText");

    let password = passwordText.value;

    let passwordSpan = $("passwordSpan");

    if (!passwordRegular.test(password)) {

        passwordSpan.style.visibility = "visible";

        return false;
    } else {
        passwordSpan.style.visibility = "hidden";
    }

    // 密码两次输入不一致
    let repeatPassword = $("repeatPasswordText").value;

    let repeatPasswordSpan = $("repeatPasswordSpan");

    if (repeatPassword !== password) {
        repeatPasswordSpan.style.visibility = "visible";

        return false;
    } else {
        repeatPasswordSpan.style.visibility = "hidden";
    }

    // 请输入正确的邮箱格式
    let emailRegular = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;

    let emailText = $("emailText");

    let email = emailText.value;

    let emailSpan = $("emailSpan");

    if (!emailRegular.test(email)) {

        emailSpan.style.visibility = "visible";

        return false;
    } else {
        emailSpan.style.visibility = "hidden";
    }

    return true;
}

// 如果想要发送异步请求, 需要一个关键的对象 xmlHttpRequest
let xmlHttpRequest;

function createXMLHttpRequest() {
    if (window.XMLHttpRequest) {
        // 符合 DOM2 标准的浏览器, xmlHttpRequest 创建方式
        xmlHttpRequest = new XMLHttpRequest();
    } else {
        // IE 浏览器
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
}

function checkName(name) {
    createXMLHttpRequest();

    let url = "user.do?operate=checkName&name=" + name;

    xmlHttpRequest.open("GET", url, true);

    // 设量回调函数
    xmlHttpRequest.onreadystatechange = checkNameCallBack;

    // 发送请求
    xmlHttpRequest.send();
}

function checkNameCallBack() {
    if ((xmlHttpRequest.readyState === 4) && (xmlHttpRequest.status === 200)) {
        // xmlHttpRequest.responseText 表示服务器端啊应的文本内容
        // alert(xmlHttpRequest.responseText);
        let responseText = xmlHttpRequest.responseText;

        // {'name':'0'}
        // alert(responseText);

        if (responseText === "{'name':'1'}") {
            alert("用户名已经被注册！");
        } else if (responseText === "{'name':'0'}") {
            alert("用户名可以被注册！");
        }
    }
}
