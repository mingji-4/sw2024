const http = require('http');
const express = require('express');
const app = express()

const port = 8000;

// 뷰 템플릿 상용을 위한 설정
// app.set('views', __dirname + '/views');  // __dirname: 절대 경로를 불러옴
app.set('views', 'views');
app.set('view engine', 'ejs');

// /public/index.html을 보여주기 위한 static 폴더 지정
app.use(express.static('public'));

// 사람 데이터 목록 선언
const saramList = [
  {no:101, name: '류현종', email: 'ryu@gmail.com', job:'개발자', age:25},
  {no:102, name: '강민지', email: 'gang@gmail.com', job:'인싸', age:25},
  {no:103, name: '홍채연', email: 'hong@gmail.com', job:'여행자', age:25},
  {no:104, name: '김민지', email: 'kim@gmail.com', job:'백수', age:24}
];

// node ./app.js -> 링크 들어간 후 -> 크롬에 나오는 링크 뒤에 /home 해야 나옴
// localhost:8000/saram
app.get('/saram', function (req, res) {
    // ejs 페이지로 렌더링 - views/saram.ejs 페이지의 코드를 읽어와서 res.end()에 적용

    var message = "사람 정보 관리 페이지";
    req.app.render('saram', {message, saramList}, function(err, html) {
      res.end(html);
    });
});

// localhost:8000/saram/detail?no=103
app.get('/saram/detail', function(req, res) {
    console.log("GET - /saram/detail >>>> no: " + req.query.no);
    var idx = saramList.findIndex(function(saram) {
      return saram.no == req.query.no;
    });
    var saram = null;
    if(idx != -1) {
      saram = saramList[idx];
    }
    req.app.render('saramDetail', {saram}, function(err, html) {
      res.end(html);
    });
});

app.get('/saram/edit', function(req, res) {
  console.log("GET - /saram/edit >>>> no: " + req.query.no);
  var idx = saramList.findIndex(function(saram) {
    return saram.no == req.query.no;
  });
  var saram = null;
  if(idx != -1) {
    saram = saramList[idx];
  }
  req.app.render('saramEdit', {saram}, function(err, html) {
    res.end(html);
  });
});

app.get('saram/update', function(req, res) {
  console.log("GET - /saram/update >>> no: " + req.query.no);
  var idx = saramList.findIndex(function(saram) {
    return saram.no == req.query.no;
  });
  var saram = req.query;
  if(idx != -1) {
    saramList[idx] = saram;
  }
  res.redirect("/saram");
});

app.get('/saram/delete', function(req, res) {
  console.log("GET - /saram/delete >>>> no: " + req.query.no);
  var idx = saramList.findIndex(function(saram) {
      return saram.no == req.query.no;
  });
  if(idx != -1) {
      saramList.splice(idx, 1);
  }
  res.redirect("/saram");
})

app.get('/saram/saramForm', function(req, res) {
  res.redirect('/saramForm.html');
})

app.get('/saram/add', function(req, res) {
  console.log("GET - /saram/add >>>> no: " + req.query.no);
  var saram = req.query;
  saramList.push(saram);
  res.redirect('/saram');
})

const server = http.createServer(app);
server.listen(8000, function () {
  console.log("서버 실행 중 >>> http://localhost:" + port);
});

// express
// 실제 웹 서버 구축에서는 node.js만 사용하지 않고 express를 더 많이 사용함