const http = require('http');
const express = require('express');
const app = express();

app.set('port', 3000);

// static 미들 웨어
app.use('/', express.static("public"));

// 데이터 임시 저장 배열
const todoList = [[
    {no: 101, title: "Study", done: false},
    {no: 102, title: "Java Study", done: true},
    {no: 103, title: "React Study", done: false},
    {no: 104, title: "Spring Study", done: false}
]];

app.get("/todo", (req, res) => {
    // 목록 출력
    res.send(todoList);
});

app.post("/todo", (req, res) => {
    // 할 일 입력
    res.send(todoList);
});

app.put("/todo", (req, res) => {
    // 할 일 수정
    res.send(todoList);
});

app.delete("/todo", (req, res) => {
    // 할 일 삭제
    res.send(todoList);
});

const server = http.createServer(app);
server.listen(app.get('port'), () => {
    console.log("Starting Server >>> http://localhost:" + app.get('port'));
});