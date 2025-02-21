![Image](https://github.com/user-attachments/assets/df06d2b6-22e7-4583-8960-009fb6a9d4e0)
![Image](https://github.com/user-attachments/assets/bd2a0a3f-d56a-418b-be0e-049f9ecf9bbb)
<br>
<br>
<!-- ------------------------ 주요 기능 ------------------------ -->
<div>
  <h1 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🖥️ 프로그라피 10기 Android 과제 </h1>  
  프로그라피 10기 Android 과제의 Android 프로젝트 레포지토리입니다.
</div>
<br>
<br>
<div>
  <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33; padding-bottom: 10px; margin-bottom: 20px;"> 주요 기능 </h2>
  <pre><code>🌲Unsplash 사진 탐색 기능
🌲랜덤 사진 조회 기능
🌲북마크 기능</code></pre>
</div>
<br>
<br>
<!-- ------------------------ 기술 ------------------------ -->
<div>
  <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33; padding-bottom: 10px; margin-bottom: 20px;"> 기술 스택 </h2>
  <h3 style="border-bottom: 1px solid #d8dee4; color: #282d33; padding-bottom: 10px; margin-bottom: 20px;"> ✨ Architecture & Pattern </h3>
</div>

* **Clean Architecture**: 비즈니스 로직을 담은 도메인 영역을 분리하고, 인터페이스를 통해서만 UI, DB와 상호작용하도록 하는 구조로 설정
* **MVVM Pattern**: Clean Architecture의 Presenter에서 logic과 view를 분리하기 위해 ViewModel을 사용한 MVVM 패턴 도입
* **Repository Pattern**: 도메인 영역에 있는 Repository interface를 구현하는 부분을 Data 영역에 두어 의존성 역전을 적용
<div>
<img src="https://github.com/user-attachments/assets/ca8a18b7-29a3-4f51-a843-3715a6bfc121" alt="디렉토리 구조 사진" style="width:30%;">
</div>
<br>
<div>
<h3 style="border-bottom: 1px solid #d8dee4; color: #282d33; padding-bottom: 10px; margin-bottom: 20px;"> ✨ UI </h3>
</div>

- **Shimmer(skeletonView)**: 로딩 시 skeletonView
- **Glide**: 이미지 프로세싱 
- **StaggeredGridLayoutManager**: 메인 페이지 속 바둑판식 view
- **ViewPager2**: 랜덤 사진 조회 페이지 속 view
- **swiperefreshlayout**: 위에서 아래로 swipe하여 새로고침

<br>
<div>
<h3 style="border-bottom: 1px solid #d8dee4; color: #282d33; padding-bottom: 10px; margin-bottom: 20px;"> ✨ Android, Jetpack </h3>
</div>
  
- **ViewModel**: MVVM 패턴 적용
- **Room**: 로컬 데이터베이스 관리
- **Hilt**: 의존성 주입
- **DownloadManager**: 이미지 다운로드

<br>
<div>
<h3 style="border-bottom: 1px solid #d8dee4; color: #282d33; padding-bottom: 10px; margin-bottom: 20px;"> ✨ Convention </h3>
</div>
  
- **Git flow**: 브랜치 관리 방식
- **Gitmoji**: 커밋 컨벤션
- **Issue template**: 이슈 컨벤션

<br>
<br>
<br>
<br>
