var todoList = [
    { seq: 101, title: "지각하지 않기", done: false },
    { seq: 102, title: "포트폴리오 만들기", done: false },
    { seq: 103, title: "필기하기", done: false },
    { seq: 104, title: "과제하기", done: false },
];
var seqCnt = 105;

// 삭제 이벤트 핸들러
function handleDelEvt(seq) {
    // 할일을 목록에서 제거
    todoList = todoList.filter(function (todoObj) {
        return todoObj.seq !== seq;
    });
    drawList();
}

// 수정 이벤트 핸들러
function editClickHandle(seq) {
    console.log("editClickHandle for seq:", seq);
}

function handleCheckBoxChange(seq) {
    // 할일의 완료 상태로 ㄱㄱ
    todoList = todoList.map(function (todoObj) {
        if (todoObj.seq === seq) {
            todoObj.done = !todoObj.done;
        }
        return todoObj;
    });
    drawList();
}

function drawList(filteredTodoList = todoList) {
    var demo = document.querySelector('#demo');
    demo.innerHTML = "";  // 기존 목록 초기화
    filteredTodoList.forEach(function (todoObj) {
        // 각 할일 항목을 테이블 행으로 추가
        demo.innerHTML += `<tr align="center">
            <td><input type="checkbox" ${todoObj.done ? 'checked' : ''} onclick="handleCheckBoxChange(${todoObj.seq})"></td>
            <td style="text-decoration: ${todoObj.done ? 'line-through' : 'none'}">${todoObj.title}</td>
            <td><button onclick="editClickHandle(${todoObj.seq})">수정</button></td>
            <td><button onclick="handleDelEvt(${todoObj.seq})">삭제</button></td>
        </tr>`;
    });
}
window.onload = function () {
    var memoTxt = document.getElementById('memoTxt');
    var clickBtn = document.querySelector('#clickBtn');
    var searchBtn = document.querySelector('#searchBtn');
    var searchTxt = document.querySelector('#searchTxt');

    // 할일 추가
    function handleEvent(event) {
        todoList.push({ seq: seqCnt++, title: memoTxt.value, done: false });
        drawList();
        memoTxt.value = "";
        memoTxt.focus();
    }

    clickBtn.addEventListener('click', handleEvent);

    memoTxt.onkeyup = function (event) {
        if (event.keyCode === 13) {
            handleEvent(event);  // enter 키 누르면 할일 추가
        }
    }
    // 검색하기
    searchBtn.addEventListener('click', function () {
        var searchValue = searchTxt.value.toLowerCase();
        var filteredTodoList = todoList.filter(function (todoObj) {
            return todoObj.title.toLowerCase().includes(searchValue);
        });
        drawList(filteredTodoList);
    });

    drawList();
}
