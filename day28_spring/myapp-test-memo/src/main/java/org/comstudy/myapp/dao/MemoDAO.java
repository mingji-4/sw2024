package org.comstudy.myapp.dao;

import org.comstudy.myapp.model.Memo;

import java.util.ArrayList;
import java.util.List;

public class MemoDAO {

    private List<Memo> memoList = new ArrayList<>();
    private int idCounter = 1;

    public List<Memo> getAllMemos() {
        return memoList;
    }

    public void saveMemo(Memo memo) {
        if (memo.getId() == 0) {
            memo.setId(idCounter++);
            memoList.add(memo);
        } else {
            Memo existingMemo = getMemoById(memo.getId());
            if (existingMemo != null) {
                existingMemo.setContent(memo.getContent());
            }
        }
    }

    public Memo getMemoById(int id) {
        return memoList.stream().filter(memo -> memo.getId() == id).findFirst().orElse(null);
    }

    public void deleteMemo(int id) {
        memoList.removeIf(memo -> memo.getId() == id);
    }
}
