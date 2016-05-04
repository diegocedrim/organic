# code-smells-detector

This project is an [Eclipse](http://www.eclipse.org) plugin that aims to collect [code smells](https://sourcemaking.com/refactoring/smells) from Java projects using only command line tools. The main objective of this project is to collect code smells with no user interaction. It does not provide user interface and this is not what we planned for this plugin. This plugin is directed to people who wants to run experiments by analyzing the existence of code smells in several projects.

# No GUI? Why is this an Eclipse Plugin?

This is only a convenience. We use the [Eclispe JDT API](http://www.eclipse.org/jdt/) in order to parse and analyze the syntactical structure of Java classes. 

# How does it work?

The workflow is pretty simple. You only need to specify one or more source code folders and one output file. The plugin will analyze all classes existing inside the source folder. In addition, it will output all code smells in a JSON file named by you. 

# Output format

The JSON file is the only output given by the tool. Its format is pretty simple: an array of objects. Each object represents one class. Each JSON object has a "methods" key, representing all methods of the analyzed class. The format is straightforward, as we can see in the following example.

```json
[
  {
    "methods": [
      {
        "parametersTypes": [
          "String"
        ],
        "metricsValues": {
          "ParameterCount": 1.0,
          "CyclomaticComplexity": 1.0,
          "LocalityRatio": 1.0,
          "MethodLinesOfCode": 2.0,
          "MaxCallChain": 1.0
        },
        "fullyQualifiedName": "Smiley.Smiley",
        "smells": []
      },
      {
        "parametersTypes": [
          "String"
        ],
        "metricsValues": {
          "ParameterCount": 1.0,
          "CyclomaticComplexity": 8.0,
          "LocalityRatio": null,
          "MethodLinesOfCode": 23.0,
          "MaxCallChain": 0.0
        },
        "fullyQualifiedName": "Smiley.checkSmiley",
        "smells": [
          {
		    "name": "LazyClass",
		     "reason": null,
		     "startingLine": 3,
		     "endingLine": 36
		  }
        ]
      }
    ],
    "metricsValues": {
      "PublicFieldCount": 0.0,
      "TightClassCohesion": 0.0,
      "IsAbstract": 0.0,
      "ClassLinesOfCode": 27.0,
      "OverrideRatio": 0.037037037037037035
    },
    "fullyQualifiedName": "Smiley",
    "smells": [
      {
        "name": "LazyClass",
        "reason": null,
        "startingLine": 3,
        "endingLine": 36
      }
    ]
  }
]
```


# Code Smells Detected

We implemented the rules published by [Bavota et al.](http://www.sciencedirect.com/science/article/pii/S0164121215001053) in order to detect all code smells types. This plugin detects the following 11 types of code smells:

- **Class data should be private**: 	A class having at least one public field.
- **Complex class**:	A class having at least one method for which McCabe cyclomatic complexity is higher than 10.
- **Feature envy**:	All methods having more calls with another class than the one they are implemented.
- **Blob class**:	All classes having (i) cohesion lower than the average of the system AND (ii) LOCs > 500.
- **Lazy class**:	All classes having LOCs lower than the first quartile of the distribution of LOCs for all systems classes.
- **Long method**:	All methods having LOCs higher than the average of the system.
- **Long parameter list**: 	All methods having a number of parameters higher than the average of the system.
- **Message chain**: 	All chains of methods calls longer than three.
- **Refused bequest**: 	All classes overriding more than half of the methods inherited by a superclass.
- **Spaghetti code**: 	A class implementing at least two long methods interacting between them through method calls or shared fields. 
- **Speculative generality**:	A class declared as abstract having less than three children classes using its methods.

# Installing

This plugin was tested only on Eclipse Mars.

- Download the [binary distribution](http://diegocedrim.github.io/downloads/smell-detector.zip);
- Unzip inside your eclipse folder;
- Done :)

# Running 

After installing, you can run this plugin using the following command on the terminal you use. As the command can be big, we have broken it into the following bash script.

```bash
ECLIPSE_PATH="/path/to/eclipse/installation"
EQUINOX="${ECLIPSE_PATH}/plugins/org.eclipse.equinox.launcher_1.3.100.v20150511-1540.jar org.eclipse.core.launcher.Main"
SMELL_DETECTOR="smell-detector-plugin.SmellDetector"
java -jar -XX:MaxPermSize=2560m -Xms40m -Xmx2500m ${EQUINOX} -application ${SMELL_DETECTOR} -output="smells.json" -src="/path/to/source/folder/" -src="/other/source/folder/"
```

Observations:
- **ECLIPSE_PATH** represents the installation directory of the eclipse, i.e., where the eclipse binary file resides;
- You can inform more than one source folder by using multiple -src arguments, as indicated above;
- Large projects can consume a considerable amount of memory, that's why we increase the JVM memory using the "-XX:MaxPermSize=2560m -Xms40m -Xmx2500m" arguments.
- Depending on the version of your eclipse, the version of org.eclipse.equinox.launcher can change. Check on that inside your eclipse plugins folder. In our test, the correct equinox jar file name was org.eclipse.equinox.launcher_1.3.100.v20150511-1540.jar
