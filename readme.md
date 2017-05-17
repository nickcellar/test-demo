# TinkLabs Tests

## Notice

This project is using Lombok for generated fields and object creations, it is not ideal but it 
is good for quick implementation. It will not be a problem if we compile this project in 
command lines. However Android Studio may complainthat some class methods are not found.

https://projectlombok.org/setup/android.html

### Install Lombok Plugin in Android Studio

You can have Lombok support in Android Studio:

1. Go to File > Settings > Plugins
2. Click on Browse repositories...
3. Search for Lombok Plugin
4. Click on Install plugin
5. Restart Android Studio

## Data

Data is stored in a Firebase database, and fetched to the app via REST APIs. An exact 
copy of the database is exported and saved in `database.json` as reference.

https://firebase.google.com/docs/reference/rest/database/

## Continuous Integration

Continuous integration is set up with CircleCI and configuration file is `circle.yml`. It 
runs code quality tools such as `findbugs`, `jacoco`, `checkstyle` and `lint`. Reports are
then saved as artifacts.

## Screenshot
![alt text](https://firebasestorage.googleapis.com/v0/b/tinklabs-test.appspot.com/o/screencap.png?alt=media&token=c2db3835-afb9-441b-b427-e9cb2bdd6a3f)
