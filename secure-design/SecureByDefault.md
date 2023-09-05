# 1 Consider below pseudo code to initialize a DB for web blog application.
```
user: "admin"
password: "admin"
connection = connect_to_db(user, password);
if (db_exists) {
    db = open_db(connection);
} else {
    db = create_db(connection);
}
```
How could above code be improved to be secure by default?

# 2 Below pseudo code will be used to design the password reset functionality for a web application.
```
front:
    email = input_email()
    if (is_valid = validate_email(email)) {
        enable_reset_password_button()
    }
    if (enable_reset_password_button_clicked()) {
        send_reset_password_request(email)
    }

back:
    user = get_user_from_db(email)
    if (user != null) { // if user exists won't be null
        new_password = generate_random_password()
        user.password = new_password
        update_user_in_db(user)
        email = create_reset_email(user.email, new_password)
        send_email(email)
    }
generate_random_password() {
    date = get_current_date()
    seed = date.timestamp() // timestamp is an integer number
    random = log(seed).toString().substring(0, 5)
}
```
How could above code be improved to be secure by default?