# anki-preparator
a small utility to prepare your words for import into anki program with '-' delimiter  
to build project  
```./gradlew clean build ```  
put your words for cards in some file, like test.txt  
```
hi-おい
```
then run `java -jar java -jar anki-preparator-1.0.jar test.txt` and you will have result file in your directory
```
cat ./result
```
```
hi - おい
おい - hi
```

todo: create anki `.apkg` cards file automatically
