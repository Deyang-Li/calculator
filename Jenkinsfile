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
		stage("Code coverage") {
			steps {
      			sh "./gradlew jacocoTestReport"
				publishHTML (target: [
					reportDir: 'build/reports/jacoco/test/html',
					reportFiles: 'index.html',
					reportName: "JaCoCo Report"
				])
				sh "./gradlew jacocoTestCoverageVerification"
			}
		}
		stage("Static code analysis") {
			steps {
				sh "./gradlew checkstyleMain"
				publishHTML (target: [
     				reportDir: 'build/reports/checkstyle/',
     				reportFiles: 'main.html',
     				reportName: "Checkstyle Report"
				])
			}
		}
		stage("Package") {
     		steps {
          		sh "./gradlew build"
     		}
		}
		stage("Docker build") {
     		steps {
          		sh "docker build -t deyangli/calculator ."
     		}
		}
		stage("Docker push") {
     		steps {
     			sh 'docker login -u="deyangli" -p=$(eval echo "MTIzNDU2YXNkRg==" | base64 --decode)'
          		sh "docker push deyangli/calculator"
     		}
		}
		stage("Deploy to staging") {
     		steps {
          		sh "docker run -d --rm -p 8765:8080 --name calculator deyangli/calculator"
     		}
		}
		stage("Acceptance test") {
     		steps {
          		sleep 30
          		sh "./gradlew acceptanceTest -Dcalculator.url=http://178.238.231.36:8765"
     		}
		}
	}
	post {
		always {
			sh "docker stop calculator"
        	mail to: 'steven@gl8bl.com',
          	subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
        	body: "Your build completed, please check: ${env.BUILD_URL}"
     	}
	}
}