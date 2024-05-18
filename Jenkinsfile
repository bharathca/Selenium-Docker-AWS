pipeline{
    
    agent any

    stages {
        
        stage('Build the Jar') {
            
            steps {
                bat "mvn clean package -DskipTests"
                        }
           
        } 

        stage('Build the docker image') {
            
            steps {
                
                bat "docker build -t=bharathca/selenium ."
            
            }
           
        }

        stage('Push the image') {
            
            steps {
                
                bat "docker push bharathca/selenium"
            
            }
           
        } 
    
    }
}