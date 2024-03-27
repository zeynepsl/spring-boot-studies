package com.zeynep.casestudy.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {

    //188.166.19.73
    // ssh-keygen -t rsa -b 4096 -C “zeyneprsalman@gmail.com” -f ./key/id_rsa
    //ssh -i key/id_rsa root@188.166.19.73

    /*


    scp -i key/id_rsa target/case-study-0.0.1-SNAPSHOT.jar root@188.166.19.73:/artifact


  If you need to have openjdk first in your PATH, run:
  echo 'export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"' >> ~/.zshrc

  stackover--> echo export "JAVA_HOME=\$(/usr/libexec/java_home)" >> ~/.zshrc


For compilers to find openjdk you may need to set:
  export CPPFLAGS="-I/opt/homebrew/opt/openjdk/include"


  sudo ln -sfn /opt/homebrew/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk

ssh-copy-id -i key/id_rsa root@ubuntu-s-1vcpu-512mb-10gb-ams3-01

root@ubuntu-s-1vcpu-512mb-10gb-ams3-01
*/
}



/*

new steps
 - (/Users/zsalaman/.ssh/id_rsa):


 Your identification has been saved in /Users/zsalaman/.ssh/id_rsa
Your public key has been saved in /Users/zsalaman/.ssh/id_rsa.pub
The key fingerprint is:
SHA256:F4NRcn7DKD9LlurmO21kfHNNsLxQLd+MD3Pg9ZzvRa0 zsalaman@zsalman-Pro.local


docker build -t your-image-name .
docker build -t app .

docker run -p 8080:8080 app:latest
docker run -d -p 8080:8080 --name bored-app --link bored-db ilkerguldali/boredapp:latest

*/
