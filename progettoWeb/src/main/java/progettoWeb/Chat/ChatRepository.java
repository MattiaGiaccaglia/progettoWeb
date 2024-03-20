package progettoWeb.Chat;

import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<ChatRecord, Integer> {
}
