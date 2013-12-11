package tengil;

public class Validator {
        String errorMessage;

        public Validator() {
                errorMessage = "";
        }

        
        public String getErrorMessage() {
                return errorMessage;
        }

        
       
        public boolean validateFirstName(String firstName) {
                errorMessage = "";
                return this.validateOnlyLetters(firstName);
        }

        
  

        public boolean validateNumbers(String str) {
                errorMessage = "";
                return this.validateOnlyNumbers(str);
        }

        
    
        public boolean validateNullInput(String str) {
                errorMessage = "";
                return validateNull(str);
        }

        
        
        
        
        private boolean validateOnlyLetters(String str) {
                if (!this.validateNull(str)) {
                        return false;
                }

                if (!str.matches("[a-zA-Z]+$")) {
                        errorMessage += "Siffror ar ej tillatna!!";
                        return false;
                }
                return true;
        }

       
        private boolean validateOnlyNumbers(String str) {
                if (!str.matches("^[0-9]+$")) {
                        errorMessage += "Bokstaver ar ej tillatna!";
                        return false;
                }
                return true;

        }

        
       
        private boolean validateNull(String str) {
                if (str.equals("") || str.equals(null)) {
                        errorMessage += "Var god fyll i faltet!";
                        return false;
                }
                return true;
        }

        
       
        private boolean validateLength(String str) {
                if (str.length() == 1) {
                        errorMessage += "Faltet maste vara minst tva bokstaver!";
                        return false;
                }
                return true;
        }

}