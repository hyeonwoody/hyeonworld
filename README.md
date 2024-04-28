# hyeonworld


<br>

## 🧑‍💻: Intro
가족의 단합을 도모하는 레크레이션 게임 모음.

❓Problem : 명절날 오랜만에 모인 가족과 함께 즐길거리가 없다.

❗Idea : 온 가족 구성원과 가까워질 수 있는 아이스브레이커 게임을 만들기.

💯Solution : 온 세대가 즐길 수 있는 웹 기반 레크레이션 게임.

<br>

## 🧱: Structure
![Web App Reference Architecture (3)](https://github.com/hyeonwoody/hyeonworld/assets/75844701/40fdf696-8fca-415f-84d6-06df73bfd300)

</br>

## 🛢️: Entity Relationship Diagram
![hyeonworld](https://github.com/hyeonwoody/hyeonworld/assets/75844701/5758f2e5-6370-462d-8fea-1fbd69138b19)

</br>


## 🗓️: Development Period
2022년 8월 ~ 2023년 6월, 개발 1명.

<br>

## ✅: Implementation
- **프록시** : 서버에 접근하기 위한 프록시 설정.
- **방화벽** : webpackDevServer.config.js에서 disableFirewall 옵션을 true로 설정.
- **포트포워딩** : 외부 네트워크에서 접속하기 위해 공유기 설정.
- **CORS 설정** : WebMvcConfigurer 활용.
- **SSE** : 플레이어가 게임에 진입 할 때, 서버와 EventSource 연결. 서버는 SSeEmitter List로 관리.
- **Login** :  파일로 저장된 가족 구성원 성함 목록과 비교하여 목록에 있을 경우 로그인 허용.
- **Member CRUD** : 사용자를 추가할 수 있는 어드민 메뉴.

<br>

### 사용자는 다음과 같이 정의  :
| 단어    | 내용                                    | 참조            |
| ----- | ------------------------------------- | ------------- |
| Moderator   | 사회자. 게임을 제어하는 사용자.                         |               |
| Monitor   | 모니터. 게임의 진행상황을 보여주는 전광판.                   |               |
| Participant   | 참가자. 게임에 참여하는 사용자.                         | 외가, 친가로 구분된다. |

<br>

### 어드민(사회자)메뉴 :
| **메뉴** |                               **설명**                               | **구현** |
|:------:|:------------------------------------------------------------------:|:------:|
|  Init  |          친가/외가에서 플레이하는지 설정, 참여 인원을 설정하며 로그인을 활성화.          |   O    |
|  Open  |                            플레이할 게임 초기화.                            |   O    |
|  Done  |                   게임을 종료하며, 모든 참가자를 홈화면으로 이동 제어.                   |   O    |  

<br>

### 각 게임에는 단계가 있으며 이를 state 관리로 구현 :
|**단계**|                **설명**                 | **구현** |
|:---:|:-------------------------------------:|:------:|
| Tutorial  |           각 게임에 대한 설명하는 단계.           |   X    |
| Submit    |             참가자가 제출하는 단계.             |   O    |
| Check    |      사회자가 참가자가 제출한 내용을 확인하는 단계.       |   O    |
| Show    | 모든 참가자와 모니터 기기에 사회자가 선택한 내용을 공개하는 단계. |   O    |
| Play    |     공개된 내용을 토대로 참가자가 게임을 진행하는 단계.     |   O    |
| Result    |           놀이의 결과를 공개하는 단계.            |   O    |
| Ranking    |        참가자의 현재 점수 순위를 보여주는 단계.        |   O    |
| Done |  사회자가 게임을 종료하며, 모든 참가자를 홈화면으로 이동 제어.  |   O    |


<br>

사회자는 단계를 변경할 수 있으며,  
모든 참가자에서도 변경이 적용.


한번 이상의 라운드를 거친 게임을 사회자 판단 하에 종료(Done)할 수 있으며  
라운드가 끝날 때마다 점수 게임 내용에 대한 기록(score_source) Entity 생성.

<br>

## 🎥: Demonstration

[Link to AWS EC2](http://13.125.105.200:13001/)
(AdminId : 가족 ) 

<br>

## 📞: Contact
- 이메일: hyeonwoody@gmail.com
- 블로그: https://velog.io/@hyeonwoody
- 깃헙: https://github.com/hyeonwoody

<br>

## 🧱: Technologies Used
> Java (Spring Boot)
> 
> Javascript (react.js)


<br>

## 📚: Libraries Used
> Tailwind CSS  
> 
> JDBC (MariaDB)  
> 
> EventSource & SseEmitter
> 
> axios (사회자 <=> 서버 <=> 참가자) 
> 
> React Hook [useState (게임 단계)]

<br>

## 🔥: Accomplishments
> [JPA to JDBC migration](https://github.com/hyeonwoody/hyeonworld/wiki/JPA-to-JDBC-Migration)

<br>

## 📖: Wiki
[hyeonworld Wiki](https://github.com/hyeonwoody/hyeonworld/wiki)

<br> 
