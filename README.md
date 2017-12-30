# MVP-Pattern
[![Build Status](https://travis-ci.org/tehras/mvp_pattern.svg?branch=master)](https://travis-ci.org/tehras/mvp_pattern)

---

<h4>Gradle:</h4>

```Groovy

ext {
    mvp_starter = '0.0.7'
}

repositories {
    maven {
        url  "https://dl.bintray.com/tehras/maven" 
    }
}
      
dependencies {    
    implementation "com.github.tehras:mvppattern:$mvp_starter"
}

apply from: "https://raw.githubusercontent.com/tehras/mvp_pattern/$mvp_starter/dependencies.gradle"
```
