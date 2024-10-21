package ru.skillfactorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.tgbot.entity.Income;

@Repository
public interface IncomeRepostory extends JpaRepository<Income,Long> {
}
