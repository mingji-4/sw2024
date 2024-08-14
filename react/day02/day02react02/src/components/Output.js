import React, { useState } from 'react';

export default (props) => {
    const [editNo, setEditNo] = useState(null);
    const [newTitle, setNewTitle] = useState('');

    let todoListArr = props.todoListArr;
    const { deleteItem, updateItem } = props;

    const handleEditClick = (item) => {
        setEditNo(item.no);
        setNewTitle(item.title);
    };

    const handleUpdateClick = (no) => {
        updateItem(no, newTitle);
        setEditNo(null);
    };

    return (
        <div>
            <table id="todoListTbl" className="table table-hover">
                <thead>
                    <tr style={{ textAlign: "center" }}>
                        <th>Confirm</th>
                        <th>Title</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {todoListArr.map((item, idx) => {
                        // 중괄호 enter하면 return문을 꼭 넣어야 함
                        return (
                            <tr key={item.no}>
                                <td>
                                    <input type="checkbox" />
                                </td>
                                <td>
                                    {editNo === item.no ? (
                                        <input
                                            value={newTitle}
                                            onChange={(e) => setNewTitle(e.target.value)}
                                        />
                                    ) : (
                                        item.title
                                    )}
                                </td>
                                <td>
                                    {editNo === item.no ? (
                                        <>
                                            <button
                                                className="btn btn-success"
                                                type="button"
                                                onClick={() => handleUpdateClick(item.no)}
                                            >
                                                Save
                                            </button>
                                            <button
                                                className="btn btn-secondary"
                                                type="button"
                                                onClick={() => setEditNo(null)}
                                            >
                                                Cancel
                                            </button>
                                        </>
                                    ) : (
                                        <>
                                            <button
                                                className="btn btn-primary"
                                                type="button"
                                                onClick={() => handleEditClick(item)}
                                            >
                                                Edit
                                            </button>
                                            <button
                                                className="btn btn-danger"
                                                type="button"
                                                onClick={() => deleteItem(item.no)}
                                            >
                                                Delete
                                            </button>
                                        </>
                                    )}
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
}
