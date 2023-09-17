import React, { useState, useEffect } from "react";

import style from "./CategorizeModal.module.css";

function CategorizeModal({ isOpen, onClose, history, userId, onClick }) {
  const [expenseType, setExpenseType] = useState("Variable");
  const [expenseCategory, setExpenseCategory] = useState("");
  const [showVariable, setShowVariable] = useState(false);
  const [showFixed, setShowFixed] = useState(false);

  useEffect(() => {
    if (expenseType === "Variable") {
      setShowFixed(false);
      setShowVariable(true);
    } else if (expenseType === "Fixed") {
      setShowFixed(true);
      setShowVariable(false);
    } else {
      setShowVariable(false);
      setShowFixed(false);
    }
  }, [expenseType]);

  const updateExpenseType = (type) => {
    setExpenseType(type);
    setExpenseCategory("");
  };

  const updateExpenseCategory = (category) => {
    setExpenseCategory(category);
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
    onClick(expenseType, expenseCategory);
  };

  return (
    <div>
      {isOpen && (
        <div className={style.modal}>
          <div className={style.modalContent}>
            <h2>{history.tradeDate}</h2>
            <h3>{history.content}</h3>
            <h3>{history.briefs}</h3>
            <div className={style.expenseTypeContainer}>
              <div
                className={`${style.expenseType} ${
                  expenseType === "Variable" ? style.selected : ""
                }`}
                onClick={() => updateExpenseType("Variable")}>
                변동비
              </div>
              <div
                className={`${style.expenseType} ${
                  expenseType === "Fixed" ? style.selected : ""
                }`}
                onClick={() => updateExpenseType("Fixed")}>
                고정비
              </div>
            </div>
            {expenseType === "Variable" && (
              <div className={style.categoryContainer}>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "관리비" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("관리비")}>
                  관리비
                </div>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "재료비" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("재료비")}>
                  재료비
                </div>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "포장재비" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("포장재비")}>
                  포장재비
                </div>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "소모품비" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("소모품비")}>
                  소모품비
                </div>
              </div>
            )}
            {expenseType === "Fixed" && (
              <div className={style.categoryContainer}>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "임차료" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("임차료")}>
                  임차료
                </div>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "인건비" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("인건비")}>
                  인건비
                </div>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "보험료" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("보험료")}>
                  보험료
                </div>
                <div
                  className={`${style.categoryBox} ${
                    expenseCategory === "대출이자" ? style.selected : ""
                  }`}
                  onClick={() => updateExpenseCategory("대출이자")}>
                  대출이자
                </div>
              </div>
            )}
            <button
              onClick={() => {
                categorize();
                onClose();
              }}>
              분류하기
            </button>
            <button onClick={onClose}>닫기</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default CategorizeModal;
