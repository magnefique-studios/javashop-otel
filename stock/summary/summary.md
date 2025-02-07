<a id="top"></a>

<p style="font-size: 24px;"><img src="./qct-icons/transform-logo.svg" style="margin-right: 15px; vertical-align: middle;"></img><b>Code Transformation Summary by Amazon Q </b></p>
<p><img src="./qct-icons/transform-variables-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Lines of code in your application: 175 <p>
<p><img src="./qct-icons/transform-clock-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Transformation duration: 8 min(s) <p>
<p><img src="./qct-icons/transform-dependencies-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Dependencies upgraded: 8 out of 16 <p>
<p><img src="./qct-icons/transform-smartStepInto-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> There are no deprecated APIs detected in this app <p>
<p><img src="./qct-icons/transform-listFiles-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Files changed: 3 <p>
<p><img src="./qct-icons/transform-build-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Build status in Java 17: <span style="color: #00CC00">SUCCEEDED</span> <p>

### Table of Contents

1. <a href="#build-log-summary">Build log summary</a> 
1. <a href="#planned-dependencies-replaced">Planned dependencies replaced</a> 
1. <a href="#additional-dependencies-added">Additional dependencies added</a> 
1. <a href="#deprecated-code-replaced">Deprecated code replaced</a> 
1. <a href="#all-files-changed">All files changed</a> 
1. <a href="#next-steps">Next steps</a> 


### Build log summary <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="build-log-summary"></a>

Amazon Q successfully built the upgraded code in Java 17. Here is a relevant snippet from the build log. To view the full build log, open [`buildCommandOutput.log`](./buildCommandOutput.log)

```
The Maven build compiled 9 Java source files and copied 1 resource file to target/classes. No test sources were compiled as none existed. The build completed successfully in 3.896 seconds.
```


### Planned dependencies replaced <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="planned-dependencies-replaced"></a>

Amazon Q updated the following dependencies that it identified in the transformation plan

| Dependency | Action | Previous version in Java 8 | Current version in Java 17 |
|--------------|--------|--------|--------|
| `info.cukes:cucumber-java` | Removed | 1.2.5 | - |
| `io.cucumber:cucumber-java` | Added | - | 7.19.0 |
| `jakarta.persistence:jakarta.persistence-api` | Added | - | 3.2.0 |
| `org.springframework.boot:spring-boot-starter-parent` | Updated | 2.1.3.RELEASE | 3.3.8 |

### Additional dependencies added <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="additional-dependencies-added"></a>

Amazon Q updated the following additional dependencies during the upgrade

| Dependency | Action | Previous version in Java 8 | Current version in Java 17 |
|--------------|--------|--------|--------|
| `info.cukes:cucumber-junit` | Updated | 1.2.5 | 1.2.6 |
| `info.cukes:cucumber-spring` | Updated | 1.2.5 | 1.2.6 |
| `org.codehaus.mojo:versions-maven-plugin` | Updated | 2.7 | - |
| `org.hamcrest:hamcrest-core` | Updated | 2.1 | - |

### Deprecated code replaced <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="deprecated-code-replaced"></a>

Amazon Q replaced the following instances of deprecated code. An instance with 0 files
changed indicates Amazon Q wasn't able to replace the deprecated code.

| Deprecated code | Files changed |
|----------------|----------------|


### All files changed <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="all-files-changed"></a>

| File | Action |
|----------------|--------|
| [pom.xml](../pom.xml) | Updated |
| [src/main/java/com/shabushabu/javashop/stock/config/DataGenerator.java](../src/main/java/com/shabushabu/javashop/stock/config/DataGenerator.java) | Updated |
| [src/main/java/com/shabushabu/javashop/stock/model/Stock.java](../src/main/java/com/shabushabu/javashop/stock/model/Stock.java) | Updated |

### Next steps <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="next-steps"></a>

1. Please review and accept the code changes using the diff viewer.If you are using a Private Repository, please ensure that updated dependencies are available.
1. In order to successfully verify these changes on your machine, you will need to change your project to Java 17. We verified the changes using [Amazon Corretto Java 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html) build environment.
1. If this project uses Maven CheckStyle, Enforcer, FindBugs or SpotBugs plugins, Q Code Transformation will disable those plugins when we build the project to verify proposed upgrades.
1. For detailed assistance on troubleshooting Java Transformation issues with Q, please refer to [Amazon Q documentation](https://docs.aws.amazon.com/amazonq/latest/qdeveloper-ug/how-CT-works.html#partially-successful-transformations), or engage with the `Q chat` for support and further recommendations.