package ru.skillfactorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.skillfactorydemo.tgbot.entity.Income;
import ru.skillfactorydemo.tgbot.entity.Spend;
import ru.skillfactorydemo.tgbot.repository.IncomeRepostory;
import ru.skillfactorydemo.tgbot.repository.SpendRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private static final String ADD_INCOME="/addincome";

    private final IncomeRepostory incomeRepostory;
    private final SpendRepository spendRepository;

    public String addFinanceOperation(String operationType,String price,Long chatId){
        String message;
        if(ADD_INCOME.equalsIgnoreCase(operationType)){
            Income income= new Income();
            income.setChatId(chatId);
            income.setIncome(new BigDecimal(price));
            incomeRepostory.save(income);
            message="Доход в размере "+ price + " был успешно добавлен";
        }else{
            Spend spend=new Spend();
            spend.setChatId(chatId);
            spend.setSpend(new BigDecimal(price));
            spendRepository.save(spend);
            message="Расход в размере "+ price + " был успешно добавлен";
        }
        return message;
    }
}
