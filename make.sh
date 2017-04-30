#!/bin/sh

echo "cleaning\n"
mvn clean

echo "building\n"
mvn package

echo "executing"
java -cp ./target/mips-simulator-0.0.1-SNAPSHOT.jar main.Main
