import { useRef, useState } from "react";

const Input = ({onClickEvent}) => {
    const [inputTitle, setInputTtile] = useState("");

    // 훅은 콜백함수에 포함 될수 없음
    const inputFocus = useRef(null);

    return (
    <div className="input-title">
        <div className="container" style={{padding: "10px"}}>
        <div className="input-group mb-3">
            <input autoFocus ref={inputFocus} value={inputTitle} onChange={(e)=> setInputTtile(e.target.value)} type="text" className="form-control"/>
            <div className="input-group-append">
                <button className="btn btn-success" onClick={(e)=>{
                    if(inputTitle === "" || inputTitle === null) {
                        alert("내용이 없습니다!");
                        inputFocus.current.focus();
                        return;
                    }
                    onClickEvent(inputTitle);
                    setInputTtile("");
                    // useRef() 훅 사용 focus 잡아줌
                    console.dir(inputFocus.current);
                    inputFocus.current.focus();
                }}>Save</button>
            </div>
        </div>
        </div>
    </div>
    );
}

// 대표 컴포넌트 하나만 모듈 등록할 때
export default Input;