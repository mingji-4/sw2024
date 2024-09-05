package org.comstudy.myapp.controller;

import org.comstudy.myapp.dao.MemoDAO;
import org.comstudy.myapp.model.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemoController {

    private MemoDAO memoDAO = new MemoDAO();

    @GetMapping("/")
    public String listMemos(Model model) {
        model.addAttribute("memos", memoDAO.getAllMemos());
        return "index";
    }

    @GetMapping("/form")
    public String newMemoForm(Model model) {
        model.addAttribute("memo", new Memo());
        return "form";
    }

    @PostMapping("/save")
    public String saveMemo(@ModelAttribute("memo") Memo memo) {
        memoDAO.saveMemo(memo);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editMemo(@PathVariable("id") int id, Model model) {
        model.addAttribute("memo", memoDAO.getMemoById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMemo(@PathVariable("id") int id) {
        memoDAO.deleteMemo(id);
        return "redirect:/";
    }
}
