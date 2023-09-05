# 1 Suppose access to a DB needs to be granted for all developers of an application. How could be implemented the access control considering the principle of least privilege?
E.g. create an individual DB user for each developer and create a shared SSH key to login to the DB server.
```bash
ssh-rsa AAAAB3N... [Shared Developer Key]
```
```sql
-- Create role for each developer with restricted access
CREATE ROLE developer1 WITH LOGIN PASSWORD 'securepassword';
GRANT SELECT ON TABLE important_data TO developer1;
```
What other actions should be taken for a secure implementation of this control?

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

