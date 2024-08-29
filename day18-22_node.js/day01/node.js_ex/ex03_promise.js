function task1(fullfill, reject) {
    console.log('Task1 시작');
    // 파일을 읽거나, Ajax를 사용한다고 가정함
    setTimeout(function() {
        console.log('Task1 끝');
        //fullfill('Task1 결과');
        reject('Error msg');
    }, 300);
    console.log("2. 이 부분은 언제 실행 될까요?");
}

function fullfilled(result) {
    console.log('fullfiled : ', result);
}

function rejected(err) {
    console.log('rejected 함수 호출: ', err);
}

new Promise(task1).then(fullfilled, rejected);

console.log("1. 이 부분은 언제 실행 될까요?");