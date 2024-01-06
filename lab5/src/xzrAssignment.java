import java.util.ArrayList;
import java.util.Random;

public class xzrAssignment {
    public static class Mail{
        String mail;
        public Mail(String mail){
            this.mail=mail;
        }
    }
    public static class PhoneNumber{
        String phoneNumber;
        public PhoneNumber(String phoneNumber){
            this.phoneNumber=phoneNumber;
        }
    }
    public class Player{
        private final String account=generateAccount();
        private String password;
        private Mail mail;
        private PhoneNumber phoneNumber;
//        ArrayList<Pokemon> pokemons=new ArrayList<>(); 这里只要文件中有 Pokemon类就不会报错了，到时候自己解除注释
        public Player(Mail mail,String password) {
        }
        public Player(PhoneNumber phoneNumber,String password){

        }
        public Player(Mail mail,PhoneNumber phoneNumber,String password){

        }
        public boolean checkIdentity(Mail mail, String password){
            return this.mail.mail.equals(mail.mail) && this.password.equals(password);
        }
        public boolean checkIdentity(PhoneNumber phoneNumber, String password){
            return this.phoneNumber.phoneNumber.equals(phoneNumber.phoneNumber)
                    && this.password.equals(password);
        }
        public boolean setMail(PhoneNumber phoneNumber, String password, Mail mail){
            if(checkIdentity(phoneNumber,password)){
                this.mail.mail= mail.mail;
                return true;
            }
            return false;
        }
        public boolean setPhoneNumber(Mail mail, String password, PhoneNumber phoneNumber){
            if(checkIdentity(mail,password)){
                this.phoneNumber.phoneNumber=phoneNumber.phoneNumber;
                return true;
            }
            return false;
        }
        public String getAccount(){
            return account;
        }
        public Mail getMail(){
            return this.mail;
        }
        public PhoneNumber getPhoneNumber(){
            return this.phoneNumber;
        }

        public String generateAccount(){
            Random r=new Random();
            StringBuilder s= new StringBuilder();
            int first=r.nextInt(10);
            if(first!=0){
                s.append(first);
            }
            for (int i = 1; i < 7; i++) {
                s.append(r.nextInt(10));
            }
            return s.toString();
        }

        public boolean changePassword(PhoneNumber phoneNumber, String oldPassword, String newPassword){
            if(checkIdentity(phoneNumber,oldPassword)){
                this.password=newPassword;
                return true;
            }
            return false;
        }
        public boolean changePassword(Mail mail,String oldPassword, String newPassword){
            if(checkIdentity(mail,oldPassword)){
                this.password=newPassword;
                return true;
            }
            return false;
        }
//        public void addPokemon(Pokemon pokemon){
//            pokemons.add(pokemon);
//        } 这里也是有Pokemon类之后就不会报错了。

    }
}
