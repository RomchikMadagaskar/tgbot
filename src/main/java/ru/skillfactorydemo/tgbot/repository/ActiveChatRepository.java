package ru.skillfactorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillfactorydemo.tgbot.entity.ActiveChat;

import java.util.Optional;

public interface ActiveChatRepository extends JpaRepository<ActiveChat,Long> {

    Optional<ActiveChat> findActiveChatByChatId(Long chatID);
}
