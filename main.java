// Código feito em aula por Artur Figueiredo, Enrique Campos Nogueira, João Victor dos Santos Silva


class GlobalConfig {
    private static GlobalConfig instance;
    
    private String nomeApp = "Sistema de Notificações";
    private String servidor = "servidor.empresa.com";
    private int maxRetries = 3;

    private GlobalConfig() {}

    public static GlobalConfig getInstance() {
        if (instance == null) {
            instance = new GlobalConfig();
        }
      else {
        System.out.println("ERRO: Configuração global já instanciada (implementação singleton):");
        System.out.println("App: " + instance.nomeApp);
        System.out.println("Servidor: " + instance.servidor);
        System.out.println("Máximo de tentativas: " + instance.maxRetries);
      }
        return instance;
    }

    public String getNomeApp() { return nomeApp; }
    public String getServidor() { return servidor; }
    public int getMaxRetries() { return maxRetries; }
}

interface Notification {
    void send(String msg);
}

class EmailNotification implements Notification {
    public void send(String msg) {
        System.out.println("Enviando E-mail: " + msg);
    }
}

class SMSNotification implements Notification {
    public void send(String msg) {
        System.out.println("Enviando SMS: " + msg);
    }
}

class PushNotification implements Notification {
    public void send(String msg) {
        System.out.println("Enviando Push Notification: " + msg);
    }
}

class NotificationFactory {
    public static Notification create(String type) {
        if (type == null) return null;
        
        switch (type.toUpperCase()) {
            case "EMAIL": return new EmailNotification();
            case "SMS":   return new SMSNotification();
            case "PUSH":  return new PushNotification();
            default: throw new IllegalArgumentException("Tipo de notificação desconhecido.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        
        GlobalConfig config = GlobalConfig.getInstance();
        System.out.println("Iniciando " + config.getNomeApp() + " no servidor " + config.getServidor());
        
        Notification email = NotificationFactory.create("EMAIL");
        Notification sms = NotificationFactory.create("SMS");
        Notification push = NotificationFactory.create("PUSH");
        // Notification error = NotificationFactory.create("ANY"); -> Causaria erro

        email.send("Mensagem por e-mail");
        sms.send("Mensagem por sms");
        push.send("mensagem por push");
        
        System.out.println();
        System.out.println("===== Tentando instanciar outra configuração global =======");
        System.out.println();
        
        GlobalConfig.getInstance();
    }
}
