package elias.vwa;

import elias.vwa.eventHandler.EventHandler;
import elias.vwa.utility.Utilities;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    public static JDA jda;

    public static void main(String[] args) {
        login();

        registerEventListeners();
    }

    /**
     * Versucht sich in das Benutzerkonto welches zum angegebenen Token gehört anzumelden
     */
    private static void login() {
        System.out.println("Versuche in das Benutzerkonto einzuloggen...");
        try {
            jda = JDABuilder.createDefault(Utilities.getTokenFromFile()).build();
            System.out.println("Erfolgreich in das Benutzerkonto " + jda.getSelfUser().getAsTag() + " eingeloggt");
        } catch (LoginException e) {
            System.err.println("Fehler beim Anmelden!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void registerEventListeners() {
        System.out.println("Registriere Event Listeners...");
        jda.addEventListener(new EventHandler());
    }

}
