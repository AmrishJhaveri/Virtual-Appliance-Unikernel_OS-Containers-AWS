# REST APIs on Virtual Appliance(VAP) using Unikernel OS(OSv) and Containers on AWS EC2 #

----------

## Pre-requiste ##

----------

- Ubuntu 16.04 / Linux OS
- Java v1.8
- Apache Maven v3.3.9
- Git v2.7.4
- Docker CE v18.09
- [Postman](https://www.getpostman.com/downloads/) 

## Setup  ##

----------

### Capstan ###
1. Install go package:

	`wget https://golang.org/doc/install?download=go1.12.1.linux-amd64.tar.gz`
	
2. Untar the tar.gz into /usr/local folder	

	`sudo tar -C /usr/local -xzf go1.2.1.linux-amd64.tar.gz`
	
3. Install qemu-kvm package.

	`sudo apt-get install qemu-kvm`			

4. Set the ENV variables:

	`echo 'export GOPATH=$HOME/go'  >>  $HOME/.profile`
	
	`echo 'export PATH=$PATH:$GOPATH/bin:/usr/local/go/bin' >>  $HOME/.profile`
	
	`source $HOME/.profile`

5. Install Capstan            

        `go get github.com/cloudius-systems/capstan`


## Build ##

----------

### 1. chess-rest-api.jar(Spring App) ###

Chekout this repository. Run the following command from within the directory where pom.xml is present.

	`mvn clean package`

Test this fat jar independently by running the following command:

	`java -jar target/chess-rest-api-0.0.1-SNAPSHOT.jar`

The REST API services for the Chess App will be running at `localhost:8081/chess/newGame` (POST request)

### 2.Build the OSv Image  ###

	`capstan run -f "8081:8081"-f "8000:8000"`

Chess Spring App runs on port 8081
OSv Dashboard and its REST APIs are available on port 8081.

Also this will create a image( `disk.qcow2` ) in the directory `~/.capstan/instances/qemu/chess-rest-api`


## Chess REST API & JSON structure ##

----------

- Chess's white piece will be the player using the rest service.
- All white pieces will be placed from a to h (horizontally-left to right) and 1 to 8 (bottom to top). 
- Please use the following service with Postman client.
- All requests are POST requests. Request and reponse JSON are provided for each kind of request.
- Please select POST request, in the request body section select "raw" and then select type as "application/json" from the drop down.
- You can copy the sample request json and change the values accordingly.
- Spring Application is exposed on port 8081. If you change the mapping on the host port then change the url accordingly. (Assumption host port & spring container port are mapped as 8081. 

### JSON MoveObject Fields ###

1. session(String): This will contain the session Id for the game provide by the newGame service(described below).
2. start(Stirng): The start square of the piece to be moved e.g. e2.
3. end(String): A target square of the piece for the the move e.g e4. It must be a valid square else the move will not be made.
4. message(String): Used in response for providing a description based on the move made.  
5. status(String): Provides the status of the game i.e. ONGOING, FINISHED, QUIT. QUIT is provided if the quit service was invoked. 

### New Game Service ###

URL: localhost:8081/chess/newGame

Request Type: POST

Request JSON:

	EMPTY

Response JSON:
	
	d85409d0-041c-4c04-85e4-c16b230b9274

The response is the session id of the game which is requried when invoking the subsequent services.


### Move Service ###

URL: localhost:8081/chess/move

Request Type: POST

Request JSON:

	{
	"session":"d85409d0-041c-4c04-85e4-c16b230b9274",
	"start":"e2",
	"end":"e4"
	}

Response JSON:
	
	{
   	 "start": "f7",
   	 "end": "f6",
   	 "session": "d85409d0-041c-4c04-85e4-c16b230b9274",
   	 "message": "",
   	 "status": "ONGOING"
	}


### Quit Game Service ###

URL: localhost:8081/chess/quit

Request Type: POST

Request JSON:

        {
        "session":"d85409d0-041c-4c04-85e4-c16b230b9274"
        }

Response JSON:

	{
	"start": "",
	"end": "",
	"session": "d85409d0-041c-4c04-85e4-c16b230b9274",
	"message": "The game exited successfully.",
	"status": "QUIT"
	}





## Deployment on AWS  ##

### OSv Image with Spring App on EC2 ###

Step 1: Convert the disk.qcow2 image located at `~/.capstan/instances/qemu/chess-rest-api to a `raw` format image
	
	`qemu-img convert disk.qcow2 chess-rest-api-img.raw`

Note: This creates a 10GB image. 

Step 2: Create a bucket on AWS S3 with the command:

	`aws s3 mb s3://com.uic.cs441.hw4.amrish`

![](https://bitbucket.org/ajhave5/amrishashvinkumar_jhaveri_hw4/raw/master/images/aws_osv_step2.png)

Step 3: Upload the `raw` image format file to s3 bucket with the command:

	`aws s3 cp chess-rest-api-img.raw s3://com.uic.cs441.hw4.amrish/`

![](https://bitbucket.org/ajhave5/amrishashvinkumar_jhaveri_hw4/raw/master/images/aws_osv_step3.png)

Step 4: 


	
### 5. Extract Output to System ###
- Once the job is completed the output needs to be extracted from hadoop to the local VM directory
    `hadoop fs -get output_dir/part-r-00000 ./` 

Sample Output:

    A. Prasad Sistla,A. Prasad Sistla,	102
    A. Prasad Sistla,Bing Liu 0001,	1
    A. Prasad Sistla,Isabel F. Cruz,	2
    A. Prasad Sistla,Lenore D. Zuck,	6
    A. Prasad Sistla,Robert H. Sloan,	1
    A. Prasad Sistla,V. N. Venkatakrishnan,	8
    Ajay D. Kshemkalyani,Ajay D. Kshemkalyani,	112
    Ajay D. Kshemkalyani,Ugo Buy,	1


### 6. SFTP to Cloudera ###
- Move this file to a folder on the host system.

### 7. Run Gephi for Graph ###
- Open Gephi and the workspace provided in the `logs` folder of this project.
- Import the CSV file( convert the file moved from the Cloudera to CSV extension).

Output Graph:

![](https://bitbucket.org/ajhave5/amrishashvinkumar_jhaveri_hw2/raw/master/logs/authors_mapping3.png)

## Built With

----------
- [Scala](https://www.scala-lang.org/) - Scala combines object-oriented and functional programming in one concise, high-level language
- [SBT](https://www.scala-sbt.org/) - sbt is a build tool for Scala & Java
- [Cloudera](https://www.cloudera.com/) - Cloudera QuickStart VMs (single-node cluster)
- [Hadoop](https://hadoop.apache.org/) - framework that allows for the distributed processing of large data sets

## Authors

----------

* [**Amrish Jhaveri**](https://github.com/AmrishJhaveri)
