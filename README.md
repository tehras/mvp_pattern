# MVP-Pattern
[![Build Status](https://travis-ci.org/tehras/mvp_pattern.svg?branch=master)](https://travis-ci.org/tehras/mvp_pattern)

---

<h4>Gradle:</h4>

```Groovy

// Define the version you want to be on
ext {
    mvp_starter = '0.0.14'
}
      
dependencies {    
    // Import the dependency
    implementation "com.github.tehras:mvppattern:$mvp_starter"
}

// This script will bring in Rx, Room, and Dagger dependencies
apply from: "https://raw.githubusercontent.com/tehras/mvp_pattern/$mvp_starter/dependencies.gradle"
```
