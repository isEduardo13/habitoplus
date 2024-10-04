package com.habitoplus.habitoplusback.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.habitoplus.habitoplusback.Model.Notification;
import com.habitoplus.habitoplusback.Service.NotificationService;
import java.util.List;

@RestController
@RequestMapping("notification")
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping 
     public List<Notification> getAllNotifications() {
         return notificationService.getAllNotifications();
     }
     @GetMapping("/Mensaje")
        public Notification getNotificationByMensaje(@RequestParam String mensaje) {
            return notificationService.getNotificationByMensaje(mensaje);
        }
        @GetMapping("/{id}")
        public Notification getNotificationById(@PathVariable int id) {
            return notificationService.getNotificationById(id);
        }
        @PostMapping
        public Notification addNotification( Notification notification) {
            return notificationService.addNotification(notification);
        }
        @PutMapping
        public boolean updateNotification(Notification notification) {
            return notificationService.updateNotification(notification);
        }
        @DeleteMapping("/{id}")
        public boolean deleteNotification(@PathVariable int id) {
            return notificationService.deleteNotification(id);
        }

}