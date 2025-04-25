package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    @Query(
        value = """
                SELECT n FROM Notification n WHERE n.recipient = ?1 OR n.recipientType = "ALL" ORDER BY n.sentTime DESC
                """
    )
    List<Notification> findUserNotifications(Account id);
}
