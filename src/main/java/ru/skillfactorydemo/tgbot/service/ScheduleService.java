package ru.skillfactorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfactorydemo.tgbot.entity.ActiveChat;
import ru.skillfactorydemo.tgbot.repository.ActiveChatRepository;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ActiveChatRepository activeChatRepository;
    private final BotService botService;
    private final CentralRussianBankService centralRussianBankService;
    private final List<ValuteCursOnDate> previousRates=new ArrayList<>();

    @Scheduled(cron="0 0 0/3 ? * *")
    public void notifyAboutChangesInCurrencyRate(){
        try{
            List<ValuteCursOnDate> curentRates=centralRussianBankService.getCurrenciesFromCbr();
            Set<Long> activeChatIds=activeChatRepository.findAll().stream().map(ActiveChat::getChatId).collect(Collectors.toSet());
            if(!previousRates.isEmpty()){
                for(int index=0; index< curentRates.size();index++){
                    if(curentRates.get(index).getCourse()-previousRates.get(index).getCourse()>=10.0){
                        botService.sendNotificationToAllActiveChats("Курс "+curentRates.get(index).getName()+" увеличился на 10 рублей",activeChatIds);
                    } else if (curentRates.get(index).getCourse()-previousRates.get(index).getCourse()<=10) {
                        botService.sendNotificationToAllActiveChats("Курс "+curentRates.get(index).getName()+" уменьшился на 10 рублей", activeChatIds);
                    }
                }
            }else{
                    previousRates.addAll(curentRates);
                }
        }catch(DatatypeConfigurationException e){
           log.error("Возникла проблема при получении данных от сервисов ЦБ РФ", e);
        }
    }
}
