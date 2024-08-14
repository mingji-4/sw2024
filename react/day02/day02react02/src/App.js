import React from 'react';
import "./App.css"
import Input from "./components/Input"
import Output from "./components/Output"

const App = () => {
    const [todoListArr, setTodoList] = React.useState([
        { no: 101, title: '운동하기', done: false },
        { no: 102, title: '과제하기', done: false },
        { no: 103, title: '복습하기', done: false }
    ]);

    const [noCnt, setNoCnt] = React.useState(104);

    // 새로운 할 일을 추가하는 함수
    function appendItem(title) {
        // setTodoList 함수를 이용해서 데이터 갱신
        // 스프리드 연산자 사용하면 편리함
        const newItem = { no: noCnt, title: title, done: false };
        setNoCnt(noCnt + 1);
        setTodoList([...todoListArr, newItem]);
    }

    // 할 일을 삭제하는 함수
    function deleteItem(no) {
        const updatedList = todoListArr.filter(item => item.no !== no);
        setTodoList(updatedList);
    }

    // 할 일을 수정하는 함수
    function updateItem(no, newTitle) {
        const updatedList = todoListArr.map(item => item.no === no ? { ...item, title: newTitle } : item);
        setTodoList(updatedList);
    }

    return (
        <div>
            <header className="jumbotron">
                <h1>Todo List</h1>
                <p>오늘 할 일을 입력하세요</p>
            </header>
            <Input appendItem={appendItem} />
            <Output todoListArr={todoListArr} deleteItem={deleteItem} updateItem={updateItem} />
        </div>
    );
};

export default App;