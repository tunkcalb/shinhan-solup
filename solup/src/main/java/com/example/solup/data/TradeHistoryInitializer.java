package com.example.solup.data;

import com.example.solup.entity.TradeHistory;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TradeHistoryInitializer implements CommandLineRunner {

    private final TradeHistoryRepository tradeHistoryRepository;
    
    @Override
    public void run(String... args) throws Exception {
        String jsonPath = "src/main/resources/static/DummyData.json";
        String jsonDatas = Files.readString(Paths.get(jsonPath));

        ObjectMapper objectMapper = new ObjectMapper();
        List<Temp> temps = objectMapper.readValue(jsonDatas, new TypeReference<List<Temp>>() {});

        for (Temp temp : temps) {
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
            tradeHistoryRepository.save(tradeHistory);
        }
    }

    @Getter
    @Setter
    static class Temp {
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
}
