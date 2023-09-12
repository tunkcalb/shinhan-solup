package com.example.solup.service.user;

import com.example.solup.dto.StaffDto;
import com.example.solup.dto.revenue.RevenueAnalysisDto;
import com.example.solup.dto.store.StoreDto;
import com.example.solup.dto.user.LoginDto;
import com.example.solup.dto.user.RegistAccountDto;
import com.example.solup.dto.user.SignupDto;
import com.example.solup.entity.*;
import com.example.solup.entity.expense.Fixed;
import com.example.solup.entity.expense.Variable;
import com.example.solup.exception.type.DuplicatedValueException;
import com.example.solup.exception.type.NotSameDataValueException;
import com.example.solup.repository.StaffRepository;
import com.example.solup.repository.account.AccountRepository;
import com.example.solup.repository.account.TradeHistoryRepository;
import com.example.solup.repository.card.CardRepository;
import com.example.solup.repository.expense.FixedRepository;
import com.example.solup.repository.expense.VariableRepository;
import com.example.solup.repository.user.StoreRepository;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final CardRepository cardRepository;
    private final VariableRepository variableRepository;
    private final AccountRepository accountRepository;
    private final StaffRepository staffRepository;
    private final TradeHistoryRepository tradeHistoryRepository;
    private final FixedRepository fixedRepository;
  
    private String delivery = "땡겨요";

    public SignupDto.Response save(SignupDto.Request request) throws DuplicatedValueException {
        if (userRepository.findByUsername(request.getUsername()) == null) {
            User user = userRepository.save(request.toEnity());
            return SignupDto.Response.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .build();
        }
        throw new DuplicatedValueException("이미 존재하는 아이디입니다");
    }

    public LoginDto.Response login(LoginDto.Request request) throws NotSameDataValueException {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new NotSameDataValueException("존재하지 않는 ID입니다");
        }
        String password = user.getPassword();
        if (!request.getPassword().equals(password)) {
            throw new NotSameDataValueException("비밀번호가 일치하지 않습니다.");

        }
        return LoginDto.Response.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
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
            LocalDate date = tradeHistory.getTradeDate();
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

    public RegistAccountDto.Response registAccount(Long userId, RegistAccountDto.Request request) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        if (user == null) {
            throw new NotSameDataValueException("존재하지 않는 회원입니다.");
        }

        Account account = accountRepository.findByNumber(request.getAccountNumber());
        if (account == null) throw new NoSuchElementException("존재하지 않는 계좌입니다.");

        user.setAccount(account);

        userRepository.save(user);
        return RegistAccountDto.Response.builder()
                .accountId(account.getId())
                .build();
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
                        .id(staff.getId())
                        .name(staff.getName())
                        .bank(staff.getBank())
                        .account(staff.getAccount())
                        .salary(staff.getSalary())
                        .build())
                .collect(Collectors.toList());

    }

    public String paySalary(Long userId, Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new NotFoundException("해당 직원을 찾을 수 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        String account = staff.getAccount();

        TradeHistory lastTradeHistory = tradeHistoryRepository.findFirstByAccountIdOrderByIdDesc(user.getAccount().getId());

        Integer currentBalance = lastTradeHistory.getBalance();

        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setTradeDate(LocalDate.now());
        tradeHistory.setTradeTime(LocalTime.now());
        tradeHistory.setBriefs("인건비");
        tradeHistory.setWithdraw(staff.getSalary());
        tradeHistory.setCategory(2);
        tradeHistory.setContent(staff.getName() + " " + staff.getAccount());
        tradeHistory.setBalance(currentBalance-staff.getSalary());
        tradeHistory.setName("신한은행");
        tradeHistory.setAccount(user.getAccount());

        Fixed fixed = new Fixed();
        fixed.setCategory("인건비");
        fixed.setContent(staff.getName() + " " + staff.getAccount());
        fixedRepository.save(fixed);

        tradeHistory.setFixed(fixed);

        tradeHistoryRepository.save(tradeHistory);

        return staff.getBank() + " " + staff.getAccount();
    }
}

