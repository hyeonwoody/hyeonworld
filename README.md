# hyeonworld


<br>

## 🧑‍💻: Intro
가족의 단합을 도모하는 레크레이션 게임 모음.

❓ Problem : 명절날 오랜만에 모인 가족과 함께 즐길거리가 없다.

‼ Idea : 온 가족 구성원과 가까워질 수 있는 아이스브레이커 게임을 만들기.

💯 Solution : 온 세대가 즐길 수 있는 웹 기반 레크레이션 게임.

<br>

## 🗓️: Development Period
2022년 8월 ~ 현재 (약 6개월), 개발 1명.

<br>

## ✅: Implementation
- **프록시** : 서버에 접근하기 위한 프록시 설정.
- - **방화벽** : webpackDevServer.config.js에서 disableFirewall 옵션을 true로 설정.
- **포트포워딩** : 외부 네트워크에서 접속하기 위해 공유기 설정.
- **CORS 설정** : WebMvcConfigurer 활용.
- - **Login** :  파일로 저장된 가족 구성원 성함 목록과 비교하여 목록에 있을 경우 로그인 허용.
- **SSE** : 플레이어가 게임에 진입 할 때, 서버와 EventSource 연결. 서버는 SSeEmitter List로 관리.
- 

<br>

### 사용자는 다음과 같이 정의  :
|**정의**|**사용자 성함**|**설명**|
|:---:|:---:|:---:|
|서버 사용자|어드min|게임을 진행하는 사회자.|
|모니터 사용자|화면monitor|게임의 현재 진행 상황을 보여주는 전광판.|
|클라이언트|가족 성함|게임 참가자.|  

<br>

### 사회자는 참가자가 볼수 없는 메뉴를 선택하여 레크레이션 진행 :
|**메뉴**|**설명**|**구현**|
|:---:|:---:|:---:|
|Init|친가/외가에서 플레이하는지 설정, 참여 인원을 설정하며 로그인을 활성화.|X|
|Open|사회자가 open 메뉴를 눌러 플레이할 게임 초기화.|O|
|Terminate|모든 게임을 종료. 참가자는 메인 화면으로 강제 이동.|O|  

<br>

### 각 게임에는 단계가 있으며 이를 state 관리로 구현 ex) 진실혹은거짓 :
|**단계**|                      **설명**                       |**구현**|
|:---:|:-------------------------------------------------:|:---:|
|Tutorial|                 게임에 대한 설명 영상 재생.                  |X|
|Submit|      참가자에게 본인에 대한 2개의 진실 명제와 1개의 거짓 명제를 제출.       |O|
|Check|      사회자는 제출된 명제 확인 후, 흥미로운 명제를 제출한 참가자 선택.       |O|
|Show|  모든 참가자와 전광판 기기에 사회자가 선택한 참가자에 대한 3가지 명제를 보여 줌.   |O|
|Play| 제출한 참가자를 제외한 모든 참가자는 3개의 명제 중 1개의 거짓 명제를 골라 답 제출. |O|
|Result|   모든 참가자가 답을 제출하면 모니터 사용자 기기에 정답자를 공개 후 점수 합산.    |O|
|Ranking|               현재 점수 순위를 전광판에 보여 줌.                |O|  

<br>

게임의 진행상황은 party Entity에서 관리.

사회자는 단계를 변경할 수 있으며,  
모든 참가자에서도 변경이 적용.


한번 이상의 라운드를 거친 게임을 사회자 판단 하에 종료(Done)할 수 있으며  
라운드가 끝날 때마다 점수 게임 내용에 대한 기록(score_source) Entity 생성.

<br>

## 📞: Contact
- 이메일: hyeonwoody@gmail.com
- 블로그: https://velog.io/@hyeonwoody
- 깃헙: https://github.com/hyeonwoody

<br>

## 🧱: Technologies Used
> Javascript (react.js)
> 
> Java (Spring Boot)

<br>

## 📖: Libraries Used
> Tailwind CSS
> 
> Sping Data JPA (MariaDB)
> 
> EventSource & SseEmitter
> 
> react-query (현재 단계가 무엇인지 6초 간격으로 서버에게 요청)
> 
> axios (사회자 <=> 서버 <=> 참가자)
> 
> useState (게임 단계)  

<br>

## 🔥: Accomplishments


<br>
