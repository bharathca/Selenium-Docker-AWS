pipeline{
    
    agent any

    stages {
        
        stage('Build the Jar') {
            
            steps {
                sh "mvn clean package -DskipTests"
                        }
           
        } 

        stage('Build the docker image') {
            
            steps {
                
                sh "docker build -t=bharathca/selenium ."
            
            }
           
        }

        stage('Push the image') {
            
            steps {
                
                sh "docker push bharathca/selenium"
            
            }
           
        } 
    
    }
}