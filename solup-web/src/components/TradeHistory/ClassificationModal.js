import React, { useState, useEffect } from "react";

function ClassificationModal ({history, onClassify, onClose, userId}) {
    const [expenseType, setExpenseType] = useState("고정비");
    const [expenseCategory, setExpenseCategory] = useState("임차료");

    const handleClassifyClick = async () => {
        const data = {
            tradeHistoryId: history.id,
            briefs: history.briefs,
            content: history.content,
            expenseType,
            expenseCategory,
        };

        try {
            const response = await fetch(`account/${userId}/trade-history`, {
                method: "POST",
                headers: {
                    "Content=Type": "application/json",
                },
                body: JSON.stringify(data),
            })
        } catch (error) {
            console.log(error);
        }

        onClose();
    }

    return (
        <div className="modal">
            <h3>거래내역 분류</h3>
            <p>시간 : {history.tradeTime}</p>
            <p>내용 : {history.content}</p>
            <p>적요 : {history.briefs}</p>
            <p>출금액 : {history.withdraw}</p>

            <label>
                비용 유형 :
                <select
                    value={expenseType}
                    onChange={e => setExpenseType(e.target.value)}
                >   
                    <option value="고정비">고정비</option>
                    <option value="변동비">변동비</option>
                </select>
            </label>

            {expenseType === "고정비" ? (
                <lable>
                    카테고리 : 
                    <select
                        value={expenseCategory}
                        onChange={e=>setExpenseCategory(e.target.value)}
                    >
                        <option value="임차료">임차료</option>
                        <option value="인건비">인건비</option>
                        <option value="보험료">보험료</option>
                        <option value="대출이자">대출이자</option>
                    </select>
                </lable>
            ) : (
                <label>
                    비용 카테고리:
                    <select
                        value={expenseCategory}
                        onChange={(e) => setExpenseCategory(e.target.value)}
                    >
                        <option value="관리비">관리비</option>
                        <option value="재료비">재료비</option>
                        <option value="포장재비">포장재비</option>
                        <option value="소모품비">소모품비</option>
                    </select>
                </label>
            )}

            <button onClick={handleClassifyClick}>분류하기</button>
            <button onClick={onClose}>취소</button>       
        </div>
    )
}

export default ClassificationModal;