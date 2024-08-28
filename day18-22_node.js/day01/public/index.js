document.addEventListener("DOMContentLoaded", () => {
    const saramTableBody = document.getElementById("saramTableBody");
    const nameInput = document.getElementById("name");
    const deptInput = document.getElementById("dept");
    const gradeInput = document.getElementById("grade");
    const addSaramBtn = document.getElementById("addSaramBtn");

    // 사원 목록 불러오기
    function loadSaramList(query = "") {
        fetch(`/saram?search=${query}`)
            .then(response => response.json())
            .then(data => {
                saramTableBody.innerHTML = "";
                data.forEach(saram => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td><input type="checkbox" data-no="${saram.no}"></td>
                        <td>${saram.no}</td>
                        <td class="editable" data-type="name" data-no="${saram.no}">${saram.name}</td>
                        <td class="editable" data-type="dept" data-no="${saram.no}">${saram.dept}</td>
                        <td class="editable" data-type="grade" data-no="${saram.no}">${saram.grade}</td>
                        <td><button onclick="startEdit(${saram.no})">edit</button></td>
                        <td><button onclick="deleteSaram(${saram.no})">delete</button></td>
                    `;
                    saramTableBody.appendChild(row);
                });
            });
    }

    // 사원 추가
    addSaramBtn.addEventListener("click", (e) => {
        e.preventDefault();

        const name = nameInput.value.trim();
        const dept = deptInput.value.trim();
        const grade = gradeInput.value.trim();

        if (name && dept && grade) {
            fetch("/saram", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name, dept, grade })
            })
            .then(response => response.json())
            .then(data => {
                loadSaramList();
                nameInput.value = "";
                deptInput.value = "";
                gradeInput.value = "";
            });
        }
    });

    // 사원 수정
    window.startEdit = function(no) {
        const row = document.querySelectorAll(`[data-no='${no}']`);
        row.forEach(cell => {
            const currentValue = cell.innerText;
            const input = document.createElement("input");
            input.type = "text";
            input.value = currentValue;
            input.setAttribute("data-original-value", currentValue);
            cell.innerHTML = "";
            cell.appendChild(input);
        });

        // 수정 모드로 전환
        const editButton = document.querySelector(`button[onclick='startEdit(${no})']`);
        editButton.innerText = "save";
        editButton.setAttribute("onclick", `saveEdit(${no})`);
    };

    // 수정 내용 저장
    window.saveEdit = function(no) {
        const row = document.querySelectorAll(`[data-no='${no}']`);
        const updatedData = {};

        row.forEach(cell => {
            const input = cell.querySelector("input");
            const fieldType = cell.getAttribute("data-type");
            if (input) {
                const updatedValue = input.value;
                updatedData[fieldType] = updatedValue;
            }
        });

        fetch(`/saram/${no}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updatedData)
        })
        .then(response => response.json())
        .then(data => {
            loadSaramList();
        });
    };

    // 사원 삭제
    window.deleteSaram = function(no) {
        if (confirm("정말 삭제하시겠습니까?")) {
            fetch(`/saram/${no}`, {
                method: "DELETE"
            })
            .then(response => response.json())
            .then(data => {
                loadSaramList();
            });
        }
    };

    loadSaramList();
});
