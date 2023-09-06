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
```
user: "admin"
password: get_env_var("ADMIN_DB_PASSWORD") // ADMIN_DB_PASSWORD is an environment variable set by the sysadmin before 
initializing the application 
sanitize_password(password) // sanitize_password() is a function that removes special characters from the password
connection = connect_to_db(user, password); // connection is a singleton, to avoid more than 1 connection to the DB
if (db_exists) {
    db = open_db(connection);
} else {
    db = create_db(connection);
}
```


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

```
front:
    email = input_email()
    if (is_valid = validate_email(email)) { // check email is a valid email address
        enable_reset_password_button()
    }
    if (enable_reset_password_button_clicked()) {
        send_reset_password_request(email)
    }

back:
    email = get_email_from_request()
    if (!validate_email(email)) { // this surface defense in depth
        return error
    }
    user = get_user_from_db(email)
    if (user != null) { // if user exists won't be null
        new_token = generate_random_password_token()
        user.reset_token = new_token
        update_user_in_db(user)
        email = create_reset_email(user.email, new_token) // token is a OTP that only works once
        send_email(email)
    }
generate_random_password_token() {
    date = get_current_date()
    seed = date.timestamp() // timestamp is an integer number
    salt = get_salt_from_db()
    random = pseudo_random(seed, salt)
}
```