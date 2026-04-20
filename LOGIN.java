public class LOGIN {
    private String username;
    private String password;
    private String cellPhone;

    public LOGIN(String username, String password, String cellPhone) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }

    // Username must contain "_" and be <= 5 characters
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Password must be >= 8 chars, contain uppercase, digit, and special char
    public boolean checkPasswordComplexity() {
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return password.length() >= 8 && hasUpper && hasDigit && hasSpecial;
    }

    // Cell phone must start with +27 and be exactly 12 characters long
    public boolean checkCellPhoneNumber() {
        return cellPhone.startsWith("+27") && cellPhone.length() == 12;
    }

    public String registerUser() {
        if (checkUserName() && checkPasswordComplexity() && checkCellPhoneNumber()) {
            return "User registered successfully";
        } else {
            return "Registration failed";
        }
    }

    public boolean loginUser(String loginUsername, String loginPassword) {
        return this.username.equals(loginUsername) && this.password.equals(loginPassword);
    }

    public String returnLoginStatus(String loginUsername, String loginPassword) {
        if (loginUser(loginUsername, loginPassword)) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }
}
