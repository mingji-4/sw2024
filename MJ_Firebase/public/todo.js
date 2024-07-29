const btn = document.getElementById('btn');
let addValue = document.getElementById('addValue');
let result = document.getElementById('result');

function addTodo() {
    if (addValue.value == false) {
        alert('오늘의 할일');
    } else {
        let list = document.createElement("li");
        let del = document.createElement('button');
        list.innerHTML = addValue.value;
        result.appendChild(list);
        list.appendChild(del);
        del.innerText = "v";
        del.style.fontSize = "20px";
        del.style.border = "none";
        del.style.float = "right";
        del.style.right = "17px";
        del.style.marginTop = "10px";
        del.style.cursor = "pointer";
        del.addEventListener("click", deleteList);
        del.style.position = 'relative';

        list.addEventListener("click", function () {
            list.style.textDecoration = "line-through";
        })
    }
}

function deleteList(e) {
    let removeOne = e.target.parentElement;
    removeOne.remove();
}