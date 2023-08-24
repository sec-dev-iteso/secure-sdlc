# *Secure Software Development Lifecycle*

# Planning
On this phase of the SDLC the number of resources are defined and managed. Planning is a continuous process, not done in a single shot.
It is also where any project risks are defined. Sample of risks are:
- Internal and external dependencies. Affecting the schedule or inhereting risks.
- Lack of resources. Not enough bandwidth to complete the work. Not enough budget to buy a security solution.
- Extra costs. Not complying with schedule, need to onboard new personnel, need to enroll an external security vendor.

## Threat Modelling
Threat modelling can be performed in multiple ways, depending on the focus they aim to tackle.
To understand threat modelling, first we need to define a threat.
*Threat*. Any event that can impact any of the security primitives (confidentiality, integrity, availability, accountability, non-repudiation, authentication, authorization, etc.).

Threats can be intenational or not, external or internal, and ideally are defined in the basis of a business or operational concern.
Think about the answer to "What could go wrong?", followed by "What if it that happens?"

Some threat modelling techniques are:
* [STRIDE][1]. Spoofing, Tampering, Repudiation, Information Disclosure, Denial of Service, Elevation of Privilege.
* PASTA. Process for Attack Simulation and Threat Analysis.
* VAST. Visual, Agile and Simple Threat modelling.
* OCTAVE. Operationally Critical Threat, Asset, and Vulnerability Evaluation.
And can be supported with Data Flow Diagrams (DFD) or Attack Trees.

## Risk Assessment
Risks assessment, like threat modelling, is more an art than a science. It aims to identify not just what could go wrong and the impact of such problem, but also the likelihood of it to happen. Risk calculation should be as objective as possible, but subtle to the enviroment where they could arise.

Example of risk assessment techniques are:
* DREAD. Damage, Reproducibility, Exploitability, Affected Users, Discoverability.
* CVSS. Common Vulnerability Scoring System.
* [OWASP Risk Rating Methodology][14].
* [NIST SP 800-30. Guide for Conducting Risk Assessments][15].

Multiple times, risk assessment is a requirement to fulfill compliance requirements. Compliance can come from regulatory bodies, industry standards, or internal policies. For example, if credit card information needs to be stored, the system need to comply with PCI DSS, a standard from the Payment Card Industry Security Standards Council.

# Analysis & Design
During this phase, the what and the how of the system is defined at a high level. During this phase it's also possible to identify security requirements and architecture decisions that will influence the overall security of the system and it's pieces.

## Security Architecture and Design ​
Building a system with security from the start is key to reduce the cost of fixing security issues later in the SDLC. The objective is to cover protective measures before the first line of code is written, or the first server is spin up. Security controls aren't just technical, can also be operational and administrative like definining security roles and responsibilities in a human centric process.

## Secure Design Principles
Considering security can be too hard or to complex if are not well understood the principles behind what makes a system secure, it's easier to follow existing patterns and best practices that have been proven to work.

Some of the most common principles are:
* Least Privilege. Procure only the minimum required access to perform a task.
* Defense in Depth. Multiple layers of security controls deter attackers and reduce blast radius.
* Fail Securely. If a component fails, it should fail in a secure state.
* [Separation of Duties.][4] No single principal (user or external system) should be able to perform all task for critical operations.
* Complete Mediation. Access to a resource should be validated against it's permissions.
* [Open Design.][5] The design should not be secret, it should be open to the public (Analog to Kerckhoffs's principle).
* Psychological Acceptability. A security control should be easy, or otherwise users will find a way to bypass it.

Many of above principles can be completed using common patterns. For example, Role Based Access Controls (_RBAC_) are a way to implement Least Privilege and Separation of Duties. Implementing Access Control Lists (_ACL_) is a way to implement Complete Mediation and Defense in Depth.


# Implementation
During the development phase, what matters the most is producing functional software that meets the requirements. Due to the urgency to deliver fast, most time security is not a priority, and it's often left behind.
In order to improve the security of the final product, some common practices can be followed.

## Secure Coding Standards
Programming languages have their own best practices depending on:
* Runtime. Compiled vs interpreted languages pose different challenges. For example, compiled languages are  prone to buffer overflows, compared to the high risk of code injection in interpreted languages.
* Language. Each language has security measures embedded on the paradigm and rules of the language itself. On the other hand, some vulnerabilities are more frequent per language.
* Frameworks. Frameworks can provide security controls out of the box, but also can introduce new vulnerabilities if not used properly.

In order to better address security at the code level, it's recommended to follow secure coding standards depending on the language and framework used. Some example standards are:
* CERT Secure Coding Standards
* OWASP Secure Coding Practices
* Microsoft Secure Coding Guidelines
* NIST Secure Coding Guidelines
* CIS Critical Security Controls: Application Software Security
* CWE/SANS Top 25 Most Dangerous Software Errors

## Code Review
Security code reviews are a manual or semi-manual process that leverages finding vulnerabilities in the code. It can be done by a security expert, or by a developer with security knowledge. To be more objective about the findings, it's recommended to use a checklist and/or a tool to automate the process. For example, when performing a PR review, the reviewer can use a checklist to ensure that the code is following one of the above secure coding standards.

## Static Application Security Testing (SAST)
Code is after all text, and text can be tested using syntactic and semantic analysis. SAST tools analyze the code looking for know patterns or predicting behaviour that could lead to security errors. Usually, they are part of a manual code review or a CI/CD pipeline. Other type of analysis is Software Composition Analysis (SCA), which looks for known vulnerabilities in the dependencies of the application.

It's important to note that SAST tools are not perfect, and can produce large numbers of [false positives or false negatives][2]. False positives are when the tool reports a vulnerability that is not present or is not applicable due to the code context. False negatives are more dangerous, as they represent a real finding that is not reported.

A few of the most common SAST vendors are:
* SonarQube by SonarSource
* Fortify by OpenText
* Sonatype
* HCL AppScan
* Checkmarx
* Veracode
* Snyk
Each one with strenghts depending on language, frameworks and application domain.

Multiple open source tools are also available that can be integrated into a CI/CD pipeline or executed on demand. Among top rated tools are Semgrep, Bearer, CodeQL, Bearer which cover multiple languages.

# Tests
Quality assurance is like diagnosis the health of a system. If there exists errors that can lead to actual explotation of vulnerabilities, should be fixed before the system can be compromised.

## Dynamic Application Security Testing (DAST)
If SAST aims to identify code errors, DAST looks to identify errors in the runtime of the application. DAST tools are usually used to test web applications or API's, but can also be used other types like is the case for fuzzing CLI or embedded software. DAST tools are also known as black box testing, as they regularly don't have access to the source code.

Widely used DAST tools are Burp Suite, OWASP ZAP, Nikto, and vendors champ on market cap like Rapid7 (Metasploit maintainers), Invictu (formerly Netsparker) or Acunetix.

Just like SAST, DAST can produce a large amount of false positives or false negative, giving a false sense of security. It's important to understand the limitations of the tool and the context where it's being used. For example, un-authenticated scans won't be able to test authorization controls.

## Security Regression Testing
Whenever a security bug is found, a unit or integration test should be created to ensure that the bug is fixed and won't be reintroduced in the future. This is known as security regression testing. It is usually performed by software developers with a strong security background or security engineers.

## Penetration Testing
Penetration testing is a manual (or semi-automated) process where a security expert tries to find vulnerabilities in the system. It can be performed by an internal team or by an external vendor. In some cases, it's a compliance requirement to perform a penetration test before an organization can be certified by the regulatory body.

One of the big drawbacks of penetration testing is that it's a point in time test, and it's not possible to test all the possible scenarios. It's also a very expensive process, as it requires a high level of expertise and time to perform.

# Deployment
When a system reaches a testing or production environment it's said to be deployed. During this phase, the system is exposed a the real world scenario, and there is no comeback in case a security issue was left behind. Additionally, for agile software development and to follow DevOps practices, it's important to consider the security of the deployment process itself.

## Environment Hardening​
A secure code is as strong as the environment where it runs. Good system (as in OS) hardening practices are key to reduce the attack surface of the system. Examples of hardening techniques are:
* Disable unnecessary resources (services, ports, etc.)
* Apply least privilege to users and services
* Use secure configuration by default
* Communicate securely ([TLS][201], [SSH][200], etc.)
* Network segmentation

## Secure Deployment Processes
A system can be compromised in multiple phases during the automated or manual process to deploy. To ensure this doesn't happen, it's important to protect each step of it with similar controls to what the final system is expected to have.

A secure deployment process should include:
* Version control, ideally with cryptographic signatures
* Secure build and deploy process, with integrity checks (e.g. SBOM)
* Secure configuration management
* Enhanced secrets management

# Operations
Once the system is up and running, the development process doesn't stop. During the maintenance and monitoring of a system must be kept the security of the system in mind. At this phase is when attacks can happen and the system can be compromised.

## Patch Management
A system is as secure as it's parts, and considering most software runs above existing systems, it's important to keep them up to date. Patch management is the process of keeping the system up to date with the latest security fixes. A policy to ensure no software has passed it's end of life EOL or that no software is running with known vulnerabilities is key to reduce the attack surface of the system.

## Incident Response
It's not what if but when will happen a security incident. SANS propose a 6 step process to handle security incidents, which enables organizations to respond in a timely manner and reduce the impact of the incident. The steps are:
1. Preparation. Define the roles and responsibilities of the incident response team, and the process to follow in case of an incident.
2. Identification. Detect the incident and determine if it's a false positive or a real incident.
3. Containment. Isolate the incident to prevent further damage.
4. Eradication. Remove the root cause of the incident.
5. Recovery. Restore the system to a secure state.
6. Lessons Learned. Analyze the incident and identify what can be improved.

## Continuous Monitoring
Monitoring is key to detect incidents and respond to them in a timely manner. It's also a way to measure the effectiveness of the security controls in place. Monitoring can be done at different levels, from the network to the application, and can be done manually or automatically. A few of the minimum requisites to keep monitoring against security problems are:
* Log collection and analysis (usually with a SIEM)
* Network traffic analysis
* Application performance monitoring (APM)
* Vulnerability management
* Threat intelligence

# References (in Markdown)
[Threat Modelling Tool Threats. Microsoft][1]

[Static Application Security Testing. Snyk][2]

[OWASP Risk Rating Methodology][14]

[NIST SP 800-30][15]

[Segregation/separation of duties definition. ISACA][4]

[Secure Product Design. OWASP][5]

[What is SSH?. TechTarget][200]

[What is TLS (Transport Layer Security)?. Cloudflare][201]


[2]:https://snyk.io/learn/application-security/static-application-security-testing/ (Static Application Security Testing. Snyk)
[1]: https://learn.microsoft.com/en-us/azure/security/develop/threat-modeling-tool-threats#stride-model (Threat Modelling Tool Threats, Microsoft)
[14]:https://owasp.org/www-community/OWASP_Risk_Rating_Methodology (OWASP Risk Rating Methodology, OWASP)
[15]:https://csrc.nist.gov/pubs/sp/800/30/r1/final (NIST SP 800-30 Rev. 1, NIST)
[4]: https://www.isaca.org/resources/glossary#glosss (ISACA)
[5]: https://cheatsheetseries.owasp.org/cheatsheets/Secure_Product_Design_Cheat_Sheet.html (Secure Product Design. OWASP)
[200]:https://www.techtarget.com/searchsecurity/definition/Secure-Shell#:~:text=SSH%2C%20also%20known%20as%20Secure,that%20implement%20the%20SSH%20protocol.
[201]:https://www.cloudflare.com/learning/ssl/transport-layer-security-tls/


