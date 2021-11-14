# Log Analytics Summary
 
This project does following 

a) Parse log file which is defined in assignment. 

b) Perform some basic transformation to create log summary by application

c) HSQL DB is embedded in the application to store output in the db

d) This app should be good enough to load big files though we need to increase heap size of java 


# Assumption

a) Only started or finished status type is supported else app will throw error while parsing input file.

b) Timestamp format is constant across the log file.

c) At max only two records per application is supported. 

d) Data is stored in embedded DB