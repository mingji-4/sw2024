package org.comstudy.myweb.model;

import java.util.ArrayList;
import java.util.List;

public class MemoDAO {
	// 데이터베이스 사용 전에 임시로 저장할 리스트
	private List<Memo> memos;
	// primary key 역학
	private int nextId;

	public MemoDAO() {
		memos = new ArrayList<Memo>();
		nextId = 1;
		
		// 임시 데이터 저장
		for(int i=0; i<5; i++) {			
			addMemo(new Memo("제목"+i, "내용"+i));
		}
	}

	public List<Memo> getAllMemos() {
		System.out.println(">>>> getAllMemmos() ...");
		return memos;
	}

	public Memo getMemoById(int id) {
		for (Memo memo : memos) {
			if (memo.getId() == id) {
				return memo;
			}
		}
		return null;
	}

	public void addMemo(Memo memo) {
		memo.setId(nextId++);
		memos.add(memo);
	}

	public void updateMemo(Memo updatedMemo) {
		for (int i = 0; i < memos.size(); i++) {
			if (memos.get(i).getId() == updatedMemo.getId()) {
				memos.set(i, updatedMemo);
				break;
			}
		}
	}

	public void deleteMemo(int id) {
		memos.removeIf(memo -> memo.getId() == id);
	}
}