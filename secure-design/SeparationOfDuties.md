# 1 A marketing company hires a new developer to manage their clients websites. Activities of the developer include:
```
1. Update the infrastructure of the websites
2. Update the content of the websites
3. Code changes requested to the websites
4. Deploy changes to the websites
5. Manage the databases of the websites
6. Retrieve the billing information of the clients infrastructure
```
How could be implemented separation of duties if there is a single developer?
 * Split work into roles
    * Assign roles to different employees
        * Content manager role
        * Billing role
        * Infrastructure manager role - same person
        * Developer role - same person
* Manage infrastructure for each client in multiple tenants/accounts
    * Each tenant/account has a different billing information
    * Each tenant/account has a different infrastructure
    * Each tenant/account has a different database
    * Each tenant/account has a different content
    * Each tenant/account has a different developer
