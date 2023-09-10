package com.example.solup.service.user;

import com.example.solup.dto.StaffDto;
import com.example.solup.dto.revenue.RevenueAnalysisDto;
import com.example.solup.dto.store.StoreDto;
import com.example.solup.dto.user.UserDto;
import com.example.solup.entity.*;
import com.example.solup.entity.expense.Variable;
import com.example.solup.exception.type.DuplicatedValueException;
import com.example.solup.exception.type.NotSameDataValueException;
import com.example.solup.repository.StaffRepository;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.example.solup.repository.card.CardRepository;
import com.example.solup.repository.expense.VariableRepository;
import com.example.solup.repository.user.StoreRepository;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final CardRepository cardRepository;
    private final VariableRepository variableRepository;
    private final StaffRepository staffRepository;
    private String delivery = "땡겨요";

    public UserDto save(UserDto userDto) throws DuplicatedValueException {
        if (userRepository.findByUsername(userDto.getUsername()) == null) {
            return userRepository.save(userDto.toEnity()).toDto();
        }
        throw new DuplicatedValueException("이미 존재하는 아이디입니다");
    }

    public UserDto login(UserDto userDto) throws NotSameDataValueException {
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            throw new NotSameDataValueException("존재하지 않는 ID입니다");
        }
        String password = user.getPassword();
        if (!userDto.getPassword().equals(password)) {
            throw new NotSameDataValueException("비밀번호가 일치하지 않습니다.");

        }
        return user.toDto();
    }

    public String findByUsername(String username) {
        if (userRepository.findByUsername(username) != null) return "이미 존재하는 ID입니다";
        return "사용 가능한 ID입니다";
    }

    public StoreDto.Response registStore(Long userId, StoreDto.Request request) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        if (user == null) {
            throw new NotSameDataValueException("존재하지 않는 회원입니다.");
        }
        Store store = storeRepository.save(request.toEntity());
        user.setStore(store);
        userRepository.save(user);
        return store.toDto();
    }

    public RevenueAnalysisDto.Response getRevenueAnalysis(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        if (user == null) {
            throw new NotSameDataValueException("존재하지 않는 회원입니다.");
        }
        Account account = user.getAccount();

        if (account == null) {
            throw new NotSameDataValueException("계좌를 등록해주세요");
        }
        LocalDateTime now = LocalDateTime.now();
        int currentMonth = now.getMonthValue();
        int currentday = now.getDayOfMonth();
        List<String> cardNames = cardRepository.findAll()
                .stream().map(Card::getName).collect(Collectors.toList());
        Map<Integer, Integer> monthlyRevenue = new HashMap<>();
        Map<String, Integer> cardRevenue = new HashMap<>();
        Map<String, Integer> analysis = new HashMap<>();
        int deliverySum = 0;
        int cash = 0;
        int estimatedRevenue = 0;
        int lastMonthExpenses = 0;

        for (TradeHistory tradeHistory : account.getTradeHistories()) {
            LocalDateTime date = tradeHistory.getDate();
            int month = date.getMonthValue();
            int deposit = tradeHistory.getDeposit();
            String content = tradeHistory.getContent();

            // 해당 월의 출금을 누적.
            int currentDepositSum = monthlyRevenue.getOrDefault(month, 0);
            monthlyRevenue.put(month, currentDepositSum + deposit);

            // 이전달 변동비
            if (month == currentMonth - 1 || month == currentMonth + 11) {
                if (tradeHistory.getVariable() != null) lastMonthExpenses += deposit;
            }

            if (month != currentMonth) continue;

            if (tradeHistory.getVariable() != null) {
                Variable variable = variableRepository.findById(tradeHistory.getVariable().getId()).get();
                String category = variable.getCategory();
                int analysisSum = analysis.getOrDefault(category, 0);
                analysis.put(category, analysisSum + deposit);
            }
            // 이번달 매출 분석

            // 분류별 매출
            boolean found = false;
            if (tradeHistory.getContent().contains(delivery)) {
                deliverySum += deposit;
                found = true;
            }
            for (String cardName : cardNames) {
                if (content.contains(cardName)) {
                    int cardSum = cardRevenue.getOrDefault(cardName, 0);
                    cardRevenue.put(cardName, cardSum + deposit);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cash += deposit;
            }
            // 예상 매출 계산
            estimatedRevenue = monthlyRevenue.getOrDefault(currentMonth, 0);
            estimatedRevenue *= 30;
            estimatedRevenue /= currentday;


        }
        int currentDepositSum = monthlyRevenue.getOrDefault(currentMonth, 0);

        RevenueAnalysisDto.Response response = RevenueAnalysisDto.Response
                .builder()
                .monthlyRevenue(monthlyRevenue)
                .monthRevenue(String.valueOf(currentDepositSum))
                .cardRevenue(cardRevenue)
                .deliverySum(String.valueOf(deliverySum))
                .cash(String.valueOf(cash))
                .estimatedRevenue(String.valueOf(estimatedRevenue))
                .lastMonthExpenses(String.valueOf(lastMonthExpenses))
                .analysis(analysis)
                .build();
        return response;
    }

    public StaffDto.Response registStaff(Long userId, StaffDto.Request request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        Staff staff = new Staff();
        staff.setUser(user);
        staff.setName(request.getName());
        staff.setBank(request.getBank());
        staff.setAccount(request.getAccount());
        staff.setHourlyRate(request.getHourlyRate());
        staff.setWorkDay(request.getWorkDay());
        staff.setWorkHour(request.getWorkHour());
        staff.setPayDay(request.getPayDay());
        staff.setSalary(request.getSalary());
        staffRepository.save(staff);

        return StaffDto.Response.builder()
                .name(request.getName())
                .bank(request.getBank())
                .account(request.getAccount())
                .salary(request.getSalary())
                .build();
    }

    public List<StaffDto.Response> getStaffes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        List<Staff> staffes = user.getStaffes();

        return staffes.stream()
                .map(staff -> StaffDto.Response.builder()
                        .name(staff.getName())
                        .bank(staff.getBank())
                        .account(staff.getAccount())
                        .salary(staff.getSalary())
                        .build())
                .collect(Collectors.toList());

    }
}

