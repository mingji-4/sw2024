import React, { useState } from "react";

const Output = ({ todoListArr, deleteItem, updateItem }) => {
  const [editMode, setEditMode] = useState(null);
  const [editedTitle, setEditedTitle] = useState("");

  const startEditing = (no, currentTitle) => {
    setEditMode(no);
    setEditedTitle(currentTitle);
  };

  const saveEdit = (no) => {
    updateItem(no, editedTitle);
    setEditMode(null);
  };

  return (
    <div>
      <ul>
        {todoListArr.map((item) => (
          <li key={item.no}>
            {editMode === item.no ? (
              <>
                <input
                  type="text"
                  value={editedTitle}
                  onChange={(e) => setEditedTitle(e.target.value)}
                />
                <button onClick={() => saveEdit(item.no)}>저장</button>
                <button onClick={() => setEditMode(null)}>취소</button>
              </>
            ) : (
              <>
                {item.title}
                <button onClick={() => startEditing(item.no, item.title)}>수정</button>
                <button onClick={() => deleteItem(item.no)}>삭제</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Output;
