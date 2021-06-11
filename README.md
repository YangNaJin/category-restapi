# 카테고리 Rest API

### 개발환경

##### 주요환경
* Spring boot 2.5
* Java 8
* h2
##### 소스 폴더 설명
``` 
root 
     |- src/main/java/com.musinsa.cate
         |- controller : Controller 클래스
         |- service : Service 클래스
         |- entiry : Entity 클래스
         |- dto : DTO 클래스
         |- repository : Repository 클래스
     |- src/test/java/com.musinsa.cate
         |- service : ServiceTest 클래스
```
##### Gradle 환경에 따른 Build 명령어 및 실행시 필수 옵션
```
Build 명령어 
    ./gradlew clean build 
```
##### 실행 명령어
```
Build 명령어 
    java -jar /musinsa/build/libs/musinsa-0.0.1-SNAPSHOT.jar 
```

##### REST API 전송 파라메터 
```
POST /v1/saveCate
{
    "cateCode": "003", //필수
    "cateSubCode": "003001", // optional null cateCode + "000" 코드가 기본으로 등록
    "cateName": "하의" //필수
}

POST /v1/removeCate
{
    no: 1 //필수
}

POST /v1/updateCate
{
    "no": "4", //필수
    "cateCode": "003", //필수
    "cateSubCode": "003001", //필수
    "cateName": "하의" //필수
}

GET /v1/search?cateCode=001
cateCode 값이 빈값일경우 전체 조회

```
##### Database
```
h2 Embeded DB

localhost:8082/h2-console
application jdbcs url : jdbc:h2:~/category
test jdbc url : jdbc:h2:~/test
```
##### IntelliJ IDE 초기 셋팅
```
1. Lombok plugin 설치
  > Menu -> Preferences -> Plugins => Browse repositories
    Lombok Plugin 검색 후 설치
    
2. 어노테이션 사용 설정
  > Menu -> Preferences -> Build, Execution, Deployment -> Compiler ->
    Annotation Processors
    
    Enable annotation processing 체크박스 체크
```