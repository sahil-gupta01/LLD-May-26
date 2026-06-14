// Another great example of Decorator design is software notifications system. 
// We can have a base notification class (Concrete Component) and various notification types (Concrete Decorators) 
// that can be added to the notification dynamically without changing the base notification class.

// Component
interface Notification {
    String getDescription();
    void sendNotification();
}

class EmailNotification implements Notification {
    @Override
    public String getDescription() {
        return "Email Notification";
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending Email Notification");
    }
}

class SMSNotification implements Notification {
    @Override
    public String getDescription() {
        return "SMS Notification";
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending SMS Notification");
    }
}

// Decorator
abstract class NotificationDecorator implements Notification {
    protected Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String getDescription() {
        return notification.getDescription();
    }

    @Override
    public void sendNotification() {
        notification.sendNotification();
    }
}

// Concrete Decorator
class PushNotification extends NotificationDecorator {
    public PushNotification(Notification notification) {
        super(notification);
    }

    @Override
    public String getDescription() {
        return notification.getDescription() + " + Push Notification";
    }

    @Override
    public void sendNotification() {
        notification.sendNotification();
        System.out.println("Sending Push Notification");
    }
}

// Another Concrete Decorator
class InAppNotification extends NotificationDecorator {
    public InAppNotification(Notification notification) {
        super(notification);
    }

    @Override
    public String getDescription() {
        return notification.getDescription() + " + In-App Notification";
    }

    @Override
    public void sendNotification() {
        notification.sendNotification();
        System.out.println("Sending In-App Notification");
    }
}


public class DecoratorDemo2 {
    public static void main(String[] args) {
        Notification notification = new EmailNotification();
        System.out.println(notification.getDescription());
        notification.sendNotification();

        System.out.println();

        Notification smsNotification = new SMSNotification();
        System.out.println(smsNotification.getDescription());
        smsNotification.sendNotification();

        System.out.println();

        Notification pushNotification = new PushNotification(notification);
        System.out.println(pushNotification.getDescription());
        pushNotification.sendNotification();

        System.out.println();

        Notification inAppNotification = new InAppNotification(pushNotification);
        System.out.println(inAppNotification.getDescription());
        inAppNotification.sendNotification();
    }
}

