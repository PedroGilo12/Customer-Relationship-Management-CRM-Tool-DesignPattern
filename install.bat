javac -d out -cp "lib\gson-2.8.2.jar;lib\itextpdf-5.5.9.jar;lib\lanterna-3.1.2.jar" application\Main.java utilities\*.java adapter\*.java userInterface\*.java dataInterface\*.java application\*.java
jar -cvfe ClientConnect.jar application.Main -C out .
