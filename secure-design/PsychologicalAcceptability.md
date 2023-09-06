# 1 Suppose two factor authentication is implemented to access a web and mobile application.
```
web:
    2fa = user_enrolled() // default true
    session_timeout = 5min
    when user logs in:
        if (2fa) {
            timeout = set_timeout(session_timeout)
            send_2fa_code_mobile()
        }
        while(wait_for_2fa_code()) {
            if (timeout.expired()) {
                deny_access()
            }
            if (2fa_code_received()) {
                if (2fa_code_valid()) {
                    allow_access()
                } else {
                    deny_access()
                }
            }
        }

mobile:
    2fa = user_enrolled() // default true
    session_timeout = 5min
    when user logs in:
        if (2fa) {
            timeout = set_timeout(session_timeout)
            send_2fa_code_email()
        }
        while(wait_for_2fa_code()) {
            if (timeout.expired()) {
                deny_access()
            }
            if (2fa_code_received()) {
                if (2fa_code_valid()) {
                    allow_access()
                } else {
                    deny_access()
                }
            }
        }
```
How could be improved the security of the two factor authentication considering the psychological acceptability principle?

* Real world example:
```
when log in to Jira:
    while yubikey attempt < 3 {
        request 2fa yubikey
        wait for yubikey
        if (yubikey connected) {
            request yubikey PIN
            if (yubikey PIN valid) {
                allow access
            } else {
                deny access
                attempt++
            }
        }
    }
    request 2fa Microsoft Authenticator
    wait for Microsoft Authenticator
    if (Microsoft Authenticator valid) {
        allow access
    } else {
        deny access
    }
    
```

# 2 Email security could largely be improved by each user sign and encrypts emails with PGP
Why hasn't PGP been adopted by the majority of users?