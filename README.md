# Guice + Quartz + Jetty Sample App

+ Google Guice 4.0.1
+ Quartz Scheduler 2.3.0
+ Jetty 9.4.6.v20170531
+ Jetty embedded. running standalone.

## Usage

install application.

+ Java SDK 8
+ Apache Maven 3.x
+ Git

set environment variables.

+ PORT
    + Server Port. (ex.:"8080")
+ TZ
    + Timezone. (ex.:"Asia/Tokyo")
+ QUARTZ_SCHEDULE_DEFAULT
    + Default Job Cron schedule. (ex.:"0/10 * * * * ?")
+ QUARTZ_SCHEDULE_SCONDARY
    + Secondary Job Cron schedule. (ex.:"0 * * * * ?")

checkout, build and run. 

> git clone https://github.com/halflite/guice-quartz-jetty-sample.git
>
> cd guice-quartz-jetty-sample
>
> mvn clean package
>
> java -jar target\guice-quartz-jetty-sample-1.0-SNAPSHOT.jar

or 

> java -jar -DPORT="8080" -DTZ="Asia/Tokyo" -DQUARTZ_SCHEDULE_DEFAULT="0/10 * * * * ?" -DQUARTZ_SCHEDULE_SCONDARY="0 * * * * ?" target\guice-quartz-jetty-sample-1.0-SNAPSHOT.jar

## License

The MIT License (MIT)
http://opensource.org/licenses/MIT
