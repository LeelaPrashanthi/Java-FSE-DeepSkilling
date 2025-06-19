public class DecoratorPatternTest {
    public static void main(String[] args) {
        // Base notifier
        Notifier basicEmail = new EmailNotifier();

        // Decorated with SMS
        Notifier emailWithSMS = new SMSNotifierDecorator(basicEmail);

        // Further decorated with Slack
        Notifier multiChannelNotifier = new SlackNotifierDecorator(emailWithSMS);

        // Send notification via Email + SMS + Slack
        multiChannelNotifier.send("Server is down!");
    }
}
