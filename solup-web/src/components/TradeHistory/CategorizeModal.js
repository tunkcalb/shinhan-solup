import React, { useState, useEffect } from "react";

import style from "./CategorizeModal.module.css";

function CategorizeModal({ isOpen, onClose, history, userId }) {
  const [expenseType, setExpenseType] = useState("");
  const [expenseCategory, setExpenseCategory] = useState("");

  useEffect(() => {}, []);

  const updateExpenseType = (e) => {
    setExpenseType(e.target.value);
  };

  const updateExpenseCategory = (e) => {
    setExpenseCategory(e.target.value);
  };

  const categorize = async () => {
    try {
      const response = await fetch(`/account/${userId}/trade-history`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          tradeHistoryId: history.id,
          content: history.content,
          briefs: history.briefs,
          expenseType,
          expenseCategory,
        }),
      });
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div>
      {isOpen && (
        <div className={style.modal}>
          <div className={style.modalContent}>
            <div>{history.content}</div>
            <div>{history.briefs}</div>
            <div>
              <label>
                Expense Type :
                <select value={expenseType} onChange={updateExpenseType}>
                  <option value="">선택</option>
                  <option value="Variable">변동비</option>
                  <option value="Fixed">고정비</option>
                </select>
              </label>
            </div>
            <div>
              <label>
                Expense Category :
                <select
                  value={expenseCategory}
                  onChange={updateExpenseCategory}>
                  <option value="">선택</option>
                  {expenseType === "Variable" ? (
                    <>
                      <option value="관리비">관리비</option>
                      <option value="재료비">재료비</option>
                      <option value="포장재비">포장재비</option>
                      <option value="소모품비">소모품비</option>
                    </>
                  ) : (
                    <>
                      <option value="임차료">임차료</option>
                      <option value="인건비">인건비</option>
                      <option value="보험료">보험료</option>
                      <option value="대출이자">대출이자</option>
                    </>
                  )}
                </select>
              </label>
            </div>
            <button onClick={categorize}>분류하기</button>
            <button onClick={onClose}>닫기</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default CategorizeModal;
