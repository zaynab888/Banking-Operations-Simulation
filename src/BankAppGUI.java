import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import banking.BankAccount;
import banking.SavingsAccount;
import banking.CheckingAccount;
import banking.BusinessAccount;

public class BankAppGUI extends Application {
    // Instances de différents comptes
    private SavingsAccount savingsAccount = new SavingsAccount("SA123", 500);
    private CheckingAccount checkingAccount = new CheckingAccount("CA123", 1000);
    private BusinessAccount businessAccount = new BusinessAccount("BA123", 2000);

    private TextArea displayArea; // Zone pour afficher les informations du compte
    private ComboBox<String> accountTypeCombo; // Menu déroulant pour le choix du type de compte
    private TextField amountField; // Champ de saisie pour le montant

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Application Bancaire");

        // Zone d'affichage des informations
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(200);

        // Champ de saisie pour le montant
        amountField = new TextField();
        amountField.setPromptText("Montant");

        // Menu déroulant pour sélectionner le type de compte
        accountTypeCombo = new ComboBox<>();
        accountTypeCombo.getItems().addAll("Checking Account", "Savings Account", "Business Account");
        accountTypeCombo.setValue("Checking Account"); // Valeur par défaut

        // Boutons pour les opérations
        Button depositButton = new Button("Déposer");
        Button withdrawButton = new Button("Retirer");
        Button balanceButton = new Button("Consulter le solde");

        // Actions pour chaque bouton
        depositButton.setOnAction(e -> performDeposit());
        withdrawButton.setOnAction(e -> performWithdraw());
        balanceButton.setOnAction(e -> displayBalance());

        // Mise en page
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));
        vbox.getChildren().addAll(
                new Label("Type de compte:"), accountTypeCombo,
                new Label("Montant:"), amountField,
                depositButton, withdrawButton, balanceButton,
                displayArea
        );

        // Configurer la scène et l'afficher
        Scene scene = new Scene(vbox, 400, 350);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Charger le CSS après la création de la scène
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode pour obtenir l'instance de compte en fonction du type sélectionné
    private BankAccount getSelectedAccount() {
        String selectedAccountType = accountTypeCombo.getValue();
        switch (selectedAccountType) {
            case "Checking Account":
                return checkingAccount;
            case "Savings Account":
                return savingsAccount;
            case "Business Account":
                return businessAccount;
            default:
                return null; // Retourner null si aucun type sélectionné
        }
    }

    // Méthode pour effectuer un dépôt
    private void performDeposit() {
        double amount = parseAmount(amountField.getText());
        if (amount <= 0) {
            displayArea.setText("Montant invalide pour un dépôt.");
            return;
        }
        BankAccount account = getSelectedAccount();
        if (account != null) {
            account.deposit(amount);
            displayArea.setText("Dépôt de " + amount + " effectué.\nNouveau solde: " + account.getBalance());
        }
    }

    // Méthode pour effectuer un retrait
    private void performWithdraw() {
        double amount = parseAmount(amountField.getText());
        if (amount <= 0) {
            displayArea.setText("Montant invalide pour un retrait.");
            return;
        }
        BankAccount account = getSelectedAccount();
        if (account != null) {
            try {
                account.withdraw(amount);
                displayArea.setText("Retrait de " + amount + " effectué.\nNouveau solde: " + account.getBalance());
            } catch (Exception e) {
                displayArea.setText("Erreur: " + e.getMessage());
            }
        }
    }

    // Méthode pour consulter le solde
    private void displayBalance() {
        BankAccount account = getSelectedAccount();
        if (account != null) {
            displayArea.setText("Solde actuel de " + account.getClass().getSimpleName() + ": " + account.getBalance());
        }
    }

    // Convertir le montant entré en double
    private double parseAmount(String amountStr) {
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            displayArea.setText("Erreur: Le montant n'est pas valide.");
            return -1;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
