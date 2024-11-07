package ensa.ma.Banc;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Client> clients = new ArrayList<>();
    private static final ArrayList<Banque> banques = new ArrayList<>();
    private static final ArrayList<Compte> comptes = new ArrayList<>();
    private static final ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) throws JsonProcessingException {
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> addClient();
                case 2 -> listAllClients();
                case 3 -> findClient();
                case 4 -> addBank();
                case 5 -> listAllBanks();
                case 6 -> findBank();
                case 7 -> createAccount();
                case 8 -> listAllAccounts();
                case 9 -> performTransaction();
                case 10 -> listAllTransactions();
                case 11 -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
        System.out.println("Application closed.");

        Client client = new Client("C1","Nom","Prenmom","email@rr","Addre 123","098765");
        Client client2 = new Client("C2","Nom2","Prenmom2","email2@rr","Addre 123","098765");
        Banque banque = new Banque("B1","Maroc");
        Banque banque2 = new Banque("B2","Maroc");
        Compte compte = new Compte("CO1",Devise.MAD,client,banque,1200);

        System.out.println(compte.toJson());
        System.out.println(client.toJson());
        System.out.println(banque.toJson());

        client.addCompte(compte);
        banque.addCompte(compte);

        System.out.println(compte.toJson());
        System.out.println(client.toJson());
        System.out.println(banque.toJson());


    }

    // Display the start menu
    private static void displayMainMenu() {
        System.out.println("==================================================");
        System.out.println("\t\t\t\t\tMain Menu:");
        System.out.println("==================================================");
        System.out.println("\t1. Add Client");
        System.out.println("\t2. List All Clients");
        System.out.println("\t3. Find Client by ID");
        System.out.println("\t4. Add Bank");
        System.out.println("\t5. List All Banks");
        System.out.println("\t6. Find Bank by ID");
        System.out.println("\t7. Create Account for Client");
        System.out.println("\t8. List All Accounts");
        System.out.println("\t9. Perform Transaction");
        System.out.println("\t10. List All Transactions");
        System.out.println("\t11. Serialize/Deserialize");
        System.out.println("\t12. Exit");
        System.out.println("==================================================");
        System.out.print("Enter your choice: ");
    }


    // Getting the choice
    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("\n==================================================");
            System.out.println("Invalid input. Please enter a number.");
            System.out.println("==================================================\n");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    // Client Methods
    private static void addClient() {
        System.out.println("\n==================================================");
        System.out.println("\t\t\t\t\tAdd New Client:");
        System.out.println("==================================================");

        System.out.print("\tClient ID (unique): ");
        String id = scanner.nextLine();



        if (findClientByNum(id) != null) {
            System.out.println("\n==================================================");
            System.out.println("\t\tClient with this ID already exists.");
            System.out.println("==================================================\n");
            return;
        }

        System.out.print("\tNom du Client: ");
        String nom = scanner.nextLine();
        System.out.print("\tPrenom du Client: ");
        String firstName = scanner.nextLine();
        System.out.print("\tEmail du Client: ");
        String email = scanner.nextLine();
        System.out.print("\tAddress du Client: ");
        String address = scanner.nextLine();
        System.out.print("\tPhone du Client: ");
        String phone = scanner.nextLine();

        Client client = new Client(id, nom, firstName, email, address, phone);
        clients.add(client);
        System.out.println("==================================================");
        System.out.println("\t\t\t\t\tClient added successfully.");
        System.out.println("==================================================\n");
    }
    // Helper method to find Client by numClient
    private static Client findClientByNum(String numClient) {
        for (Client client : clients) {
            if (client.getNumClient().equals(numClient)) {
                return client;
            }
        }
        return null; // Client not found
    }
    private static void listAllClients() {

        if (clients.isEmpty()) {
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tNo clients found.");
            System.out.println("==================================================\n");
        } else {
            int count = clients.size();
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tFound " + count + " clients.");
            System.out.println("==================================================");
            clients.forEach(client -> {
                try {
                    System.out.println(client.toJson());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("==================================================\n");
        }
    }
    private static void findClient() throws JsonProcessingException {

        System.out.println("\n==================================================");
        System.out.println("\t\t\t\t\tFind a Client:");
        System.out.println("==================================================");

        System.out.print("\tEnter Client ID: ");
        String id = scanner.nextLine();
        scanner.nextLine();
        Client client = findClientByNum(id);

        if (client != null) {
            System.out.println("==================================================");
            System.out.println("\tClient found: " + client.toJson());
            System.out.println("==================================================\n");
        } else {
            System.out.println("==================================================");
            System.out.println("\tClient not found!!");
            System.out.println("==================================================\n");
        }
    }


    // Banques Methods
    private static void addBank() {

        System.out.println("\n==================================================");
        System.out.println("\t\t\t\t\tAdd New Banque:");
        System.out.println("==================================================");
        System.out.print("\tBank ID (unique): ");
        String id = scanner.nextLine();

        if (findBankByNum(id) != null) {
            System.out.println("\n==================================================");
            System.out.println("\t\tBank with this ID already exists.");
            System.out.println("=================================================\n");
            return;
        }

        System.out.print("\tCountry: ");
        String country = scanner.nextLine();

        Banque bank = new Banque(id, country);
        banques.add(bank);
        System.out.println("\n==================================================");
        System.out.println("\t\t\t\tBank added successfully.");
        System.out.println("==================================================\n");
    }
    private static Banque findBankByNum(String numBank) {
        for (Banque bank : banques) {
            if (bank.getId().equals(numBank)) {
                return bank;
            }
        }
        return null;
    }
    private static void listAllBanks() {
        if (banques.isEmpty()) {
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tNo banks found.");
            System.out.println("==================================================\n");
        } else {
            int count = banques.size();
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tFound " + count + " banks.");
            System.out.println("==================================================");
            banques.forEach(banque -> {
                try {
                    System.out.println(banque.toJson());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("==================================================\n");
        }
    }
    private static void findBank() throws JsonProcessingException {
        System.out.println("\n==================================================");
        System.out.println("\t\t\t\t\tFind a Bank:");
        System.out.println("==================================================");
        System.out.print("\tEnter Bank ID: ");
        String id = scanner.nextLine();
        Banque bank = findBankByNum(id);

        if (bank != null) {
            System.out.println("==================================================");
            System.out.println("\tClient found: " + bank.toJson());
            System.out.println("==================================================\n");
        } else {
            System.out.println("==================================================");
            System.out.println("\tClient not found!!");
            System.out.println("==================================================\n");
        }
    }


    // Comptes (Accounts) Methods
    private static void createAccount() {
        if (clients.isEmpty() || banques.isEmpty()) {
            System.out.println("==================================================");
            System.out.println("\t\t\t\t\tNo clients or banks available!!!!!");
            System.out.println("==================================================");
            return;
        }

        System.out.print("\tEnter Client ID: ");
        String clientId = scanner.nextLine();
        Client client = findClientByNum(clientId);

        if (client == null) {
            System.out.println("==================================================");
            System.out.println("\t\tClient not found.");
            System.out.println("==================================================");
            return;
        }

        System.out.print("\tEnter Bank ID: ");
        String bankId = scanner.nextLine();
        Banque bank = findBankByNum(bankId);

        if (bank == null) {
            System.out.println("==================================================");
            System.out.println("\t\tBank not found.");
            System.out.println("==================================================");
            return;
        }

        System.out.print("\tAccount Number (unique): ");
        String accNum = scanner.nextLine();

        if (findCompteByNum(accNum) != null) {
            System.out.println("==================================================");
            System.out.println("\t\tAn account with this number already exists.");
            System.out.println("==================================================");
            return;
        }

        System.out.print("\tCurrency (MAD/USD/EUR): ");
        Devise devise = Devise.valueOf(scanner.nextLine());

        System.out.print("\tInitial Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Compte compte = new Compte(accNum, devise, client, bank, balance);
        comptes.add(compte);
        client.addCompte(compte);
        bank.addCompte(compte);
        System.out.println("==================================================");
        System.out.println("\t\t\t\t\tAccount created successfully.");
        System.out.println("==================================================");
    }
    // Helper method to find Compte by account number
    private static Compte findCompteByNum(String numCompte) {
        for (Compte compte : comptes) {
            if (compte.getNumCompte().equals(numCompte)) {
                return compte;
            }
        }
        return null; // Account not found
    }
    private static void listAllAccounts() {
        if (comptes.isEmpty()) {
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tNo accounts found.");
            System.out.println("==================================================\n");
        } else {
            int count = comptes.size();
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tFound " + count + " accounts.");
            System.out.println("==================================================");
            comptes.forEach(c -> {
                try {
                    System.out.println(c.toJson());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("==================================================\n");
        }
    }


    // Transactions Methods
    private static void performTransaction() {
        System.out.println("\n==================================================");
        System.out.println("\t\t\t\t\tPerform a Transaction");
        System.out.println("==================================================\n");

        // Get Sender Account Number
        System.out.print("\tEnter Sender Account Number: ");
        String senderNum = scanner.nextLine();
        Compte sender = findCompteByNum(senderNum);

        if (sender == null) {
            System.out.println("\n==================================================");
            System.out.println("\t\tSender account not found.");
            System.out.println("==================================================\n");
            return;
        }

        // Get Receiver Account Number
        System.out.print("\tEnter Receiver Account Number: ");
        String receiverNum = scanner.nextLine();
        Compte receiver = findCompteByNum(receiverNum);

        if (receiver == null) {
            System.out.println("\n==================================================");
            System.out.println("Receiver account not found.");
            System.out.println("==================================================\n");
            return;
        }

        TypeTransaction type;

        Banque senderBanque = sender.getBanque();
        Banque receiverBanque = receiver.getBanque();

        if (
                senderBanque.equals(receiverBanque)
        ){
            type = TypeTransaction.VIRINI;
        }
        else if (
                senderBanque.getPays().equals(receiverBanque.getPays())
        ){
            type = TypeTransaction.VIREST;
        }
        else{
            type = TypeTransaction.VIRCHAC;
        }


        // Get Transaction Reference and Amount
        System.out.print("\tEnter Reference: ");
        String reference = scanner.nextLine();
        System.out.print("\tEnter Amount: ");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("\n==================================================");
            System.out.println("Invalid amount entered.");
            System.out.println("==================================================\n");
            scanner.nextLine();
            return;
        }

        if (amount <= 0) {
            System.out.println("\n==================================================");
            System.out.println("\t\tInvalid transaction amount.");
            System.out.println("==================================================\n");
            return;
        }

        // Create and Process the Transaction
        Transaction transaction = new Transaction(type, reference, sender, receiver, amount);

        if (transaction.process()) {
            transactions.add(transaction);
            System.out.println("\n==================================================");
            System.out.println("\t\tTransaction completed successfully.");
            System.out.println("==================================================\n");
        } else {
            System.out.println("\n==================================================");
            System.out.println("\t\tTransaction failed. Insufficient balance.");
            System.out.println("==================================================\n");
        }
    }


    private static void listAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tNo transactions found.");
            System.out.println("==================================================\n");
        } else {
            int count = transactions.size();
            System.out.println("\n==================================================");
            System.out.println("\t\t\t\t\tFound " + count + " transactions.");
            System.out.println("==================================================");
            transactions.forEach(tr -> {
                try {
                    System.out.println(tr.toJson());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("==================================================\n");
        }
    }


}