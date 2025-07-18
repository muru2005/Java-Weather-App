import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
public class Login {
    Scanner sc = new Scanner(System.in);

    void register() {
        String name, username, pwd, phoneno, email, address, id;
        System.out.println("enter the name");
        name = sc.nextLine();
        System.out.println("enter the username");
        username = sc.nextLine();
        System.out.println("enter the password");
        pwd = sc.nextLine();
        System.out.println("enter the gmail");
        email = sc.nextLine();
        System.out.println("enter the address");
        address = sc.nextLine();
        System.out.println("enter the phone number");
        phoneno = sc.nextLine();
        id = phoneno + name.substring(0, 5);
        String csvfile = "login.csv";
        try (FileWriter writer = new FileWriter(csvfile, true)) {
            writer.append(id + "," + name + "," + username + "," + pwd + "," + phoneno + "," + email + "," + address + "\n");
            System.out.println("your id is :" + id);
            System.out.println("Registration is succesfully completed");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void login() throws IOException {
        String username, password;
        System.out.println("enter the username");
        username = sc.nextLine();
        System.out.println("enter the password");
        password = sc.nextLine();
        String csvFile = "login.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int c = 0;
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] data = line.split(csvSplitBy);
                if (data[2].equals(username) && data[3].equals(password)) {
                    System.out.println("login sucessfull");
                    c = 1;
                    break;
                }

            }
            if (c == 0) {
                System.out.println("login unsuccessfull");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    int verify(String username,String phoneno) throws IOException{
        String file = "D:\\java project\\untitled2\\src\\main\\java\\login.csv";
        String line = "";
        String csvSplitBy = ",";
        int c = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                if (data[2].equals(username) && data[4].equals(phoneno)) {
                    c = 1;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return c;

    }

    void forgotpwd(String username, String  phoneno,String password) throws IOException {
        String file = "D:\\java project\\untitled2\\src\\main\\java\\login.csv";
        String line = "";
        String csvSplitBy = ",";

        List<List<String>> arr1 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int c = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                List<String> dataList = new ArrayList<>(List.of(data));
                if (data[2].equals(username) && data[4].equals(phoneno)) {
                    System.out.println("enter the new password");
                    password = sc.nextLine();
                    dataList.set(3, password);
                    c = 1;
                }
                arr1.add(dataList);
            }

            try (FileWriter writer = new FileWriter(file, false)) {
                for (List<String> row : arr1) {
                    writer.append(String.join(csvSplitBy, row));
                    writer.append(",");
                    writer.append("\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static void main(String[] args) throws IOException {
       /* Login l = new Login();
        System.out.println("Welcome to the registration and login Portal!");
        while(true){
        int a = 0;
        System.out.println("enter 1-login\n2-registration\n3-forgot password");
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();

            if (a == 1) {
                l.login();
            } else if (a == 2) {
                l.register();
            } else {
                sc.nextLine();
                System.out.println("enter the username");
                String username = sc.nextLine();
                System.out.println("enter the phoneno");
                String phoneno = sc.nextLine();
                l.forgotpwd(username, phoneno);
            }
            int d=0;
            System.out.println("Do you want to continue further y-1 no-0");
            d=sc.nextInt();
            sc.nextLine();
            if(d==0){
                break;
            }
        }*/

    }
}





