javac -cp ./lib/Pokemon.jar -d bin -sourcepath src ./src/Main.java
echo -e "Manifest-Version: 1.0\nMain-Class: Main\nClass-Path: lib/Pokemon.jar" > MANIFEST.MF
jar -cfm main.jar MANIFEST.MF -C bin .
mv main.jar ./optional_files
rm MANIFEST.MF
rm -rf bin