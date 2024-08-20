import "./App.css";
import {useState} from "react";
import Input from "./Input";
import Output from "./Output";

const App = ()=>{
    // 전역변수를 state로 만들어 주어야 re rendering 된다.
    // 구조분해 할당 = state변수, setter함수
    const [name, setName] = useState("Todo List");
    const [todoList, setTodoList] = useState([]);
    const [noCnt, setNoCnt] = useState(105);

    const onClickEvent = (inputTitle) => {
        // 기존 내용에 새 내용을 추가 해서 새 배열을 생성
        setTodoList([...todoList, {no:noCnt, title:inputTitle, done: false}]);
        setNoCnt(noCnt+1);
    }

    const onDelete = ({no, title, done}) => {
        const newList = todoList.filter((todo)=> {
            return todo.no != no;
        });
        setTodoList(newList);
    };

    const onDoneFlag = ({no, title, done})=>{
        const newTodoList = [...todoList];
        todoList.forEach((item, idx)=> {
            if(item.no == no) {
                newTodoList[idx].done = !done;
            }
        });
        setTodoList(newTodoList);
    };

    const onEdit = ({no, title, done})=>{
        const newTodoList = [...todoList];
        todoList.forEach((item, idx)=> {
            if(item.no == no) {
                newTodoList[idx].done = done;
                newTodoList[idx].title = title;
            }
        });
        setTodoList(newTodoList);
    };

    // 취소선 스타일 설정
    const lineThroughClass = {textDecoration:"line-through", color:"blue"}

    return (<div className="todoList">
        <div className="App-header">
            <h1>{name} App</h1>
        </div>

        {/* todo 타이틀 입력 컴포넌트 위치 */}
        <Input onClickEvent={onClickEvent} />

        {/* todo 목록이 출력되는 컴포넌트 위치 */}
        <Output todoList = {todoList} onDelete={onDelete} onDoneFlag={onDoneFlag} onEdit={onEdit} />
    </div>);
}

export default App;