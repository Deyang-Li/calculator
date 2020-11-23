pipeline {
	agent any
	stages {
		stage("Compile") {
			steps {
				sh "chmod +x gradlew"
				sh "./gradlew compileJava"
				//sh "./mvnw compile"
			}
		}
		stage("Unit test") {
			steps {
				sh "./gradlew test"
			}
		}
	}
}