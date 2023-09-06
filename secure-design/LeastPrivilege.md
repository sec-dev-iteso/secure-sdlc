# 1 Suppose access to a DB needs to be granted for all developers of an application. How could be implemented the access control considering the principle of least privilege?
E.g. create an individual DB user for each developer and create a shared SSH key to login to the DB server.
```bash
ssh-rsa AAAAB3N... [Shared Developer Key]
```
```sql
-- Create role for each developer with restricted access
CREATE ROLE developer1 WITH LOGIN PASSWORD 'securepassword';
GRANT SELECT ON TABLE schema.specifc_table TO developer1; -- Save relationship between developer and table
```

What other actions should be taken for a secure implementation of this control?
* Restrict access to the DB server to the IP of the ssh server
* Restrict each developer user to read only and not create or delete tables
* Restrict each developer to read only specific tables
* Create one ssh key per developer, one server user and one DB role assigned to each user per developer
```bash
ssh-rsa AAAAB3N... [One Developer Key per developer]
ssh-rsa BBBBB3N... [One Developer Key per developer]
ssh-rsa CCCCB3N... [One Developer Key per developer]
ssh-rsa DDDDB3N... [One Developer Key per developer]
```
```sql
-- Create role for each developer with restricted access
CREATE ROLE developer_read_only WITH LOGIN PASSWORD 'securepassword';
GRANT SELECT ON TABLE schema.* TO developer1; -- Save relationship between role and tables
```


# 2 Suppose a web application needs to be deployed in a cloud environment and each resource is controlled via an Identity and Access Management policy assigned to roles.
```yaml
iam: Identity and Access Management
    internet_gateway: true
    database:
        read: true
        write: true
    load_balancer: true
    instance:
        read: true
        write: true
        execute: true
front: 
    iam = iam
back:
    iam = iam
database:
    iam = iam
```
 How could be implemented the access control considering the principle of least privilege?
```yaml
iam_compute_instances: Identity and Access Management for instances
    internet_gateway: true
    instance:
        read: true
        write: true
        execute: true
    
iam_public: Identity and Access Management for external resources
    internet_gateway: true
    load_balancer: true
    instance:
        read: true
        execute: true
iam_datastores: Identity and Access Management for data storage
    instance:
        read: true
        write: true
        execute: true
    database:
        read: true
        write: true

front: 
    iam = iam_public
back:
    iam = iam_compute_instances
database:
    iam = iam_datastores
 ```