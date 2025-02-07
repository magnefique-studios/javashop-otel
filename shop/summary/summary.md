<a id="top"></a>

<p style="font-size: 24px;"><img src="./qct-icons/transform-logo.svg" style="margin-right: 15px; vertical-align: middle;"></img><b>Code Transformation Summary by Amazon Q </b></p>
<p><img src="./qct-icons/transform-build-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> The transformed app had errors when compiling in Java 17. Q has upgraded 85% of your dependencies. <p>
<p><img src="./qct-icons/transform-variables-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Lines of code in your application: 1040 <p>
<p><img src="./qct-icons/transform-clock-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Transformation duration: 40 min(s) <p>
<p><img src="./qct-icons/transform-dependencies-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Dependencies upgraded: 11 out of 13 <p>
<p><img src="./qct-icons/transform-smartStepInto-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> There are no deprecated APIs detected in this app <p>
<p><img src="./qct-icons/transform-listFiles-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Files changed: 2 <p>
<p><img src="./qct-icons/transform-build-dark.svg" style="margin-bottom: 1px; vertical-align: middle;"></img> Build status in Java 17: <span style="color: #CCCC00">PARTIALLY_SUCCEEDED</span> <p>

### Table of Contents

1. <a href="#build-log-summary">Build log summary</a> 
1. <a href="#planned-dependencies-replaced">Planned dependencies replaced</a> 
1. <a href="#additional-dependencies-added">Additional dependencies added</a> 
1. <a href="#deprecated-code-replaced">Deprecated code replaced</a> 
1. <a href="#other-changes">Other changes</a> 
1. <a href="#all-files-changed">All files changed</a> 
1. <a href="#next-steps">Next steps</a> 


### Build log summary <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="build-log-summary"></a>

Amazon Q could not build the upgraded code in Java 17. The following build log snippet that shows the errors Amazon Q encountered during the build log. To view the full build log, open [`buildCommandOutput.log`](./buildCommandOutput.log)

```
The Maven build failed with two errors. The POM file is missing version declarations for two Spring Cloud dependencies: spring-cloud-starter-circuitbreaker-resilience4j and spring-cloud-starter-netflix-eureka-client. As a result, Maven could not read the project and the build failed.
```


### Planned dependencies replaced <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="planned-dependencies-replaced"></a>

Amazon Q updated the following dependencies that it identified in the transformation plan

| Dependency | Action | Previous version in Java 8 | Current version in Java 17 |
|--------------|--------|--------|--------|
| `org.springframework.boot:spring-boot-starter-parent` | Updated | 1.5.19.RELEASE | 3.2.3 |

### Additional dependencies added <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="additional-dependencies-added"></a>

Amazon Q updated the following additional dependencies during the upgrade

| Dependency | Action | Previous version in Java 8 | Current version in Java 17 |
|--------------|--------|--------|--------|
| `io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations` | Updated | 1.19.2-alpha | 2.8.0 |
| `net.sourceforge.nekohtml:nekohtml` | Updated | 1.9.22 | - |
| `org.apache.logging.log4j:log4j-api` | Updated | 2.6.1 | 2.24.3 |
| `org.apache.logging.log4j:log4j-core` | Updated | 2.6.1 | 2.24.3 |
| `org.apache.maven.plugins:maven-jar-plugin` | Updated | 3.0.0 | - |
| `org.springframework.cloud:spring-cloud-dependencies` | Removed | Dalston.SR5 | - |
| `org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j` | Added | - |  |
| `org.springframework.cloud:spring-cloud-starter-eureka` | Removed | - | - |
| `org.springframework.cloud:spring-cloud-starter-hystrix` | Removed | - | - |
| `org.springframework.cloud:spring-cloud-starter-netflix-eureka-client` | Added | - |  |

### Deprecated code replaced <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="deprecated-code-replaced"></a>

Amazon Q replaced the following instances of deprecated code. An instance with 0 files
changed indicates Amazon Q wasn't able to replace the deprecated code.

| Deprecated code | Files changed |
|----------------|----------------|


### Other changes <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="other-changes"></a>



### All files changed <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="all-files-changed"></a>

| File | Action |
|----------------|--------|
| [pom.xml](../pom.xml) | Updated |
| [src/main/java/com/shabushabu/javashop/shop/JavaShopApp.java](../src/main/java/com/shabushabu/javashop/shop/JavaShopApp.java) | Updated |

### Next steps <a style="float:right; font-size: 14px;" href="#top">Scroll to top</a><a id="next-steps"></a>

1. Please review and accept the code changes using the diff viewer.If you are using a Private Repository, please ensure that updated dependencies are available.
1. In order to successfully verify these changes on your machine, you will need to change your project to Java 17. We verified the changes using [Amazon Corretto Java 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html) build environment.
1. If this project uses Maven CheckStyle, Enforcer, FindBugs or SpotBugs plugins, Q Code Transformation will disable those plugins when we build the project to verify proposed upgrades.
1. For detailed assistance on troubleshooting Java Transformation issues with Q, please refer to [Amazon Q documentation](https://docs.aws.amazon.com/amazonq/latest/qdeveloper-ug/how-CT-works.html#partially-successful-transformations), or engage with the `Q chat` for support and further recommendations.