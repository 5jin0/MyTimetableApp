# 코드 설명

## MyTimetableApp 📅
Android(Java)로 제작한 개인 시간표 관리 앱입니다.  
SQLite 데이터베이스를 활용해 과목 정보를 저장하고, RecyclerView를 통해 직관적인 리스트 형태로 시간표를 표시합니다.

---

## 🚀 주요 기능
- 요일·시간·과목 정보를 입력하여 새로운 일정 추가
- RecyclerView 기반의 목록 조회
- 롱클릭 시 삭제 다이얼로그를 통해 손쉬운 일정 삭제
- SQLite 기반의 영구 저장 (앱 재실행 시에도 유지)

---

## 💡 코드 구조

com.example.mytimetable/
┣ 📂 model/
┃ ┗ Schedule.java
┣ DBHelper.java
┣ MainActivity.java
┣ AddScheduleActivity.java
┗ ScheduleAdapter.java


---

## 🌟 이 코드의 장점(선정 이유)

### 1️⃣ **깔끔하고 단일 책임 원칙(SRP)에 충실한 구조**
- 각 클래스가 명확한 역할을 가짐:
  - `DBHelper` → 데이터 저장/조회/삭제만 담당  
  - `MainActivity` → 전체 화면 흐름 관리  
  - `AddScheduleActivity` → 입력 및 검증  
  - `ScheduleAdapter` → UI 리스트 표현 및 이벤트 처리  
- 역할이 분리되어 유지보수가 용이하고, 수정 시 영향 범위가 작음.

---

### 2️⃣ **안정적인 SQLite 로직 구현**
- DBHelper에서 `onCreate()`와 `onUpgrade()`를 명확히 분리하여  
  앱 버전 변경 시에도 안전하게 테이블 구조 유지.  
- `deleteSchedule()`로 불필요한 레코드 정리 가능 → 데이터 누적 방지.

---

### 3️⃣ **RecyclerView + Adapter 설계의 우수성**
- `ScheduleAdapter`에서 **롱클릭 이벤트**를 별도의 인터페이스(`OnItemDeleteListener`)로 분리 →  
  Adapter와 Activity 간 결합도를 낮춤.  
- `MainActivity`는 Adapter의 콜백만 받아서 삭제 처리 → UI/데이터 흐름 명확.

---

### 4️⃣ **데이터 변경 즉시 반영되는 직관적인 UI**
- `onResume()`에서 `loadSchedules()` 호출 →  
  새로 추가하거나 삭제한 데이터가 즉시 반영되어 사용자 경험 향상.  
- 불필요한 DB 연결을 닫고, Cursor도 명시적으로 close() → 메모리 안전성 확보.

---

### 5️⃣ **확장성 고려한 구조**
- DBHelper의 insert/delete/selectAll 메서드를 분리해  
  추후 Room DB나 Firebase로 이식하기 쉬움.  
- Adapter의 콜백 구조 덕분에 “수정” 기능이나 “상세보기” 추가도 간단히 확장 가능.

---

## 🛠 기술 스택
- **Language:** Java  
- **Database:** SQLite  
- **UI:** RecyclerView + Material Button  
- **IDE:** Android Studio

---

## 📈 개선 방향 (To-Do)
- Room ORM으로 마이그레이션  
- Compose + ViewModel로 리팩토링  
- 시간 입력에 TimePickerDialog 도입  
- DiffUtil 적용으로 RecyclerView 성능 향상  


