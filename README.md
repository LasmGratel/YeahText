# YeahText
Unicode text styling library

## Usage

Gradle:
```groovy
implementation 'dev.lasm:yeahtext:1.0.0'
```

In module-info.java:
```java
requires dev.lasm.yeahtext;
```

To transform your text:
```java
import dev.lasm.yeahtext.YeahText;

YeahText.transform("Hello, world!", "bold-italic");
//𝑯𝒆𝒍𝒍𝒐, 𝒘𝒐𝒓𝒍𝒅!
```

## License

Apache-2.0

## Credits

<https://github.com/macmcmeans/aleaPRNG>

<https://yaytext.com>
