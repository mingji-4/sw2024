const http = require("http");
const express = require("express");
const app = express();
const path = require("path");
const cors = require("cors");
const bodyParser = require("body-parser");

app.set('port', 3000);
console.log("__dirname:", __dirname);
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");

// public 디렉토리를 static으로 사용하기 위한 설정 
app.use("/", express.static(path.join(__dirname, "public") ));
app.use(cors());

// POST 요청 시 파라미터를 body에서 사용하기 위한 설정
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

const saramList = [
    {no:101, name:"박태호", dept:"기획부", grade: "부장"},
    {no:102, name:"손유일", dept:"개발부", grade: "과장"},
    {no:103, name:"오수철", dept:"인사부", grade: "대리"},
    {no:104, name:"인재홍", dept:"영업부", grade: "사원"}
];
let noCnt=105;

// index.html에서 Ajax 요청 처리
// 구현하고 포스트맨으로 테스트 ㄱㄱ

app.get("/saram", (req, res) => {
    console.log("GET - /saram 요청");
    res.send(saramList);
});

app.post("/saram", (req, res) => {
    console.log("POST - /saram 요청");
    let newSaram = {
        no: noCnt++,
        name: req.body.name,
        dept: req.body.dept,
        grade: req.body.grade
    }
    saramList.push(newSaram);
    res.send(saramList);
});

app.put("/saram/:no", (req, res) => {
    console.log("PUT - /saram 요청");
    const toEdit = saramList.find(saram => saram.no === parseInt(req.params.no));
    if (toEdit) {
        toEdit.name = req.body.name;
        toEdit.dept = req.body.dept;
        toEdit.grade = req.body.grade;
        res.send(saramList);
    } else {
        res.status(404).send({ error: "Saram not found" });
    }
});

app.delete("/saram/:no", (req, res) => {
    console.log("DELETE - /saram 요청");
    const idx = saramList.findIndex(saram => saram.no === parseInt(req.params.no));
    saramList.splice(idx, 1)
    res.send(saramList);
});

const server = http.createServer(app);
server.listen(app.get('port'), ()=>{
    console.log(`서버 실행 중>>> http://localhost:${app.get('port')}`);
});