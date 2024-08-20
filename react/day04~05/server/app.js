const http = require('http');
const express = require('express');
const app = express();
const cors = require('cors');

app.set('port', 3000);

// static 미들 웨어
app.use('/', express.static("public"));
// URL 또는 포트가 다른 클라이언트 요청 허용
app.use(cors());

// 데이터 임시 저장 배열
const todoList = [
    {no: 101, title: "Study", done: false},
    {no: 102, title: "Java Study", done: false},
    {no: 103, title: "React Study", done: false},
    {no: 104, title: "Spring Study", done: false}
];

app.get("/todo", (req, res)=>{
    // 목록 출력
    res.send(todoList);
});

app.post("/todo", (req, res)=>{
    // 할일 입력 
    res.send(todoList);
});

app.put("/todo", (req, res)=>{
    // 할일 수정
    res.send(todoList);
});

app.delete("/todo", (req, res)=>{
    // 할일 삭제
    res.send(todoList);
});

const server = http.createServer(app);
server.listen(app.get('port'), ()=>{
    console.log("서버 실행 중 >>> http://localhost:"+app.get('port'));
});