# 1 Consider below pseudo code to authorize a user to access a resource.
```
resource_id = get_resource_id_from_request();
session_id = get_session_id_from_cookie(); // cookies are set at the browser and passed to the server with each request
user = get_user_from_session(session_id);
if (user.is_admin) {
    allow_access(resource_id);
} else {
    deny_access();
}
```
How could above code be improved to follow the principle of complete mediation?