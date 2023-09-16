package com.example.solup.data;

import com.example.solup.entity.Card;
import com.example.solup.entity.account.Account;
import com.example.solup.entity.history.LoanHistory;
import com.example.solup.entity.history.TradeHistory;
import com.example.solup.entity.account.LoanAccount;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.history.LoanHistoryRepository;
import com.example.solup.repository.history.TradeHistoryRepository;
import com.example.solup.repository.account.LoanAccountRepository;
import com.example.solup.repository.card.CardRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TradeHistoryRepository tradeHistoryRepository;
    private final LoanHistoryRepository loanHistoryRepository;
    private final AccountRepository accountRepository;
    private final LoanAccountRepository loanAccountRepository;
    private final CardRepository cardRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (checkData()) return;
        InitTradeHistories();
        InitCards();
        InitLoanHistories();
    }

    public Boolean checkData() {
        return tradeHistoryRepository.existsById(1L);
    }

    // 카드 더미데이터
    public void InitCards() {
        Card card1 = new Card();
        card1.setName("신한");
        cardRepository.save(card1);

        Card card2 = new Card();
        card2.setName("싸피");
        cardRepository.save(card2);

        Card card3 = new Card();
        card3.setName("쏠쏠");
        cardRepository.save(card3);
    }

    // 거래내역 더미데이터
    public void InitTradeHistories() throws JsonProcessingException {
        String jsonDatas = "[\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"20000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"480000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"181503\",\n" +
                "    \"briefs\": \"포장재비\",\n" +
                "    \"withdraw\": \"10000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"470000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"105742\",\n" +
                "    \"briefs\": \"소모품비\",\n" +
                "    \"withdraw\": \"12000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"458000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"211344\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"538000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"618000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"688000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"215823\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"828000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230801\",\n" +
                "    \"tradeTime\": \"220507\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"888000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230802\",\n" +
                "    \"tradeTime\": \"105742\",\n" +
                "    \"briefs\": \"소모품비\",\n" +
                "    \"withdraw\": \"12000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"876000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230802\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"1046000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230802\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"1146000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230802\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"1216000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230802\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"1276000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230802\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"1366000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230803\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"1316000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230803\",\n" +
                "    \"tradeTime\": \"120214\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"50000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"1366000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230803\",\n" +
                "    \"tradeTime\": \"130320\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"50000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"1416000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230803\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"1516000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230803\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"1676000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230803\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"1756000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230804\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"1706000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230804\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"1886000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230804\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"1936000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230804\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"1976000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230804\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"2106000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230804\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"2226000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230805\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"20000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"2206000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230805\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"2326000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230805\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"2426000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230805\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"2496000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230805\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"2556000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230805\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"2646000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230806\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"2596000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230806\",\n" +
                "    \"tradeTime\": \"120214\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"2646000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230806\",\n" +
                "    \"tradeTime\": \"130320\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"50000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"2696000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230806\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"2796000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230806\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"2956000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230806\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"3036000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230807\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"70000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"2966000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230807\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"3066000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230807\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"3136000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230807\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"3186000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230807\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"3316000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230807\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"3426000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"60000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"3366000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"181503\",\n" +
                "    \"briefs\": \"포장재비\",\n" +
                "    \"withdraw\": \"10000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"3356000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"105742\",\n" +
                "    \"briefs\": \"소모품비\",\n" +
                "    \"withdraw\": \"12000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"3344000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"3449000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"3519000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"3599000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"3719000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230808\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"3829000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230809\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"3779000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230809\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"3929000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230809\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"4039000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230809\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"4129000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230809\",\n" +
                "    \"tradeTime\": \"145623\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"4179000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230810\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"80000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"4099000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230810\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"4269000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230810\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"4359000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230810\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"4379000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230810\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"4529000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230810\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"4599000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230811\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"90000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"4509000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230811\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"4599000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230811\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"4799000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230811\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"4839000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230811\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"4969000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230811\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"5089000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230812\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"5039000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230812\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"5209000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230812\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"5269000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230812\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"5319000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230812\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"5449000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230812\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"5569000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230813\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"60000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"5509000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230813\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"5689000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230813\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"5739000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230813\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"5779000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230813\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"5909000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230813\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"5979000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230814\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"5929000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230814\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"6049000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230814\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"6069000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230814\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"6149000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230814\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"6279000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230814\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"6369000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230814\",\n" +
                "    \"tradeTime\": \"153822\",\n" +
                "    \"briefs\": \"가스비\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"부산도시가스\",\n" +
                "    \"balance\": \"6459000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"6409000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"6589000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"6639000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"6679000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"6809000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"180023\",\n" +
                "    \"briefs\": \"임대료\",\n" +
                "    \"withdraw\": \"3000000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"최동수\",\n" +
                "    \"balance\": \"3809000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"181503\",\n" +
                "    \"briefs\": \"포장재비\",\n" +
                "    \"withdraw\": \"15000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"3794000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"105742\",\n" +
                "    \"briefs\": \"소모품비\",\n" +
                "    \"withdraw\": \"13000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"3781000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230815\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"3901000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230816\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"40000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"3861000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230816\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"4031000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230816\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"4081000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230816\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"4121000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230816\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"4251000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230816\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"4371000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"70000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"4301000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"103324\",\n" +
                "    \"briefs\": \"전기세\",\n" +
                "    \"withdraw\": \"220000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"한국전력공사\",\n" +
                "    \"balance\": \"4081000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"103422\",\n" +
                "    \"briefs\": \"수도세\",\n" +
                "    \"withdraw\": \"70000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"한국수자원공사\",\n" +
                "    \"balance\": \"4011000\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"4191000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"87000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"4278000\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70200\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"4348200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"4428200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230817\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"4538200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230818\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"80000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"4458200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230818\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"4608200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230818\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"98000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"4686200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230818\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"4776200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230818\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"4906200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230818\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"134000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"5040200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230819\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"90000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"4950200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230819\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"5020200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230819\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"5170200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230819\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"100000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"5210200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230819\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"5290200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230819\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"5450200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230820\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"5400200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230820\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"5570200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230820\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"5620200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230820\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"5660200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230820\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"5790200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230820\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"5910200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230821\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"60000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"5850200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230821\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"6030200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230821\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"6080200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230821\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90600\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"6120200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230821\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"108000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"6250200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230821\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"6370200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"6320200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"181503\",\n" +
                "    \"briefs\": \"포장재비\",\n" +
                "    \"withdraw\": \"30000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"6290200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"105742\",\n" +
                "    \"briefs\": \"소모품비\",\n" +
                "    \"withdraw\": \"12000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"6278200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"6448200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"6498200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"6538200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"6668200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"152323\",\n" +
                "    \"briefs\": \"광고 수수료\",\n" +
                "    \"withdraw\": \"100000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피미디어\",\n" +
                "    \"balance\": \"6568200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230822\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"6688200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230823\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"6638200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230823\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"99000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"6818200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230823\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"6868200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230823\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"6908200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230823\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"97000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"7038200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230823\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"7158200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230824\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"40000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"7118200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230824\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"113000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"7288200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230824\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"7338200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230824\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"7378200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230824\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"7508200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230824\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"7628200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"40000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"7588200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"7768200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"7818200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"7858200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"7988200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"153323\",\n" +
                "    \"briefs\": \"직원 급여\",\n" +
                "    \"withdraw\": \"1000000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"박진위\",\n" +
                "    \"balance\": \"6988200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"7108200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230826\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"80000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"7028200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230826\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"100000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"7198200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230826\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"7288200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230826\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"7308200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230826\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"50000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"7458200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230826\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"7528200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230827\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"90000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"7438200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230827\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"7528200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230827\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"7728200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230827\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"98000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"7768200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230827\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"7898200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230827\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"8018200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230828\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"7968200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230828\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"8138200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230828\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"89000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"8198200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230828\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"99000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"8248200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230828\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"93000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"8378200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230828\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"8498200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"8448200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"8618200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"8668200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"121000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"8708200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"8838200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"8958200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"181503\",\n" +
                "    \"briefs\": \"포장재비\",\n" +
                "    \"withdraw\": \"28000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"8930200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230829\",\n" +
                "    \"tradeTime\": \"105742\",\n" +
                "    \"briefs\": \"소모품비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"싸피물산\",\n" +
                "    \"balance\": \"8880200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230830\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"80000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"8800200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230830\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"89000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"8980200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230830\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"98000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"9030200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230830\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"79000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"9070200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230830\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"9200200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230830\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"97800\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"9320200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230831\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"90000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"9230200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230831\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"79000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"9410200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230831\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"98000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"9460200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230831\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"83000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"9500200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230831\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"110000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"9630200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230831\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"9750200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230901\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"9700200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230901\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"89000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"9790200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230901\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"127000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"9990200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230901\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"10030200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230901\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"67000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"10160200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230901\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"10280200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230902\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"70000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"10210200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230902\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"50000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"10380200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230902\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"60000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"10440200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230902\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"50000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"10490200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230902\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"10620200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230902\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"10740200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230903\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"10690200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230903\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"10870200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230903\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"10920200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230903\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"70000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"10960200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230903\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"97000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"11090200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230903\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"89000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"11160200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230904\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"50000\",\n" +
                "    \"deposit\": \"\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"11110200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230904\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"11230200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230904\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"67000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"11250200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230904\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"80000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"11330200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230904\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"11460200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230904\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"11550200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230905\",\n" +
                "    \"tradeTime\": \"093028\",\n" +
                "    \"briefs\": \"재료비\",\n" +
                "    \"withdraw\": \"60000\",\n" +
                "    \"deposit\": \"90000\",\n" +
                "    \"content\": \"쏠쏠푸드\",\n" +
                "    \"balance\": \"11580200\",\n" +
                "    \"category\": \"2\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230905\",\n" +
                "    \"tradeTime\": \"140214\",\n" +
                "    \"briefs\": \"현금결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"88000\",\n" +
                "    \"content\": \"김싸피\",\n" +
                "    \"balance\": \"11760200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한은행\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230905\",\n" +
                "    \"tradeTime\": \"211704\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"77000\",\n" +
                "    \"content\": \"신한카드\",\n" +
                "    \"balance\": \"11810200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"신한카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230905\",\n" +
                "    \"tradeTime\": \"213821\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"98000\",\n" +
                "    \"content\": \"싸피카드\",\n" +
                "    \"balance\": \"11850200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"싸피카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230905\",\n" +
                "    \"tradeTime\": \"215504\",\n" +
                "    \"briefs\": \"카드결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"130000\",\n" +
                "    \"content\": \"쏠쏠카드\",\n" +
                "    \"balance\": \"11980200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"쏠쏠카드\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230905\",\n" +
                "    \"tradeTime\": \"153701\",\n" +
                "    \"briefs\": \"땡겨요결제 입금\",\n" +
                "    \"withdraw\": \"\",\n" +
                "    \"deposit\": \"120000\",\n" +
                "    \"content\": \"땡겨요\",\n" +
                "    \"balance\": \"12100200\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"name\": \"땡겨요\"\n" +
                "  }\n" +
                "]\n";

        ObjectMapper objectMapper = new ObjectMapper();
        List<TradeHistoryTemp> temps = objectMapper.readValue(jsonDatas, new TypeReference<List<TradeHistoryTemp>>() {});

        Account account = new Account();
        account.setBank("신한은행");
        account.setNumber("110480000001");
        account.setOpenDate(LocalDate.parse("20230731", DateTimeFormatter.BASIC_ISO_DATE));
        account.setCurrencyType("KRW");
        accountRepository.save(account);

        for (TradeHistoryTemp temp : temps) {
            TradeHistory tradeHistory = new TradeHistory();
            tradeHistory.setTradeDate(LocalDate.parse(temp.getTradeDate(), DateTimeFormatter.BASIC_ISO_DATE));
            tradeHistory.setTradeTime(LocalTime.parse(temp.getTradeTime(), DateTimeFormatter.ofPattern("HHmmss")));
            tradeHistory.setBriefs(temp.getBriefs());
            tradeHistory.setWithdraw(Objects.equals(temp.getWithdraw(), "") ? null : Integer.parseInt(temp.getWithdraw()));
            tradeHistory.setDeposit(Objects.equals(temp.getDeposit(), "") ? null : Integer.parseInt(temp.getDeposit()));
            tradeHistory.setContent(temp.getContent());
            tradeHistory.setBalance(Integer.parseInt(temp.getBalance()));
            tradeHistory.setCategory(Integer.parseInt(temp.getCategory()));
            tradeHistory.setName(temp.getName());
            tradeHistory.setAccount(account);
            tradeHistory.setIsCategorized(false);
            tradeHistoryRepository.save(tradeHistory);
        }
    }

    // 대출 상환 더미데이터
    public void InitLoanHistories() throws JsonProcessingException {

        String jsonData = "[\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230825\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-47200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"45\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230725\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-48400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"44\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230625\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-49600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"43\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230525\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-50800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"42\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230425\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-52000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"41\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230325\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-53200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"40\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-54400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"39\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20230125\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-55600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"38\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20221225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-56800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"37\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20221125\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-58000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"36\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20221025\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-59200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"35\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220925\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-60400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"34\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220825\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-61600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"33\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220725\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-62800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"32\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220625\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-64000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"31\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220525\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-65200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"30\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220425\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-66400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"29\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220325\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-67600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"28\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-68800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"27\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20220125\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-70000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"26\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20211225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-71200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"25\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20211125\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-72400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"24\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20211025\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-73600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"23\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210925\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-74800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"22\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210825\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-76000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"21\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210725\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-77200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"20\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210625\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-78400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"19\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210525\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-79600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"18\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210425\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-80800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"17\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210325\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-82000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"16\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-83200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"15\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20210125\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-84400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"14\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20201225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-85600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"13\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20201125\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-86800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"12\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20201025\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-88000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"11\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200925\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-89200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"10\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200825\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-90400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"9\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200725\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-91600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"8\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200625\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-92800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"7\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200525\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-94000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"6\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200425\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-95200000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"5\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200325\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-96400000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"4\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-97600000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"3\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20200125\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-98800000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"2\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"tradeDate\": \"20191225\",\n" +
                "    \"tradeTime\": \"160159\",\n" +
                "    \"briefs\": \"모바일\",\n" +
                "    \"withdraw\": \"0\",\n" +
                "    \"deposit\": \"1200000\",\n" +
                "    \"content\": \"신한캐피탈\",\n" +
                "    \"balance\": \"-100000000\",\n" +
                "    \"name\": \"원신한\",\n" +
                "    \"category\": \"1\",\n" +
                "    \"tradeNumber\": \"1\"\n" +
                "  }\n" +
                "]";

        LoanAccount loanAccount = new LoanAccount();
        loanAccount.setBankName("신한은행");
        loanAccount.setAccountName("대출계좌");
        loanAccount.setNumber("110480000002");
        loanAccount.setWithdrawalAmount(52800000);
        loanAccount.setOpenDate(LocalDate.parse("20191224", DateTimeFormatter.BASIC_ISO_DATE));
        loanAccount.setLoanableAmount(100000000);
        loanAccount.setExpirationDate(LocalDate.parse("20280101", DateTimeFormatter.BASIC_ISO_DATE));
        loanAccount.setInterestRate(8.8);
        loanAccount.setLastDate(LocalDate.parse("20230825", DateTimeFormatter.BASIC_ISO_DATE));
        loanAccount.setManagement("영업부");
        loanAccount.setRepeatNumber(1);

        loanAccountRepository.save(loanAccount);

        ObjectMapper objectMapper = new ObjectMapper();
        List<LoanHistoryTemp> temps = objectMapper.readValue(jsonData, new TypeReference<List<LoanHistoryTemp>>() {});

        int number = 45;
        for (LoanHistoryTemp temp : temps) {
            LoanHistory loanHistory = new LoanHistory();
            loanHistory.setTradeDate(LocalDate.parse(temp.getTradeDate(), DateTimeFormatter.BASIC_ISO_DATE));
            loanHistory.setTradeTime(LocalTime.parse(temp.getTradeTime(), DateTimeFormatter.ofPattern("HHmmss")));
            loanHistory.setBriefs(temp.getBriefs());
            loanHistory.setWithdraw(Objects.equals(temp.getWithdraw(), "") ? null : Integer.parseInt(temp.getWithdraw()));
            loanHistory.setDeposit(Objects.equals(temp.getDeposit(), "") ? null : Integer.parseInt(temp.getDeposit()));
            loanHistory.setContent(temp.getContent());
            loanHistory.setLoanBalance(Integer.parseInt(temp.getBalance()));
            loanHistory.setCategory(Integer.parseInt(temp.getCategory()));
            loanHistory.setName(temp.getName());
            loanHistory.setTradeNumber(number);
            loanHistory.setLoanAccount(loanAccount);
            loanHistoryRepository.save(loanHistory);

            number --;
        }

    }

    @Getter
    @Setter
    static class TradeHistoryTemp {
        @JsonProperty("tradeDate")
        private String tradeDate;
        @JsonProperty("tradeTime")
        private String tradeTime;
        @JsonProperty("briefs")
        private String briefs;
        @JsonProperty("withdraw")
        private String withdraw;
        @JsonProperty("deposit")
        private String deposit;
        @JsonProperty("content")
        private String content;
        @JsonProperty("balance")
        private String balance;
        @JsonProperty("category")
        private String category;
        @JsonProperty("name")
        private String name;
    }

    @Getter
    @Setter
    static class LoanHistoryTemp {
        @JsonProperty("tradeDate")
        private String tradeDate;
        @JsonProperty("tradeTime")
        private String tradeTime;
        @JsonProperty("briefs")
        private String briefs;
        @JsonProperty("withdraw")
        private String withdraw;
        @JsonProperty("deposit")
        private String deposit;
        @JsonProperty("content")
        private String content;
        @JsonProperty("balance")
        private String balance;
        @JsonProperty("category")
        private String category;
        @JsonProperty("name")
        private String name;
        @JsonProperty("tradeNumber")
        private Integer tradeNumber;
    }
}
