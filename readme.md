# Test Demo

## Notice

This project is using Lombok for generated fields and object creations, it is not ideal but it 
is good for quick implementation. It will not be a problem if we compile this project in 
command lines. However Android Studio may complainthat some class methods are not found.

https://projectlombok.org/setup/android.html

### Test coverage report in Android Studio

Add -noverify in the VM option for your tests.
https://github.com/robolectric/robolectric/issues/3023

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