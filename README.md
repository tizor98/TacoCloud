# Taco Cloud App

Application to put orders in a fictitious Taco restaurant using `Spring framework`.

You can download the project and run it with `Maeven` commands.
You will find one branch for one specific topic or subproject of Spring Framework.
Thus, each branch is independent of one another.

This app was developed using `Java 11`. Folder structure follows the usual Java application structure using an MVC design pattern.
Therefore, main files are located in `src/main/java/com.local.tacocloud/` for the base code, and `src/main/resources` for static
and configuration files.

### Dependencies

| Dependency  | Version or purpose                     |
|-------------|----------------------------------------|
| Spring Boot | 2.7.7                                  |
| Thumeleaf   | View Engine                            |
| MongoDB     | 4.0.28                                 |
| Flapdoodle  | To create embedded Mongo db            |
| Lombok      | To autogenerate getters, setters, etc. |

### Environment variables

You need to set a few environment variables for the app to function correctly
- SSL_KEY_PASSWORD to set the password for SSL key to allow https request.
You can generate the password and the `mykeys.jks` file reference in `application.yml`
with the `keytool` command in the terminal
- SPRING_PROFILES_ACTIVE to set the environment. Options are: `dev`
or `prod`

### Related repositories

- If you want to use ingredients api (api/ingredients, there is also available other ingredients api at data-api/ingredients) you need to get authorization. For the process you should use
the [TacoCloud-auth-server](https://github.com/tizor98/TacoCloud-auth-server) repo. There you will find instructions to successfully get authorization in the app using JWT.

### Created by Alberto Ortiz