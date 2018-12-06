<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=11">
  <title>Document</title>
  <script type="text/javascript" src="./assert/js/lib/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="./assert/js/lib/sockjs.min.js"></script>
</head>
<body>
  <h1>消息接收</h1>
  <p id="message"></p>
  <script type="text/javascript">
    var websocket = null;
    if ('WebSocket' in window) {
      websocket = new WebSocket("ws://localhost:8082/websocket/socketServer.do");
    }
    else if ('MozWebSocket' in window) {
      websocket = new MozWebSocket("ws://localhost:8082/websocket/socketServer.do");
    }
    else {
      websocket = new SockJS("http://localhost:8082/sockjs/socketServer.do");
    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;
    function onOpen(openEvt) {
      $('#message').html(openEvt.data);
    }
    function onMessage(evt) {
      $('#message').html(evt.data);
    }
    function onError(event) {

    }
    function onClose(event) {

    }
    function doSend() {
      if (websocket.readyState == websocket.OPEN) {
        var msg = document.getElementById("inputMsg").value;
        // 调用后台handleTextMessage方法alert("发送成功!");
        websocket.send(msg);
      } else {
        alert("连接失败!");
      }
    }
    window.onbeforeunload = function () {
      websocket.close();
    }
  </script>
</body>
</html>
