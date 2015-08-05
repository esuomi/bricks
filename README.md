# Cradle - a Gradle project template

Base template to use for Gradle projects. Sort of similar to eg. Netflix' [gradle-template](https://github.com/Netflix/gradle-template), only more lightweight and suited to my personal needs.

## Starting a new project

First, create a Git repository as Cradle fork locally
```bash
git init project-name
cd project-name
git remote add cradle https://github.com/esuomi/Cradle.git
```

Second, init repository at GitHub like you normally would, add it as remote to `project-name` and merge the contents:
```bash
git remote add origin git@github.com:esuomi/http.git
git fetch --all
git checkout cradle/master
git push -u origin master
```

After this you can start adding the actual project files. Easiest place to start is the missing `build.gradle` and `gradle.properties`