const http = require("http");
const express = require("express");
const app = express();
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const expressSession = require("express-session");

// 파일 업로드용 미들웨어
var multer = require('multer');
var fs = require('fs');

// multer 미들웨어 사용: 미들웨어 사용 순서 
// body-parser -> multer -> router 순으로 실행
var storage = multer.diskStorage({
    destination: function (req, file, callback) {
        callback(null, 'uploads');
    },
    filename: function (req, file, callback) {
        // 한글 파일명 깨짐 방지
        const fileName = Buffer.from(file.originalname, 'latin1').toString('utf8');
        // 파일명 중복을 방지하기 위한 처리
        // Date.now() <-- 타임스템프
        let index = fileName.lastIndexOf(".");
        let newFileName = fileName.substring(0, index);
        newFileName += Date.now();
        newFileName += fileName.substring(index);
        callback(null, newFileName);
    }
});

// 파일 제한: 10개, 1G 이하
var upload = multer({
    storage: storage,
    limits: {
        files: 10,
        fileSize: 1024 * 1024 * 1024
    }
});


app.set('port', 3000);
app.set("views", "views");
app.set("view engine", "ejs");

app.use(express.static("public"));
app.use('/uploads', express.static("uploads"));

// POST 방식으로 파라미터 전달 받기 위한 설정
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// 쿠키 사용 미들웨어 설정
app.use(cookieParser());

// 세션 미들웨어 등록
app.use(expressSession({
    secret: 'my key',
    resave: true,
    saveUninitialized: true
}));

// 회원 목록
const memberList = [
    { no: 101, id: "user01", password: "1234", name: "홍길동", email: "hong@gmail.com" },
    { no: 102, id: "user02", password: "12345", name: "김길동", email: "kim@gmail.com" },
    { no: 103, id: "user03", password: "123456", name: "박길동", email: "park@gmail.com" },
    { no: 104, id: "user04", password: "1234567", name: "이길동", email: "lee@gmail.com" }
];
let noCnt = 105;

// 쇼핑몰 상품 목록
const carList = [
    {
        _id: 101,
        name: 'SM5',
        price: 3000,
        year: 1999,
        company: 'SAMSUNG',
        writedate: "",
        photos: [
            {
                originalname: "Newsm5.jpg",
                filename: "Newsm5.jpg",
                filesize: 371000,
                mimetype: "img/jpg"
            }, {
                originalname: "sm5.jpeg",
                filename: "sm5.jpeg",
                filesize: 371000,
                mimetype: "img/jpeg"
            }
        ]
    }
];
let carSeq = 102;

// 요청 라우팅 사용
const router = express.Router();

// HTML 폼에서 REST method는 GET와 POST만 사용 가능
// Ajax를 사용하지 않기 때문에 GET와 POST만 처리 가능
// app.get()은 ejs 뷰로 forward 시켜주기 (목록 출력)
// app.post()는 DB와 연동해서 처리하는 process 역할 (목록 저장)
// forward란, 주소의 내용이 아닌 다른 파일의 내용 표시

// home
router.route("/home").get((req, res) => {
    req.app.render("home/Home", {}, (err, html) => {
        res.end(html);
    });
});

// profile
router.route("/profile").get((req, res) => {
    req.app.render("profile/Profile", {}, (err, html) => {
        res.end(html);
    });
});

// member
router.route("/member").get((req, res) => {
    // 로그인이 되어 있다면 member 페이지를 보여줌
    // 쿠키는 사용자쪽에 전달(res), 세션은 요청이 들어올 때 생성(req)
    if (req.session.user !== undefined) {
        const user = req.session.user;
        req.app.render("member/Member", { user }, (err, html) => {
            res.end(html);
        });
    } else {
        res.redirect("/login")
    }
});

// -------- login -------- 
router.route("/login").get((req, res) => {
    req.app.render("member/Login", {}, (err, html) => {
        // 사용자(접속자)의 로컬에 쿠키가 저장됨
        res.cookie('user', {
            id: 'TestUser',
            name: '테스트 유저',
            authorized: true
        });
        res.end(html);
    });
});

router.route("/login").post((req, res) => {
    console.log(req.body.id, req.body.password);
    const idx = memberList.findIndex(member => member.id === req.body.id);
    if (idx != -1) {
        if (memberList[idx].password === req.body.password) {
            console.log("로그인 성공!");
            // 세션에 로그인 정보를 등록 후 멤버 페이지로 이동
            req.session.user = {
                id: req.body.id,
                name: memberList[idx].name,
                email: memberList[idx].email,
                no: memberList[idx].no
            }
            res.redirect("/member");
        } else {
            console.log("로그인 실패! 패스워드가 맞지 않습니다");
            // 다시 로그인 페이지로 이동
            res.redirect("/login");
        }
    } else {
        console.log("존재하지 않는 계정입니다");
        res.redirect("/login");
    }
});

// -------- logout -------- 
router.route("/logout").get((req, res) => {
    console.log("GET - /logout 호출...");
    // 로그인이 된 상태라면 로그아웃
    if (!req.session.yser) {
        console.log("로그인 전 상태입니다");
        res.redirect("/login");
        return;
    }
    // 세션의 user 정보를 제거해서 logout 처리
    req.session.destroy((err) => {
        if (err) throw err;
        console.log("로그아웃 성공");
        res.redirect("/login");
    });
});

// -------- joinus -------- 
router.route("/joinus").get((req, res) => {
    // 회원 가입 ejs 페이지 forward
    req.app.render("member/Joinus", {}, (err, html) => {
        res.end(html);
    });
});

router.route("/joinus").post((req, res) => {
    // 회원 가입 처리 후 목록으로 갱신
    res.redirect("/member");
});

// gallery
router.route("/gallery").get((req, res) => {
    req.app.render("gallery/Gallery", {}, (err, html) => {
        res.end(html);
    });
});

// -------- todolist -------- 
const { MongoClient, ObjectId } = require("mongodb");

const client = new MongoClient("mongodb://localhost:27017");
const dbName = "comstudy";
const collectionName = "todolist";

/* 몽고디비에 데이터 추가
db.todolist.insertMany([
{title:"밥먹기2", done:false},
{title:"잠자기2", done:false},
{title:"공부하기2", done:true},
{title:"친구랑 놀기2", done:false}
])
*/

// 서버가 시작될 때 한 번만 MongoDB에 연결합니다.
async function connectToDB() {
    try {
        await client.connect();
        db = client.db(dbName);
        console.log("Connected successfully to MongoDB");
    } catch (err) {
        console.error("MongoDB connection error:", err);
    }
}
connectToDB();

app.get("/todo/list", async (req, res) => {
    //res.render("todolist/TodoList", {todoList});
    const todoList = [];
    try {
        client.connect();
        const database = client.db(dbName);
        const todoCollection = database.collection(collectionName);
        const QUERY = {};
        const cursor = todoCollection.find(QUERY, {});
        if ((await todoCollection.countDocuments(QUERY)) === 0) {
            console.log("No documents found!");
        }

        for await (const doc of cursor) {
            todoList.push(doc);
            console.dir(doc);
        }
        req.app.render("todolist/TodoList", { todoList }, (err, html) => {
            if (err) throw err;
            res.end(html);
        });
    } finally {
        await client.close();
    }
});


// input
app.get("/todo/input", (req, res) => {
    res.render("todolist/TodoInput", {});
});

// detail
app.get("/todo/detail", (req, res) => {
    res.render("todolist/TodoDetail", {});
});

// modify
app.get("/todo/modify", async (req, res) => {
    try {
        // MongoDB 연결 코드
        const collection = db.collection(collectionName);
        // _id 값을 ObjectId로 변환
        const query = { _id: new ObjectId(req.query._id) };
        const fetch = await collection.findOne(query);

        if (!fetch) {
            console.log("No document found with the given _id");
            return res.status(404).send("Document not found");
        }

        res.render("todolist/TodoModify", { todo: fetch });
    } catch (err) {
        console.error("Error fetching document for modification:", err.message);
        res.status(500).send("Internal Server Error");
    }
});

// 저장처리 - 몽고디비와 연동
app.post("/todo/input", async (req, res) => {
    const doc = {
        title: req.body.title,
        done: (req.body.done == "true" ? true : false)
    }
    console.dir(doc);
    try {
        client.connect();
        const database = client.db(dbName);
        const cars = database.collection(collectionName);
        const result = await cars.insertOne(doc);
        console.log(`A document was inserted with the _id: ${result.insertedId}`);
        res.redirect("/todo/list");
    } finally {
        await client.close();
    }
});

app.post("/todo/detail", (req, res) => {
    res.redirect("todo/list");
});

app.post("/todo/modify", async (req,res)=>{
    console.log(req.body._id);
    try {
        client.connect();
        const database = client.db(dbName);
        const movies = database.collection(collectionName);
        const filter = { _id: new ObjectId(req.body._id) };
        const options = { upsert: true };
        const updateDoc = {
          $set: {
            title: req.body.title,
            done: (req.body.done=="true"?true:false)
          }
        };// Update the first document that matches the filter
        const result = await movies.updateOne(filter, updateDoc, options);
        console.log(`${result.matchedCount} document(s) matched the filter, updated ${result.modifiedCount} document(s)`,);
        // res.redirect("/todo/list");
    } finally {
        await client.close();
      }
    res.redirect("/todo/list");
});

app.get("/todo/delete", async (req, res) => {
    try {
        await client.connect()
        const database = client.db(dbName)
        const collection = database.collection(collectionName);
        await collection.deleteOne({_id : new ObjectId(req.query._id)});
        res.redirect("/todo/list");
    } finally {
        await client.close()
    }
});


// -------- shop -------- 
router.route("/shop").get((req, res) => {
    req.app.render("shop/Shop", { carList }, (err, html) => {
        if (err) throw err;
        res.end(html);
    });
});

// shop-insert
router.route("/shop/insert").get((req, res) => {
    req.app.render("shop/Insert", {}, (err, html) => {
        res.end(html);
    });
});

// 파일 업로드 기능 추가
router.route("/shop/insert").post(upload.array('photo', 1), (req, res) => {
    console.log("POST - /shop/insert");
    // 구조분해 할당으로 body의 파라미터를 꺼낸다.
    const { name, price, year, company } = req.body;
    const newCar = {
        _id: carSeq++, name, price, year, company,
        writedate: Date.now(),
        photos: []
    };
    newCar.photos = req.files;
    carList.push(newCar);
    // res.send(carList);
    res.redirect('/shop');
});

// shop-modify
router.route("/shop/modify").get((req, res) => {
    const _id = parseInt(req.query._id);
    console.log(_id)
    const idx = carList.findIndex(car => _id === car._id);
    console.log(idx);
    if (idx === -1) {
        console.log("상품이 존재 하지 않습니다.")
        res.redirect("/shop");
        return;
    }
    req.app.render("shop/Modify", { car: carList[idx] }, (err, html) => {
        if (err) throw err;
        res.end(html);
    });
});
router.route("/shop/modify").post((req, res) => {
    console.log("POST - /shop/modify 호출");
    console.dir(req.body);
    res.redirect('/shop');
});

// shop-detail
router.route("/shop/detail").get((req, res) => {
    // 쿼리로 전송된 데이터는 모두 문자열이다. 
    // parseInt() 필수 "56" <-- numeric
    const _id = parseInt(req.query._id);
    // console.log(_id)
    const idx = carList.findIndex(car => _id === car._id);
    // console.log(idx);
    if (idx === -1) {
        console.log("상품이 존재 하지 않습니다.")
        res.redirect("/shop");
        return;
    }
    req.app.render("shop/Detail", { car: carList[idx] }, (err, html) => {
        if (err) throw err;
        res.end(html);
    });
});

// shop-delete
router.route("/shop/delete").get((req, res) => {
    req.app.render("shop/Delete", {}, (err, html) => {
        res.end(html);
    });
});

// shop-cart
router.route("/shop/cart").get((req, res) => {
    req.app.render("shop/Cart", {}, (err, html) => {
        res.end(html);
    });
});



// router 설정 맨 아래에 미들웨어 등록
app.use('/', router);

// 등록되지 않은 패스에 대해 페이지 오류 응답
// app.all('*', function(req, res) {
//     res.status(404).send('<h1>ERROR - 페이지를 찾을 수 없습니다.</h1>')
// });

// 오류 핸들러 모듈 사용
const expressErrorHandler = require('express-error-handler');

// 모든 라우터 처리 후 404 오류 페이지 처리
const errorHandler = expressErrorHandler({
    static: {
        '404': './public/404.html'
    }
});

app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);


// server
const server = http.createServer(app);
server.listen(app.get('port'), () => {
    console.log(`Run on server >>> http://localhost:${app.get('port')}`);
});